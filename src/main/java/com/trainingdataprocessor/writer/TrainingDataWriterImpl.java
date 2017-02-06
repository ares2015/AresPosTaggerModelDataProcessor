package com.trainingdataprocessor.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Oliver on 2/6/2017.
 */
public class TrainingDataWriterImpl implements TrainingDataWriter {

    @Override
    public void writeTags(List<String> tagsList) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\Tags.txt", true);
            bw = new BufferedWriter(fw);
            for (String tag : tagsList) {
                bw.write(tag);
                bw.newLine();
            }
            System.out.println("Writing into file finished");
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

    @Override
    public void writeTokenTags(List<String> tokensList, List<String> tagsList) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\TokenTags.txt", true);
            bw = new BufferedWriter(fw);
            for (int i = 0; i < tokensList.size(); i++) {
                String trainingDataRow = tokensList.get(i) + "#" + tagsList.get(i);
                bw.write(trainingDataRow);
                bw.newLine();
            }
            System.out.println("Writing into file finished");
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
