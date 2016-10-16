package com.trainingdataprocessor.semantics;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.trainingdataprocessor.semantics.SemanticExtractionUtils.getVerbIndex;

/**
 * Created by Oliver on 10/15/2016.
 */
public class VerbExtractorImpl implements VerbExtractor {

    private final Set<String> negativeIsAndModalVerbs = new HashSet<>();

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
    }

    public String extractExtendedVerb(List<String> subSentence, List<String> encodedTags, int constantIndex, SemanticRelationConstantType constantType) {
        String verbAuxiliaryVerbPhrase = "";
        int verbIndex = getVerbIndex(encodedTags, constantIndex);
        if (SemanticRelationConstantType.MODAL_VERB == constantType || SemanticRelationConstantType.MODAL_VERB_NOT == constantType) {
            for (int i = constantIndex; i <= verbIndex; i++) {
                if (i < verbIndex) {
                    verbAuxiliaryVerbPhrase += subSentence.get(i) + " ";
                } else {
                    verbAuxiliaryVerbPhrase += subSentence.get(i);
                }
            }
        } else if (SemanticRelationConstantType.VERB_DONT == constantType) {
            verbAuxiliaryVerbPhrase = subSentence.get(constantIndex - 1) + " " + subSentence.get(constantIndex);
        } else if (SemanticRelationConstantType.VERB_DO_NOT == constantType) {
            verbAuxiliaryVerbPhrase = subSentence.get(constantIndex - 2) + " " + subSentence.get(constantIndex - 1) + " " + subSentence.get(constantIndex);
        } else if (SemanticRelationConstantType.IS_NOT == constantType) {
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
}
