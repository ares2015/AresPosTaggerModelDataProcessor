package com.trainingdataprocessor.writer;

import com.trainingdataprocessor.data.syntax.BigramData;
import com.trainingdataprocessor.data.syntax.SubPathData;

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

    @Override
    public void writeBigrams(List<BigramData> bigramDataList) {
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

    @Override
    public void writeSubPaths(List<SubPathData> subPathDataList) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\SubPaths.txt", true);
            bw = new BufferedWriter(fw);
            for (SubPathData subPathData : subPathDataList) {
                String trainingDataRow = subPathData.getStartTag() + "#" + subPathData.getEndTag() + "#" +
                        subPathData.getSubPath() + "#" + subPathData.getLength() + "#" + subPathData.isConstantSubPath();
                bw.write(trainingDataRow);
                bw.newLine();
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

    @Override
    public void writeEncodedPath(List<String> encodedPathsList) throws IOException {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\EncodedPaths.txt", true);
            bw = new BufferedWriter(fw);
            for (String encodedPath : encodedPathsList) {
                bw.write(encodedPath);
                bw.newLine();
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