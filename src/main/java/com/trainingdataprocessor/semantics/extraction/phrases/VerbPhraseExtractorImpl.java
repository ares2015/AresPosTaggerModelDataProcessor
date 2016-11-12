package com.trainingdataprocessor.semantics.extraction.phrases;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.tokenizing.Tokenizer;

import java.util.logging.Logger;

/**
 * Created by Oliver on 10/22/2016.
 */
public class VerbPhraseExtractorImpl implements PhraseExtractor {

    private final static Logger LOGGER = Logger.getLogger(VerbPhraseExtractorImpl.class.getName());

    private Tokenizer tokenizer;

    public VerbPhraseExtractorImpl(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    @Override
    public void extract(SemanticPreprocessingData semanticPreprocessingData, SemanticExtractionData semanticExtractionData) {
        int startIndex = semanticPreprocessingData.getVerbPhrase().getStartIndex();
        int endIndex = semanticPreprocessingData.getVerbPhrase().getEndIndex();
        if (containsExtendedVerbPredicate(endIndex, startIndex)) {
            String extendedVerbPredicate = tokenizer.convertSubListToString(semanticPreprocessingData.getTokens(), startIndex, endIndex);
            semanticExtractionData.setExtendedVerbPredicate(extendedVerbPredicate);
            LOGGER.info("Found extendedVerbPredicate: " + extendedVerbPredicate);
        }
        int verbIndex = semanticPreprocessingData.getVerbIndex();
        String atomicVerbPredicate = semanticPreprocessingData.getTokens().get(verbIndex);
        semanticExtractionData.setAtomicVerbPredicate(atomicVerbPredicate);
        LOGGER.info("Found atomicVerbPredicate: " + atomicVerbPredicate);
    }

    private boolean containsExtendedVerbPredicate(int endIndex, int startIndex) {
        return endIndex - startIndex > 1;
    }

}
