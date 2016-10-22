package com.trainingdataprocessor.semantics.extraction.phrases;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.tokenizing.Tokenizer;

/**
 * Created by Oliver on 10/22/2016.
 */
public class VerbPhraseExtractorImpl implements PhraseExtractor {

    private Tokenizer tokenizer;

    public VerbPhraseExtractorImpl(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    @Override
    public void extract(SemanticPreprocessingData semanticPreprocessingData, SemanticExtractionData semanticExtractionData) {
        int startIndex = semanticPreprocessingData.getVerbPhrase().getStartIndex();
        int endIndex = semanticPreprocessingData.getVerbPhrase().getEndIndex();
        String extendedSubject = tokenizer.convertSubListToString(semanticPreprocessingData.getTokens(), startIndex, endIndex);
        semanticExtractionData.setExtendedVerbPredicate(extendedSubject);
        int verbIndex = semanticPreprocessingData.getVerbIndex();
        semanticExtractionData.setAtomicVerbPredicate(semanticPreprocessingData.getTokens().get(verbIndex));
    }

}
