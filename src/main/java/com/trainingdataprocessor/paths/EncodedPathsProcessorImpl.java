package com.trainingdataprocessor.paths;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.writer.TrainingDataWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 11/17/2016.
 */
public class EncodedPathsProcessorImpl implements EncodedPathsProcessor, Runnable {

    private TrainingDataWriter trainingDataWriter;

    private List<TrainingDataRow> trainingDataRowList;

    public EncodedPathsProcessorImpl(TrainingDataWriter trainingDataWriter,
                                     List<TrainingDataRow> trainingDataRowList) {
        this.trainingDataWriter = trainingDataWriter;
        this.trainingDataRowList = trainingDataRowList;
    }

    @Override
    public void run() {
        try {
            process();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process() throws IOException {
        List<String> encodedPathsList = new ArrayList<>();
        for (TrainingDataRow trainingDataRow : trainingDataRowList) {
            if (trainingDataRow.containsSubSentences()) {
                for (String encodedPathAsString : trainingDataRow.getEncodedPathsAsStringList()) {
                    encodedPathsList.add(encodedPathAsString);
                }
            } else {
                encodedPathsList.add(trainingDataRow.getEncodedPathAsString());
            }
        }
        trainingDataWriter.writeEncodedPath(encodedPathsList);
    }

}
