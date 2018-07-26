package com.aresPosTaggerModelDataProcessor.writer.tokens;

import com.aresPosTaggerModelDataProcessor.data.preprocessing.ModelDataRow;
import com.aresPosTaggerModelDataProcessor.tags.Tags;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Oliver on 2/7/2017.
 */
public class TokenTagsWriterImpl implements TokenTagsWriter, Runnable {

    private List<ModelDataRow> modelDataRowList;

    public TokenTagsWriterImpl(List<ModelDataRow> modelDataRowList) {
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
            fw = new FileWriter("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\AresPosTaggerModelData\\TokenTags.txt", true);
            bw = new BufferedWriter(fw);
            List<String> tokensList = null;
            List<String> tagsList = null;
            for (ModelDataRow modelDataRow : modelDataRowList) {
                tokensList = modelDataRow.getTokensList();
                tagsList = modelDataRow.getTagsList();
                for (int i = 0; i < tokensList.size(); i++) {
                    String token = tokensList.get(i);
                    if (!Character.isUpperCase(token.charAt(0))) {
                        String tag = tagsList.get(i);
                        if (Tags.NOUN.equals(tag) || Tags.ADJECTIVE.equals(tag) || Tags.VERB.equals(tag) || Tags.VERB_ED.equals(tag)
                                || Tags.VERB_ING.equals(tag) || Tags.ADVERB.equals(tag)) {

                            String trainingDataRowAsString = token + "#" + tag;
                            bw.write(trainingDataRowAsString);
                            bw.newLine();
                        }
                    }
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
