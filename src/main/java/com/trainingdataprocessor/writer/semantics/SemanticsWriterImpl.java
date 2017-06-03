package com.trainingdataprocessor.writer.semantics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by oled on 2/22/2017.
 */
public class SemanticsWriterImpl implements SemanticsWriter {

    @Override
    public void write(List<com.semanticRelationsExtractor.data.SemanticExtractionData> semanticExtractionDataList) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\Semantics.txt", true);
            bw = new BufferedWriter(fw);
            for (com.semanticRelationsExtractor.data.SemanticExtractionData semanticExtractionData : semanticExtractionDataList) {
                String trainingDataRow = semanticExtractionData.getAtomicSubject() + "#" + semanticExtractionData.getExtendedSubject() + "#" +
                        semanticExtractionData.getAtomicVerbPredicate() + "#" + semanticExtractionData.getExtendedVerbPredicate()
                        + "#" + semanticExtractionData.getAtomicNounPredicate() + "#" + semanticExtractionData.getExtendedNounPredicate();
                bw.write(trainingDataRow);
                bw.newLine();
            }
            System.out.println("Writing into semantics file finished");
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
