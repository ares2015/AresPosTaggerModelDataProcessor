package com.trainingdataprocessor.semantics.extraction;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.semantics.extraction.phrases.PhraseExtractor;
import com.trainingdataprocessor.semantics.extraction.phrases.PrepositionPhraseExtractorImpl;
import com.trainingdataprocessor.tokenizing.Tokenizer;

/**
 * Created by Oliver on 10/21/2016.
 */
public class SemanticExtractorImpl implements SemanticExtractor {

    private PhraseExtractor prepositionPhraseExtractor;

    private PhraseExtractor nounPhraseExtractor;

    private PhraseExtractor verbPhraseExtractor;

    public SemanticExtractorImpl(PhraseExtractor prepositionPhraseExtractor, PhraseExtractor nounPhraseExtractor, PhraseExtractor verbPhraseExtractor) {
        this.prepositionPhraseExtractor = prepositionPhraseExtractor;
        this.nounPhraseExtractor = nounPhraseExtractor;
        this.verbPhraseExtractor = verbPhraseExtractor;
    }

    @Override
    public SemanticExtractionData extract(SemanticPreprocessingData semanticPreprocessingData) {
        SemanticExtractionData semanticExtractionData = new SemanticExtractionData();

        prepositionPhraseExtractor.extract(semanticPreprocessingData, semanticExtractionData);
        nounPhraseExtractor.extract(semanticPreprocessingData, semanticExtractionData);
        verbPhraseExtractor.extract(semanticPreprocessingData, semanticExtractionData);

        return semanticExtractionData;
    }

}