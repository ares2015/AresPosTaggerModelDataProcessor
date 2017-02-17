package com.trainingdataprocessor.semantics.extraction;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;

/**
 * Created by Oliver on 2/16/2017.
 */
public interface SubjectExtractor {

    void extract(SemanticExtractionData semanticExtractionData, SemanticPreprocessingData semanticPreprocessingData);

}
