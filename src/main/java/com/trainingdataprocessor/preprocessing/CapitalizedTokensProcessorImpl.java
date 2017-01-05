package com.trainingdataprocessor.preprocessing;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.tags.EncodedTags;
import com.trainingdataprocessor.tags.Tags;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 11/30/2016.
 */
public class CapitalizedTokensProcessorImpl implements CapitalizedTokensProcessor {

    @Override
    public void process(TrainingDataRow trainingDataRow) {
        List<String> tokensList = trainingDataRow.getTokensList();
        List<String> tagsList = trainingDataRow.getTagsList();
        List<String> encodedTagsList = trainingDataRow.getEncodedTagsList();

        List<String> processedTokensList = new ArrayList<>();
        List<String> processedTagsList = new ArrayList<>();
        List<String> processedEncodedTagsList = new ArrayList<>();


    }

    private void runCapitalizationLogic(List<String> tokensList, List<String> tagsList, List<String> encodedTagsList,
                                        List<String> processedTokensList, List<String> processedTagsList, List<String> processedEncodedTagsList,
                                        TrainingDataRow trainingDataRow, boolean containsSubSentence, int listIndex) {
        String mergedToken = "";

        outer:
        for (int i = 0; i < tokensList.size(); i++) {
            if (!"".equals(mergedToken)) {
                i = i - 1;
                processedTokensList.add(mergedToken);
                processedTagsList.add(Tags.NOUN);
                processedEncodedTagsList.add(EncodedTags.NOUN);
                mergedToken = "";
            }
            if (Character.isUpperCase(tokensList.get(i).charAt(0))) {
                while (Character.isUpperCase(tokensList.get(i).charAt(0))) {
                    if ("".equals(mergedToken)) {
                        mergedToken = tokensList.get(i);
                    } else {
                        mergedToken += " " + tokensList.get(i);
                    }
                    if (i == tokensList.size() - 1) {
                        processedTokensList.add(mergedToken);
                        processedTagsList.add(Tags.NOUN);
                        processedEncodedTagsList.add(EncodedTags.NOUN);
                        break outer;
                    }
                    i++;
                }
            } else {
                processedTokensList.add(tokensList.get(i));
                processedTagsList.add(tagsList.get(i));
                processedEncodedTagsList.add(encodedTagsList.get(i));
            }
        }
        if (containsSubSentence) {
            trainingDataRow.getTokensMultiList().remove(listIndex);
            trainingDataRow.getTokensMultiList().add(processedTokensList);
            trainingDataRow.getTagsMultiList().remove(listIndex);
            trainingDataRow.getTagsMultiList().add(processedTagsList);
            trainingDataRow.getEncodedTagsMultiList().remove(listIndex);
            trainingDataRow.getEncodedTagsMultiList().add(processedEncodedTagsList);
        } else {
            trainingDataRow.setTokensList(processedTokensList);
            trainingDataRow.setTagsList(processedTagsList);
            trainingDataRow.setEncodedTagsList(processedEncodedTagsList);
        }
    }

}