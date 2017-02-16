package com.trainingdataprocessor.semantics.extraction;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.tags.EncodedTags;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Oliver on 10/21/2016.
 */
public class SemanticExtractorImpl implements SemanticExtractor {

    private final static Logger LOGGER = Logger.getLogger(SemanticExtractorImpl.class.getName());


    @Override
    public SemanticExtractionData extract(List<String> tokensList, List<EncodedTags> encodedTagsList, int verbIndex, int beforeVerbIndex, int afterVerbIndex) {
        LOGGER.info("ENTERING extract method of SemanticExtractorImpl....");
        LOGGER.info("*********************************************************************");

        SemanticExtractionData semanticExtractionData = new SemanticExtractionData();

        if (beforeVerbIndex > -1 && beforeVerbIndex < verbIndex) {

        }

//        prepositionPhraseExtractor.extract(semanticPreprocessingData, semanticExtractionData);
//        nounPhraseExtractor.extract(semanticPreprocessingData, semanticExtractionData);
//        verbPhraseExtractor.extract(semanticPreprocessingData, semanticExtractionData);

        LOGGER.info("LEAVING extract method of SemanticExtractorImpl....");
        LOGGER.info("*********************************************************************");

        return semanticExtractionData;
    }


}