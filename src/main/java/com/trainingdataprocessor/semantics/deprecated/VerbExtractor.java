package com.trainingdataprocessor.semantics.deprecated;

import java.util.List;

/**
 * Created by Oliver on 10/15/2016.
 */
public interface VerbExtractor {

    String extractExtendedVerb(List<String> subSentence, List<String> encodedTags, int constantIndex, SemanticRelationConstantType constantType);

    String extractVerbFromModalVerbPhrase(List<String> subSentence, List<String> encodedTags, int constantIndex);

    boolean isPositiveVerb(List<String> subSentence, int constantIndex, SemanticRelationConstantType constantType);

    boolean isPresentTense(List<String> subSentence, List<String> encodedTags, int constantIndex, SemanticRelationConstantType constantType);

}
