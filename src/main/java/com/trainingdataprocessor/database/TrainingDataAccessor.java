package com.trainingdataprocessor.database;

import com.trainingdataprocessor.data.BigramData;
import com.trainingdataprocessor.data.StartTagEndTagPair;
import com.trainingdataprocessor.data.semantics.SemanticExtractionData;

/**
 * Created by Oliver on 8/1/2016.
 */
public interface TrainingDataAccessor {

    void insertBigramData(BigramData bigramData);

    void insertStartTagEndTagPair(StartTagEndTagPair startTagEndTagPair);

    void populateBigramFrequencyData(BigramData bigramData);

    void populateBigramTag1FrequencyData(BigramData bigramData);

    void insertSemanticData(SemanticExtractionData semanticExtractionData);

}
