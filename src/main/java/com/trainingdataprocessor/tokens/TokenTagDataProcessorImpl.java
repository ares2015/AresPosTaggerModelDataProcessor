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
        tokenTagData.setToken(token);
        Optional<TokenDatabaseData> tokenDatabaseData = trainingDataDatabaseAccessor.getTokenDatabaseData(token);
        if (tokenDatabaseData.isPresent()) {
            int totalFrequency = tokenDatabaseData.get().getTotalFrequency();
            totalFrequency++;
            tokenTagData.setTotalFrequency(totalFrequency);
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
        if (tokenTagData.tokenExistsInDB()) {
            if (Tags.NOUN.equals(tag)) {
                int isNounFrequency = tokenDatabaseData.get().getIsNounFrequency();
                isNounFrequency++;
                tokenTagData.setIsNounFrequency(isNounFrequency);
                tokenTagData.setNoun(true);
            } else {
                tokenTagData.setNoun(tokenDatabaseData.get().isNoun());
                tokenTagData.setIsNounFrequency(tokenDatabaseData.get().getIsNounFrequency());
            }
        } else {
            if (Tags.NOUN.equals(tag)) {
                tokenTagData.setNoun(true);
                tokenTagData.setIsNounFrequency(1);
                tokenTagData.setTotalFrequency(1);
            }
        }
    }

    private void populateIsAdjectiveAttributes(TokenTagData tokenTagData, String tag, Optional<TokenDatabaseData> tokenDatabaseData) {
        if (tokenTagData.tokenExistsInDB()) {
            if (Tags.ADJECTIVE.equals(tag)) {
                int isAdjectiveFrequency = tokenDatabaseData.get().getIsAdjectiveFrequency();
                isAdjectiveFrequency++;
                tokenTagData.setIsAdjectiveFrequency(isAdjectiveFrequency);
                tokenTagData.setAdjective(true);
            } else {
                tokenTagData.setAdjective(tokenDatabaseData.get().isAdjective());
                tokenTagData.setIsAdjectiveFrequency(tokenDatabaseData.get().getIsAdjectiveFrequency());
            }
        } else {
            if (Tags.ADJECTIVE.equals(tag)) {
                tokenTagData.setAdjective(true);
                tokenTagData.setIsAdjectiveFrequency(1);
                tokenTagData.setTotalFrequency(1);
            }
        }
    }

    private void populateIsVerbAttributes(TokenTagData tokenTagData, String tag, Optional<TokenDatabaseData> tokenDatabaseData) {
        if (tokenTagData.tokenExistsInDB()) {
            if (Tags.VERB.equals(tag)) {
                int isVerbFrequency = tokenDatabaseData.get().getIsVerbFrequency();
                isVerbFrequency++;
                tokenTagData.setIsVerbFrequency(isVerbFrequency);
                tokenTagData.setVerb(true);
            } else {
                tokenTagData.setVerb(tokenDatabaseData.get().isVerb());
                tokenTagData.setIsVerbFrequency(tokenDatabaseData.get().getIsVerbFrequency());
            }
        } else {
            if (Tags.VERB.equals(tag)) {
                tokenTagData.setVerb(true);
                tokenTagData.setIsVerbFrequency(1);
                tokenTagData.setTotalFrequency(1);
            }
        }
    }

    private void populateIsVerbEdAttributes(TokenTagData tokenTagData, String tag, Optional<TokenDatabaseData> tokenDatabaseData) {
        if (tokenTagData.tokenExistsInDB()) {
            if (Tags.VERB_ED.equals(tag)) {
                int isVerbEdFrequency = tokenDatabaseData.get().getIsVerbEdFrequency();
                isVerbEdFrequency++;
                tokenTagData.setIsVerbEdFrequency(isVerbEdFrequency);
                tokenTagData.setVerbEd(true);
            } else {
                tokenTagData.setVerbEd(tokenDatabaseData.get().isVerbEd());
                tokenTagData.setIsVerbEdFrequency(tokenDatabaseData.get().getIsVerbEdFrequency());
            }
        } else {
            if (Tags.VERB_ED.equals(tag)) {
                tokenTagData.setVerbEd(true);
                tokenTagData.setIsVerbEdFrequency(1);
                tokenTagData.setTotalFrequency(1);
            }
        }
    }

    private void populateIsVerbIngAttributes(TokenTagData tokenTagData, String tag, Optional<TokenDatabaseData> tokenDatabaseData) {
        if (tokenTagData.tokenExistsInDB()) {
            if (Tags.VERB_ING.equals(tag)) {
                int isVerbIngFrequency = tokenDatabaseData.get().getIsVerbIngFrequency();
                isVerbIngFrequency++;
                tokenTagData.setIsVerbIngFrequency(isVerbIngFrequency);
                tokenTagData.setVerbIng(true);
            } else {
                tokenTagData.setVerbIng(tokenDatabaseData.get().isVerbIng());
                tokenTagData.setIsVerbIngFrequency(tokenDatabaseData.get().getIsVerbIngFrequency());
            }
        } else {
            if (Tags.VERB_ING.equals(tag)) {
                tokenTagData.setVerbIng(true);
                tokenTagData.setIsVerbIngFrequency(1);
                tokenTagData.setTotalFrequency(1);
            }
        }
    }

    private void populateIsAdverbAttributes(TokenTagData tokenTagData, String tag, Optional<TokenDatabaseData> tokenDatabaseData) {
        if (tokenTagData.tokenExistsInDB()) {
            if (Tags.ADVERB.equals(tag)) {
                int isAdverbFrequency = tokenDatabaseData.get().getIsAdverbFrequency();
                isAdverbFrequency++;
                tokenTagData.setIsAdverbFrequency(isAdverbFrequency);
                tokenTagData.setAdverb(true);
            } else {
                tokenTagData.setAdverb(tokenDatabaseData.get().isAdverb());
                tokenTagData.setIsAdverbFrequency(tokenDatabaseData.get().getIsAdverbFrequency());
            }
        } else {
            if (Tags.ADVERB.equals(tag)) {
                tokenTagData.setAdverb(true);
                tokenTagData.setIsAdverbFrequency(1);
                tokenTagData.setTotalFrequency(1);
            }
        }
    }
}