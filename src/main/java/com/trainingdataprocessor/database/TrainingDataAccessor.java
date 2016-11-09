package com.trainingdataprocessor.database;

import com.trainingdataprocessor.data.BigramData;
import com.trainingdataprocessor.data.semantics.SemanticExtractionData;

/**
 * Created by Oliver on 8/1/2016.
 */
public interface TrainingDataAccessor {

    void insertSemanticData(SemanticExtractionData semanticExtractionData);

    void populateBigramFrequencyData(BigramData bigramData);

    void populateBigramTag1FrequencyData(BigramData bigramData);
}
