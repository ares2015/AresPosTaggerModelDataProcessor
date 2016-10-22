package com.trainingdataprocessor.semantics.extraction.phrases.noun;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.semantics.extraction.phrases.PhraseExtractor;
import com.trainingdataprocessor.tokenizing.Tokenizer;

/**
 * Created by Oliver on 10/22/2016.
 */
public class NounPhraseExtractorImpl implements PhraseExtractor {

    private Tokenizer tokenizer;

    public NounPhraseExtractorImpl(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    @Override
    public void extract(SemanticPreprocessingData semanticPreprocessingData, SemanticExtractionData semanticExtractionData) {
        if ((!semanticPreprocessingData.containsBeforeVerbPrepositionPhrase() && semanticPreprocessingData.containsBeforeVerbNounPhrase())) {
            int startIndex = semanticPreprocessingData.getBeforeVerbNounPhrase().getStartIndex();
            int endIndex = semanticPreprocessingData.getBeforeVerbNounPhrase().getEndIndex();
            String extendedSubject = tokenizer.convertSubListToString(semanticPreprocessingData.getTokens(), startIndex, endIndex - 1);
            semanticExtractionData.setExtendedSubject(extendedSubject);
            semanticExtractionData.setAtomicSubject(semanticPreprocessingData.getTokens().get(endIndex - 1));
        }
        if (semanticPreprocessingData.containsAfterVerbNounPhrase() && semanticExtractionData.getExtendedNounPredicate() == null) {
            int startIndex = semanticPreprocessingData.getAfterVerbNounPhrase().getStartIndex();
            int endIndex = semanticPreprocessingData.getAfterVerbNounPhrase().getEndIndex();
            String extendedSubject = tokenizer.convertSubListToString(semanticPreprocessingData.getTokens(), startIndex, endIndex - 1);
            semanticExtractionData.setExtendedNounPredicate(extendedSubject);
            semanticExtractionData.setAtomicNounPredicate(semanticPreprocessingData.getTokens().get(endIndex - 1));
        }else if(semanticPreprocessingData.containsAfterVerbNounPhrase() && semanticExtractionData.getExtendedNounPredicate() != null){
            int endIndex = semanticPreprocessingData.getAfterVerbNounPhrase().getEndIndex();
            semanticExtractionData.setAtomicNounPredicate(semanticPreprocessingData.getTokens().get(endIndex - 1));
        }
    }


}
