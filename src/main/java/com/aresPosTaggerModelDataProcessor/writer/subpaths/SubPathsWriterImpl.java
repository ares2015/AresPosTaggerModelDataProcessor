package com.aresPosTaggerModelDataProcessor.writer.subpaths;

import com.aresPosTaggerModelDataProcessor.data.syntax.SubPathData;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Oliver on 2/7/2017.
 */
public class SubPathsWriterImpl implements SubPathsWriter {

    @Override
    public void write(List<SubPathData> subPathDataList) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\AresPosTaggerModelData\\SubPaths.txt", true);
            bw = new BufferedWriter(fw);
            for (SubPathData subPathData : subPathDataList) {
                if (!subPathData.isConstantSubPath()) {
                    String trainingDataRow = subPathData.getStartTag() + "#" + subPathData.getEndTag() + "#" +
                            subPathData.getSubPath() + "#" + subPathData.getLength() + "#" + subPathData.isConstantSubPath();
                    bw.write(trainingDataRow);
                    bw.newLine();
                }
            }
            System.out.println("Writing into subpaths file finished");
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
