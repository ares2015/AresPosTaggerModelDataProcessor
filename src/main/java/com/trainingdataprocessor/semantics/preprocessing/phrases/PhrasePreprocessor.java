package com.trainingdataprocessor.semantics.preprocessing.phrases;

import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;

/**
 * Created by Oliver on 10/21/2016.
 */
public interface PhrasePreprocessor {

    void analyse(String sentencePattern, SemanticPreprocessingData semanticPreprocessingData);
}
