package com.trainingdataprocessor.semantics.deprecated;

import java.util.List;

/**
 * Created by Oliver on 10/15/2016.
 */
public interface PredicateExtractor {

    String extractExtendedPredicate(List<String> subSentence, List<String> encodedTags,
                                    int constantIndex, SemanticRelationConstantType constantType);

    String extractExtendedPredicate(List<String> subSentence, List<String> encodedTags,
                                    int constantIndex, int prepositionIndex, SemanticRelationConstantType constantType);

    String extractPrepositionPredicate(List<String> subSentence, List<String> encodedTags, int constantIndex,
                                       SemanticRelationConstantType constantType);
}
