package com.trainingdataprocessor.semantics.extraction;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.semantics.extraction.phrases.PhraseExtractor;

import java.util.logging.Logger;

/**
 * Created by Oliver on 10/21/2016.
 */
public class SemanticExtractorImpl implements SemanticExtractor {

    private final static Logger LOGGER = Logger.getLogger(SemanticExtractorImpl.class.getName());

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
        LOGGER.info("ENTERING extract method of SemanticExtractorImpl....");
        LOGGER.info("*********************************************************************");

        SemanticExtractionData semanticExtractionData = new SemanticExtractionData();

        prepositionPhraseExtractor.extract(semanticPreprocessingData, semanticExtractionData);
        nounPhraseExtractor.extract(semanticPreprocessingData, semanticExtractionData);
        verbPhraseExtractor.extract(semanticPreprocessingData, semanticExtractionData);

        LOGGER.info("LEAVING extract method of SemanticExtractorImpl....");
        LOGGER.info("*********************************************************************");

        return semanticExtractionData;
    }

}