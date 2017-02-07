package com.trainingdataprocessor.preprocessing;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.factories.row.TrainingDataRowListFactory;
import com.trainingdataprocessor.reader.TrainingDataReader;

import java.util.List;

/**
 * Created by Oliver on 11/12/2016.
 */
public class TrainingDataPreprocessorImpl implements TrainingDataPreprocessor {

    private TrainingDataReader trainingDataReader;

    private TrainingDataRowListFactory trainingDataRowListFactory;

    private CapitalizedTokensProcessor capitalizedTokensProcessor;

    public TrainingDataPreprocessorImpl(TrainingDataReader trainingDataReader, TrainingDataRowListFactory trainingDataRowListFactory,
                                        CapitalizedTokensProcessor capitalizedTokensProcessor) {
        this.trainingDataReader = trainingDataReader;
        this.trainingDataRowListFactory = trainingDataRowListFactory;
        this.capitalizedTokensProcessor = capitalizedTokensProcessor;
    }

    @Override
    public List<TrainingDataRow> preprocess() {
        List<String> testDataRowStringList = trainingDataReader.read();
        List<TrainingDataRow> trainingDataRows = trainingDataRowListFactory.create(testDataRowStringList);
        for (TrainingDataRow trainingDataRow : trainingDataRows) {
            capitalizedTokensProcessor.process(trainingDataRow);
        }
        return trainingDataRows;
    }
}