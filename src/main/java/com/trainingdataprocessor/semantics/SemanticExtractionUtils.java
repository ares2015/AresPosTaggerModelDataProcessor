package com.trainingdataprocessor.semantics;

import com.trainingdataprocessor.tags.EncodedTags;

import java.util.List;

/**
 * Created by Oliver on 10/15/2016.
 */
public final class SemanticExtractionUtils {

    public static int getStartIndexForPredicateExtraction(int constantIndex, List<String> encodedTags, SemanticRelationConstantType constantType) {
        if (SemanticRelationConstantType.MODAL_VERB_3_LEVEL == constantType || SemanticRelationConstantType.MODAL_VERB_NOT_3_LEVEL == constantType) {
            return getVerbIndex(encodedTags, constantIndex) + 1;
        } else {
            return constantIndex + 1;
        }
    }

    public static int getVerbIndex(List<String> encodedTags, int modalVerbIndex) {
        for (int i = modalVerbIndex; i <= encodedTags.size() - 1; i++) {
            if (EncodedTags.VERB.equals(encodedTags.get(i))) {
                return i;
            }
        }
        throw new IllegalStateException("There is no verb in the list of tags.");
    }
}
