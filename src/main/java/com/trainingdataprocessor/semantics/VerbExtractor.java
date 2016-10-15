package com.trainingdataprocessor.semantics;

import java.util.List;

/**
 * Created by Oliver on 10/15/2016.
 */
public interface VerbExtractor {

    String extractVerbAuxiliaryVerbPhrase(List<String> subSentence, List<String> encodedTags, int constantIndex, SemanticRelationConstantType constantType);

    String extractVerbFromModalVerbPhrase(List<String> subSentence, List<String> encodedTags, int constantIndex);

    boolean isPositiveVerb(List<String> subSentence, int constantIndex, SemanticRelationConstantType constantType);

}
