package com.trainingdataprocessor.writer.bigrams;

import com.trainingdataprocessor.data.syntax.BigramData;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Oliver on 2/7/2017.
 */
public class BigramsWriterImpl implements BigramsWriter {

    @Override
    public void write(List<BigramData> bigramDataList) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\Bigrams.txt", true);
            bw = new BufferedWriter(fw);
            for (BigramData bigramData : bigramDataList) {
                String trainingDataRow = bigramData.getTag1() + "#" + bigramData.getTag2();
                bw.write(trainingDataRow);
                bw.newLine();
            }
            System.out.println("Writing into bigrams file finished");
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
