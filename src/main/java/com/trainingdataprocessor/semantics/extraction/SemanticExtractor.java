package com.trainingdataprocessor.semantics.extraction;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;

/**
 * Created by Oliver on 2/17/2017.
 */
public interface SemanticExtractor {

    SemanticExtractionData extract(SemanticPreprocessingData semanticPreprocessingData);

}
