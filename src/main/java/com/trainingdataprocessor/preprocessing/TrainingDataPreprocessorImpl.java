package com.trainingdataprocessor.preprocessing;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.factories.TrainingDataRowListFactory;
import com.trainingdataprocessor.reader.TestDataReader;

import java.util.List;

/**
 * Created by Oliver on 11/12/2016.
 */
public class TrainingDataPreprocessorImpl implements TrainingDataPreprocessor {

    private TestDataReader testDataReader;

    private TrainingDataRowListFactory trainingDataRowListFactory;

    private CapitalizedTokensProcessor capitalizedTokensProcessor;

    public TrainingDataPreprocessorImpl(TestDataReader testDataReader, TrainingDataRowListFactory trainingDataRowListFactory,
                                        CapitalizedTokensProcessor capitalizedTokensProcessor) {
        this.testDataReader = testDataReader;
        this.trainingDataRowListFactory = trainingDataRowListFactory;
        this.capitalizedTokensProcessor = capitalizedTokensProcessor;
    }

    @Override
    public List<TrainingDataRow> preprocess() {
        List<String> testDataRowStringList = testDataReader.read();
        List<TrainingDataRow> trainingDataRows = trainingDataRowListFactory.create(testDataRowStringList);
        for (TrainingDataRow trainingDataRow : trainingDataRows) {
            capitalizedTokensProcessor.process(trainingDataRow);
        }
        return trainingDataRows;
    }
}