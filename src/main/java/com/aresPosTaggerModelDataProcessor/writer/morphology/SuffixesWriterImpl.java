package com.aresPosTaggerModelDataProcessor.writer.morphology;

import com.aresPosTaggerModelDataProcessor.data.preprocessing.ModelDataRow;
import com.aresPosTaggerModelDataProcessor.morphology.MorphemesDetector;
import com.aresPosTaggerModelDataProcessor.tags.Tags;

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

    private List<ModelDataRow> modelDataRowList;

    private final static Logger LOGGER = Logger.getLogger(SuffixesWriterImpl.class.getName());

    public SuffixesWriterImpl(MorphemesDetector morphemesDetector, List<ModelDataRow> modelDataRowList) {
        this.morphemesDetector = morphemesDetector;
        this.modelDataRowList = modelDataRowList;
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
            fw = new FileWriter("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\AresPosTaggerModelData\\Suffixes.txt", true);
            bw = new BufferedWriter(fw);
            for (ModelDataRow modelDataRow : modelDataRowList) {
                List<String> tokensList = modelDataRow.getTokensList();
                for (int i = 0; i <= tokensList.size() - 1; i++) {
                    String token = tokensList.get(i);
                    String suffix = morphemesDetector.detectSuffix(token);
                    if (!"no suffix".equals(suffix)) {
                        String tag = modelDataRow.getTagsList().get(i);
                        if (Tags.NOUN.equals(tag) || Tags.ADJECTIVE.equals(tag) || Tags.VERB.equals(tag) ||
                                Tags.VERB_ED.equals(tag) || Tags.VERB_ING.equals(tag) || Tags.ADVERB.equals(tag)) {
                            String modelDataRowAsString = suffix + "#" + tag + "#" + token;
                            LOGGER.info("Token: " + token + " contains suffix " + suffix + " with tag " + tag);
                            bw.write(modelDataRowAsString);
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
