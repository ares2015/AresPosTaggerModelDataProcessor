package com.trainingdataprocessor.semantics.extraction.phrases;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;

/**
 * Created by Oliver on 10/21/2016.
 */
public interface PhraseExtractor {

    void extract(SemanticPreprocessingData semanticPreprocessingData, SemanticExtractionData semanticExtractionData);
}
