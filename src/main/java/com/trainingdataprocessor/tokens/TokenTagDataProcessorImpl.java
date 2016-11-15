package com.trainingdataprocessor.tokens;

import com.trainingdataprocessor.cache.DatabaseTagsCache;
import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.data.token.TokenDatabaseData;
import com.trainingdataprocessor.data.token.TokenTagData;
import com.trainingdataprocessor.database.TrainingDataDatabaseAccessor;
import com.trainingdataprocessor.tags.Tags;

import java.util.List;
import java.util.Optional;

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
                String tag = tagsList.get(i);
                if (DatabaseTagsCache.databaseTagsCache.contains(tag)) {
                    TokenTagData tokenTagData = new TokenTagData();
                    String token = tokensList.get(i);
                    populateTokenTagDataAttributes(tokenTagData, tag, token);
                    trainingDataDatabaseAccessor.insertTokenTagData(tokenTagData);
                }
            }
        }
    }

    private void populateTokenTagDataAttributes(TokenTagData tokenTagData, String tag, String token) {
        Optional<TokenDatabaseData> tokenDatabaseData = trainingDataDatabaseAccessor.getTokenDatabaseData(token);
        if (tokenDatabaseData.isPresent()) {
            tokenTagData.setTotalFrequency(tokenDatabaseData.get().getTotalFrequency());
            tokenTagData.setTokenExistsInDB(true);
        }
        populateIsNounAttributes(tokenTagData, tag, tokenDatabaseData);
        populateIsAdjectiveAttributes(tokenTagData, tag, tokenDatabaseData);
        populateIsVerbAttributes(tokenTagData, tag, tokenDatabaseData);
        populateIsVerbEdAttributes(tokenTagData, tag, tokenDatabaseData);
        populateIsVerbIngAttributes(tokenTagData, tag, tokenDatabaseData);
        populateIsAdverbAttributes(tokenTagData, tag, tokenDatabaseData);
    }

    private void populateIsNounAttributes(TokenTagData tokenTagData, String tag, Optional<TokenDatabaseData> tokenDatabaseData) {
        if (tokenDatabaseData.isPresent()) {
            int isNounFrequency = tokenDatabaseData.get().getIsNounFrequency();
            if (Tags.NOUN.equals(tag)) {
                isNounFrequency++;
                tokenTagData.setIsNounFrequency(isNounFrequency);
                tokenTagData.setNoun(true);
            }else {
                tokenTagData.setNoun(tokenDatabaseData.get().isNoun());
                tokenTagData.setIsNounFrequency(tokenDatabaseData.get().getIsNounFrequency());
            }

        } else {
            tokenTagData.setIsNounFrequency(1);
        }
    }

    private void populateIsAdjectiveAttributes(TokenTagData tokenTagData, String tag, Optional<TokenDatabaseData> tokenDatabaseData) {
        if (tokenDatabaseData.isPresent()) {
            int isAdjectiveFrequency = tokenDatabaseData.get().getIsAdjectiveFrequency();
            if (Tags.ADJECTIVE.equals(tag)) {
                isAdjectiveFrequency++;
                tokenTagData.setAdjective(true);
            }
            tokenTagData.setIsAdjectiveFrequency(isAdjectiveFrequency);
        } else {
            tokenTagData.setIsAdjectiveFrequency(1);
        }
    }

    private void populateIsVerbAttributes(TokenTagData tokenTagData, String tag, Optional<TokenDatabaseData> tokenDatabaseData) {
        if (tokenDatabaseData.isPresent()) {
            int isVerbFrequency = tokenDatabaseData.get().getIsVerbFrequency();
            if (Tags.VERB.equals(tag)) {
                isVerbFrequency++;
                tokenTagData.setVerb(true);
            }
            tokenTagData.setIsVerbFrequency(isVerbFrequency);
        } else {
            tokenTagData.setIsVerbFrequency(1);
        }
    }

    private void populateIsVerbEdAttributes(TokenTagData tokenTagData, String tag, Optional<TokenDatabaseData> tokenDatabaseData) {
        if (tokenDatabaseData.isPresent()) {
            int isVerbEdFrequency = tokenDatabaseData.get().getIsVerbEdFrequency();
            if (Tags.VERB_ED.equals(tag)) {
                isVerbEdFrequency++;
                tokenTagData.setVerbEd(true);
            }
            tokenTagData.setIsVerbEdFrequency(isVerbEdFrequency);
        } else {
            tokenTagData.setIsVerbEdFrequency(1);
        }
    }

    private void populateIsVerbIngAttributes(TokenTagData tokenTagData, String tag, Optional<TokenDatabaseData> tokenDatabaseData) {
        if (tokenDatabaseData.isPresent()) {
            int isVerbIngFrequency = tokenDatabaseData.get().getIsVerbIngFrequency();
            if (Tags.VERB_ING.equals(tag)) {
                isVerbIngFrequency++;
                tokenTagData.setVerbIng(true);
            }
            tokenTagData.setIsVerbIngFrequency(isVerbIngFrequency);
        } else {
            tokenTagData.setIsVerbIngFrequency(1);
        }
    }

    private void populateIsAdverbAttributes(TokenTagData tokenTagData, String tag, Optional<TokenDatabaseData> tokenDatabaseData) {
        if (tokenDatabaseData.isPresent()) {
            int isAdverbFrequency = tokenDatabaseData.get().getIsAdverbFrequency();
            if (Tags.ADVERB.equals(tag)) {
                isAdverbFrequency++;
                tokenTagData.setAdverb(true);
            }
            tokenTagData.setIsAdverbFrequency(isAdverbFrequency);
        } else {
            tokenTagData.setIsAdverbFrequency(1);
        }
    }


}
