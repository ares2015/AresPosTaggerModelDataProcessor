package com.trainingdataprocessor.semantics;

import com.trainingdataprocessor.tags.EncodedTags;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.trainingdataprocessor.semantics.SemanticExtractionUtils.getVerbIndex;

/**
 * Created by Oliver on 10/15/2016.
 */
public class VerbExtractorImpl implements VerbExtractor {

    private final Set<String> negativeIsAndModalVerbs = new HashSet<>();

    private final Set<String> presentTenseModalVerbs = new HashSet<>();


    {
        negativeIsAndModalVerbs.add("isn't");
        negativeIsAndModalVerbs.add("aren't");
        negativeIsAndModalVerbs.add("wasn't");
        negativeIsAndModalVerbs.add("weren't");
        negativeIsAndModalVerbs.add("shouldn't");
        negativeIsAndModalVerbs.add("wouldn't");
        negativeIsAndModalVerbs.add("mustn't");
        negativeIsAndModalVerbs.add("can't");
        negativeIsAndModalVerbs.add("couldn't");

        presentTenseModalVerbs.add("can");
        presentTenseModalVerbs.add("can't");
        presentTenseModalVerbs.add("could");
        presentTenseModalVerbs.add("couldn't");
        presentTenseModalVerbs.add("would");
        presentTenseModalVerbs.add("wouldn't");
        presentTenseModalVerbs.add("should");
        presentTenseModalVerbs.add("shouldn't");
        presentTenseModalVerbs.add("must");
        presentTenseModalVerbs.add("mustn't");

    }

    public String extractExtendedVerb(List<String> subSentence, List<String> encodedTags, int constantIndex, SemanticRelationConstantType constantType) {
        String verbAuxiliaryVerbPhrase = "";
        int verbIndex = getVerbIndex(encodedTags, constantIndex);
        if (SemanticRelationConstantType.MODAL_VERB_3_LEVEL == constantType || SemanticRelationConstantType.MODAL_VERB_NOT_3_LEVEL == constantType) {
            for (int i = constantIndex; i <= verbIndex; i++) {
                if (i < verbIndex) {
                    verbAuxiliaryVerbPhrase += subSentence.get(i) + " ";
                } else {
                    verbAuxiliaryVerbPhrase += subSentence.get(i);
                }
            }
        } else if (SemanticRelationConstantType.VERB_DONT_3_LEVEL == constantType) {
            verbAuxiliaryVerbPhrase = subSentence.get(constantIndex - 1) + " " + subSentence.get(constantIndex);
        } else if (SemanticRelationConstantType.VERB_DO_NOT_3_LEVEL == constantType) {
            verbAuxiliaryVerbPhrase = subSentence.get(constantIndex - 2) + " " + subSentence.get(constantIndex - 1) + " " + subSentence.get(constantIndex);
        } else if (SemanticRelationConstantType.IS_NOT_3_LEVEL == constantType) {
            verbAuxiliaryVerbPhrase = subSentence.get(constantIndex) + " " + subSentence.get(constantIndex + 1);
        }
        return verbAuxiliaryVerbPhrase;
    }

    public String extractVerbFromModalVerbPhrase(List<String> subSentence, List<String> encodedTags, int constantIndex) {
        int verbIndex = getVerbIndex(encodedTags, constantIndex);
        return subSentence.get(verbIndex);
    }

    public boolean isPositiveVerb(List<String> subSentence, int constantIndex, SemanticRelationConstantType constantType) {
        return !(negativeIsAndModalVerbs.contains(subSentence.get(constantIndex)) || SemanticRelationConstantType.negativeTypes.contains(constantType));
    }

    public boolean isPresentTense(List<String> subSentence, List<String> encodedTags, int constantIndex, SemanticRelationConstantType constantType) {
        if(!(SemanticRelationConstantType.negativeTypesForPresentTenseTest.contains(constantType))){
            String token = subSentence.get(constantIndex);
            String encodedTag = encodedTags.get(constantIndex);
            return ("is".equals(token) || "are".equals(token)) || EncodedTags.VERB.equals(encodedTag) || presentTenseModalVerbs.contains(token);
        }else {
            return !(containsDidDidntToken(subSentence, constantIndex));
        }
    }

    private boolean containsDidDidntToken(List<String> subSentence, int constantIndex){
        for(int i = constantIndex; i >= 0; i --){
            if(subSentence.get(i).equals("did") || subSentence.get(i).equals("didn't")){
                return true;
            }
        }
        return false;
    }
}
