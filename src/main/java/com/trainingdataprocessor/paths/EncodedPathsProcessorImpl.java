package com.trainingdataprocessor.paths;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.database.TrainingDataDatabaseAccessor;
import com.trainingdataprocessor.tokens.Tokenizer;

import java.util.List;

/**
 * Created by Oliver on 11/17/2016.
 */
public class EncodedPathsProcessorImpl implements EncodedPathsProcessor, Runnable {

    private Tokenizer tokenizer;

    private TrainingDataDatabaseAccessor trainingDataDatabaseAccessor;

    private List<TrainingDataRow> trainingDataRowList;

    public EncodedPathsProcessorImpl(Tokenizer tokenizer, TrainingDataDatabaseAccessor trainingDataDatabaseAccessor,
                                     List<TrainingDataRow> trainingDataRowList) {
        this.tokenizer = tokenizer;
        this.trainingDataDatabaseAccessor = trainingDataDatabaseAccessor;
        this.trainingDataRowList = trainingDataRowList;
    }

    @Override
    public void run() {
        process();
    }

    @Override
    public void process() {
        for (TrainingDataRow trainingDataRow : trainingDataRowList) {
            if (trainingDataRow.containsSubSentences()) {
                for (String encodedPathAsString : trainingDataRow.getEncodedPathsAsStringList()) {
                    trainingDataDatabaseAccessor.insertEncodedPath(encodedPathAsString);
                }
            } else {
                trainingDataDatabaseAccessor.insertEncodedPath(trainingDataRow.getEncodedPathAsString());
            }
        }
    }

}
