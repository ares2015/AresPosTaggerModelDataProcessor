package com.trainingdataprocessor.writer.morphology;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.morphology.MorphemesDetector;
import com.trainingdataprocessor.tags.Tags;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Oliver on 3/20/2017.
 */
public class SuffixesWriterImpl implements SuffixesWriter, Runnable {

    private MorphemesDetector morphemesDetector;

    private List<TrainingDataRow> trainingDataRowList;

    private final static Logger LOGGER = Logger.getLogger(SuffixesWriterImpl.class.getName());

    public SuffixesWriterImpl(MorphemesDetector morphemesDetector, List<TrainingDataRow> trainingDataRowList) {
        this.morphemesDetector = morphemesDetector;
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
            fw = new FileWriter("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\Suffixes.txt", true);
            bw = new BufferedWriter(fw);
            for (TrainingDataRow trainingDataRow : trainingDataRowList) {
                List<String> tokensList = trainingDataRow.getTokensList();
                for (int i = 0; i <= tokensList.size() - 1; i++) {
                    String token = tokensList.get(i);
                    String suffix = morphemesDetector.detectSuffix(token);
                    if (!"no suffix".equals(suffix)) {
                        String tag = trainingDataRow.getTagsList().get(i);
                        if (Tags.NOUN.equals(tag) || Tags.ADJECTIVE.equals(tag) || Tags.VERB.equals(tag) ||
                                Tags.VERB_ED.equals(tag) || Tags.VERB_ING.equals(tag) || Tags.ADVERB.equals(tag)) {
                            String trainingDataRowAsString = suffix + "#" + tag + "#" + token;
                            LOGGER.info("Token: " + token + " contains suffix " + suffix + " with tag " + tag);
                            bw.write(trainingDataRowAsString);
                            bw.newLine();
                        }
                    }
                }
            }
            System.out.println("Writing into suffixes file finished");
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
