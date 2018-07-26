package com.aresPosTaggerModelDataProcessor.writer.tags;

import com.aresPosTaggerModelDataProcessor.data.preprocessing.ModelDataRow;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Oliver on 2/7/2017.
 */
public class TagsWriterImpl implements TagsWriter {

    @Override
    public void write(List<ModelDataRow> modelDataRowList) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\AresPosTaggerModelData\\Tags.txt", true);
            bw = new BufferedWriter(fw);
            for (ModelDataRow modelDataRow : modelDataRowList) {
                for (String tag : modelDataRow.getTagsList()) {
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
