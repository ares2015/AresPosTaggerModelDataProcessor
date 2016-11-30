package com.trainingdataprocessor.preprocessing;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;

import java.util.List;

/**
 * Created by Oliver on 11/30/2016.
 */
public class CapitalizedTokensProcessorImpl implements CapitalizedTokensProcessor {

    @Override
    public void process(TrainingDataRow trainingDataRow) {
        List<String> tokensList = trainingDataRow.getTokensList();
        List<String> tagsList = trainingDataRow.getTagsList();
        int capitalizedSequenceLength = 0;
        for (int i = 0; i < tokensList.size(); i++) {
            if (i > 0 && Character.isUpperCase(tokensList.get(i - 1).charAt(0)) && Character.isUpperCase(tokensList.get(i).charAt(0))) {
                capitalizedSequenceLength++;
                String mergedToken = tokensList.get(i - 1) + "_" + tokensList.get(i);
                tokensList.add(i, mergedToken);
                tokensList.remove(i - 1);
                for (int j = 0; j <= capitalizedSequenceLength; j++) {
                    tokensList.remove(i + j);
                }
                tagsList.remove(i - 1);
            } else if (Character.isUpperCase(tokensList.get(i).charAt(0)) && Character.isUpperCase(tokensList.get(i + 1).charAt(0))) {
                capitalizedSequenceLength++;
                String mergedToken = tokensList.get(i) + "_" + tokensList.get(i + 1);
                tokensList.add(i, mergedToken);
                tokensList.remove(i + 1);
                tokensList.remove(i + 1);
                tagsList.remove(i + 1);
            } else {
                capitalizedSequenceLength = 0;
            }
        }

    }

}