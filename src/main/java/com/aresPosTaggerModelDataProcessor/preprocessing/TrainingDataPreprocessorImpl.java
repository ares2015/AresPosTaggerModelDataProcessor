package com.aresPosTaggerModelDataProcessor.preprocessing;

import com.aresPosTaggerModelDataProcessor.data.preprocessing.TrainingDataRow;
import com.aresPosTaggerModelDataProcessor.factories.row.TrainingDataRowListFactory;
import com.aresPosTaggerModelDataProcessor.reader.TrainingDataReader;

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
        List<String> trainingDataRowStringList = trainingDataReader.read();
        List<TrainingDataRow> trainingDataRows = trainingDataRowListFactory.create(trainingDataRowStringList);
        for (TrainingDataRow trainingDataRow : trainingDataRows) {
            capitalizedTokensProcessor.process(trainingDataRow);
        }
        return trainingDataRows;
    }
}