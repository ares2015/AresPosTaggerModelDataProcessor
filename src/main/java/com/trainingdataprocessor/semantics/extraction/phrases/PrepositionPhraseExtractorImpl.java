package com.trainingdataprocessor.semantics.extraction.phrases;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.semantics.extraction.phrases.PhraseExtractor;
import com.trainingdataprocessor.tokenizing.Tokenizer;

/**
 * Created by Oliver on 10/21/2016.
 */
public class PrepositionPhraseExtractorImpl implements PhraseExtractor {

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
        }
    }

}