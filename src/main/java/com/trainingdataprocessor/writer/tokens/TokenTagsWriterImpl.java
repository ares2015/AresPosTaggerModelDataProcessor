package com.trainingdataprocessor.writer.tokens;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Oliver on 2/7/2017.
 */
public class TokenTagsWriterImpl implements TokenTagsWriter, Runnable {

    private List<TrainingDataRow> trainingDataRowList;

    public TokenTagsWriterImpl(List<TrainingDataRow> trainingDataRowList) {
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
            fw = new FileWriter("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\TokenTags.txt", true);
            bw = new BufferedWriter(fw);
            List<String> tokensList = null;
            List<String> tagsList = null;
            for (TrainingDataRow trainingDataRow : trainingDataRowList) {
                tokensList = trainingDataRow.getTokensList();
                tagsList = trainingDataRow.getTagsList();
                for (int i = 0; i < tokensList.size(); i++) {
                    String trainingDataRowAsString = tokensList.get(i) + "#" + tagsList.get(i);
                    bw.write(trainingDataRowAsString);
                    bw.newLine();
                }
            }
            System.out.println("Writing into token tags file finished");
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
