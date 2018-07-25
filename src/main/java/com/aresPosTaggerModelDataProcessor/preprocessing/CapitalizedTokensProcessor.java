package com.aresPosTaggerModelDataProcessor.preprocessing;

import com.aresPosTaggerModelDataProcessor.data.preprocessing.TrainingDataRow;

/**
 * Created by Oliver on 11/30/2016.
 */
public interface CapitalizedTokensProcessor {

    void process(TrainingDataRow trainingDataRow);

}
