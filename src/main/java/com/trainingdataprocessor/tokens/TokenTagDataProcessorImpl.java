package com.trainingdataprocessor.tokens;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.data.token.TokenTagData;
import com.trainingdataprocessor.database.TrainingDataDatabaseAccessor;

import java.util.List;

/**
 * Created by Oliver on 11/12/2016.
 */
public class TokenTagDataProcessorImpl implements TokenTagDataProcessor, Runnable {

    private TrainingDataDatabaseAccessor trainingDataDatabaseAccessor;

    private List<TrainingDataRow> trainingDataRowList;

    public TokenTagDataProcessorImpl(TrainingDataDatabaseAccessor trainingDataDatabaseAccessor, List<TrainingDataRow> trainingDataRowList) {
        this.trainingDataDatabaseAccessor = trainingDataDatabaseAccessor;
        this.trainingDataRowList = trainingDataRowList;
    }

    @Override
    public void run() {
        process();
    }

    @Override
    public void process() {
        for (TrainingDataRow trainingDataRow : trainingDataRowList) {
            List<String> tokensList = trainingDataRow.getTokensList();
            List<String> tagsList = trainingDataRow.getTagsList();
            for (int i = 0; i < tokensList.size(); i++) {
                String token = tokensList.get(i);
                String tag = tagsList.get(i);
                TokenTagData tokenTagData = new TokenTagData();
                trainingDataDatabaseAccessor.insertTokenTagData(tokenTagData);
            }
        }
    }

}
