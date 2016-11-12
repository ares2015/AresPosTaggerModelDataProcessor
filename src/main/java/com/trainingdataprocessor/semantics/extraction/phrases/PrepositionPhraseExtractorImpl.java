package com.trainingdataprocessor.semantics.extraction.phrases;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.tokens.Tokenizer;

import java.util.logging.Logger;

/**
 * Created by Oliver on 10/21/2016.
 */
public class PrepositionPhraseExtractorImpl implements PhraseExtractor {

    private final static Logger LOGGER = Logger.getLogger(PrepositionPhraseExtractorImpl.class.getName());

    private Tokenizer tokenizer;

    public PrepositionPhraseExtractorImpl(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    @Override
    public void extract(SemanticPreprocessingData semanticPreprocessingData, SemanticExtractionData semanticExtractionData) {
        if(semanticPreprocessingData.containsBeforeVerbPrepositionPhrase()){
            int startIndex = semanticPreprocessingData.getBeforeVerbPrepositionPhrase().getStartIndex();
            int endIndex = semanticPreprocessingData.getBeforeVerbPrepositionPhrase().getEndIndex();
            String extendedSubject = tokenizer.convertSubListToString(semanticPreprocessingData.getTokens(), startIndex, endIndex);
            semanticExtractionData.setExtendedSubject(extendedSubject);
            LOGGER.info("ExtendedSubject found: " + extendedSubject);
        }
        if(semanticPreprocessingData.containsAfterVerbPrepositionPhrase()){
            int startIndex = 0;
            if(semanticPreprocessingData.containsAfterVerbNounPhrase()){
                startIndex = semanticPreprocessingData.getAfterVerbNounPhrase().getStartIndex();
            }else{
                startIndex = semanticPreprocessingData.getAfterVerbPrepositionPhrase().getStartIndex();
            }
            int endIndex = semanticPreprocessingData.getAfterVerbPrepositionPhrase().getEndIndex();
            String extendedNounPredicate = tokenizer.convertSubListToString(semanticPreprocessingData.getTokens(), startIndex, endIndex);
            semanticExtractionData.setExtendedNounPredicate(extendedNounPredicate);
            LOGGER.info("ExtendedNounPredicate found: " + extendedNounPredicate);
        }
    }

}