package com.trainingdataprocessor.writer.tags;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Oliver on 2/7/2017.
 */
public class TagsWriterImpl implements TagsWriter {

    @Override
    public void write(List<TrainingDataRow> trainingDataRowList) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\Tags.txt", true);
            bw = new BufferedWriter(fw);
            for (TrainingDataRow trainingDataRow : trainingDataRowList) {
                for (String tag : trainingDataRow.getTagsList()) {
                    bw.write(tag);
                    bw.newLine();
                }
            }
            System.out.println("Writing into tags file finished");
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
