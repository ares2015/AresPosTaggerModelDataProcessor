package com.trainingdataprocessor.semantics;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.trainingdataprocessor.semantics.SemanticExtractionUtils.getVerbIndex;

/**
 * Created by Oliver on 10/15/2016.
 */
public class VerbExtractorImpl implements VerbExtractor {

    private final Set<String> positiveIsNouns = new HashSet<>();

    {
        positiveIsNouns.add("is");
        positiveIsNouns.add("are");
        positiveIsNouns.add("was");
        positiveIsNouns.add("were");
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
        return positiveIsNouns.contains(subSentence.get(constantIndex)) || SemanticRelationConstantType.positiveTypes.contains(constantType);
    }
}
