package com.trainingdataprocessor.semantics.extraction.phrases.preposition;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.semantics.extraction.phrases.PhraseExtractor;
import com.trainingdataprocessor.tokenizing.Tokenizer;

/**
 * Created by Oliver on 10/21/2016.
 */
public class PrepositionPhraseExtractor implements PhraseExtractor {

    private Tokenizer tokenizer;

    public PrepositionPhraseExtractor(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    @Override
    public void extract(SemanticPreprocessingData semanticPreprocessingData, SemanticExtractionData semanticExtractionData) {
        if(semanticPreprocessingData.containsBeforeVerbPrepositionPhrase()){
            int startIndex = semanticPreprocessingData.getBeforeVerbPrepositionPhrase().getStartIndex();
            int endIndex = semanticPreprocessingData.getBeforeVerbPrepositionPhrase().getEndIndex();
            String extendedSubject = tokenizer.convertSubListToString(semanticPreprocessingData.getTokens(), startIndex, endIndex);
            semanticExtractionData.setExtendedSubject(extendedSubject);
        }else if(semanticPreprocessingData.containsAfterVerbPrepositionPhrase()){
            int startIndex = semanticPreprocessingData.getAfterVerbPrepositionPhrase().getStartIndex();
            int endIndex = semanticPreprocessingData.getAfterVerbPrepositionPhrase().getEndIndex();
            String extendedSubject = tokenizer.convertSubListToString(semanticPreprocessingData.getTokens(), startIndex, endIndex);
            semanticExtractionData.setExtendedSubject(extendedSubject);
        }
    }

}