package com.trainingdataprocessor.database;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;

/**
 * Created by Oliver on 8/1/2016.
 */
public interface TrainingDataDatabaseAccessor {

    void insertSemanticData(SemanticExtractionData semanticExtractionData);

}
