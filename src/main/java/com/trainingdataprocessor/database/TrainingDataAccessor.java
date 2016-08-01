package com.trainingdataprocessor.database;

import com.trainingdataprocessor.data.BigramData;

/**
 * Created by Oliver on 8/1/2016.
 */
public interface TrainingDataAccessor {

    void populateBigramFrequencyData(BigramData bigramData);

    void populateBigramTag1FrequencyData(BigramData bigramData);
}
