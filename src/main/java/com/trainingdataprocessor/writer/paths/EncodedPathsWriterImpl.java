package com.trainingdataprocessor.writer.paths;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Oliver on 2/7/2017.
 */
public class EncodedPathsWriterImpl implements EncodedPathsWriter, Runnable {

    private List<TrainingDataRow> trainingDataRowList;

    public EncodedPathsWriterImpl(List<TrainingDataRow> trainingDataRowList) {
        this.trainingDataRowList = trainingDataRowList;
    }

    @Override
    public void run() {
        write();
    }

    @Override
    public void write() {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\EncodedPaths.txt", true);
            bw = new BufferedWriter(fw);
            for (TrainingDataRow trainingDataRow : trainingDataRowList) {
                if (trainingDataRow.containsSubSentences()) {
                    for (String encodedPathAsString : trainingDataRow.getEncodedPathsAsStringList()) {
                        bw.write(encodedPathAsString);
                        bw.newLine();
                    }
                } else {
                    bw.write(trainingDataRow.getEncodedPathAsString());
                    bw.newLine();
                }
            }
            System.out.println("Writing into encoded paths file finished");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}