package com.trainingdataprocessor.semantics.preprocessing;

import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.semantics.preprocessing.phrases.PhrasePreprocessor;
import com.trainingdataprocessor.tags.EncodedTags;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Oliver on 10/17/2016.
 */
public class SemanticPreprocessorImpl implements SemanticPreprocessor {

    private final static Logger LOGGER = Logger.getLogger(SemanticPreprocessorImpl.class.getName());

    private SemanticPreprocessingFilter semanticPreprocessingFilter;

    private PhrasePreprocessor prepositionPhrasePreprocessor;

    private PhrasePreprocessor nounPhrasePreprocessor;

    private PhrasePreprocessor verbPhrasePreprocessor;

    public SemanticPreprocessorImpl(SemanticPreprocessingFilter semanticPreprocessingFilter, PhrasePreprocessor prepositionPhrasePreprocessor,
                                    PhrasePreprocessor nounPhrasePreprocessor, PhrasePreprocessor verbPhrasePreprocessor) {
        this.semanticPreprocessingFilter = semanticPreprocessingFilter;
        this.prepositionPhrasePreprocessor = prepositionPhrasePreprocessor;
        this.nounPhrasePreprocessor = nounPhrasePreprocessor;
        this.verbPhrasePreprocessor = verbPhrasePreprocessor;
    }

    public SemanticPreprocessingData preprocess(String encodedSubPath, List<String> tokensList, List<String> encodedTagsList, int verbIndex) {
        LOGGER.info("ENTERING preprocess method of SemanticPreprocessorImpl....");
        LOGGER.info("*********************************************************************");

        String filteredEncodedSubpath = semanticPreprocessingFilter.filterToString(encodedTagsList);
        List<String> filteredTokensList = semanticPreprocessingFilter.filterToList(tokensList);
        List<String> filteredEncodedTagsList = semanticPreprocessingFilter.filterToList(encodedTagsList);

        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();
        semanticPreprocessingData.setVerbIndex(verbIndex);
        LOGGER.info("Verb index in: " + encodedSubPath + " is on index: " + verbIndex);

        int afterVerbPrepositionIndex = getAfterVerbPrepositionIndex(filteredEncodedTagsList, verbIndex);
        if (afterVerbPrepositionIndex > -1) {
            semanticPreprocessingData.setContainsAfterVerbPreposition(true);
        }

        semanticPreprocessingData.setAfterVerbFirstPrepositionIndex(afterVerbPrepositionIndex);
        semanticPreprocessingData.setTokens(filteredTokensList);
        semanticPreprocessingData.setEncodedTags(filteredEncodedTagsList);

        prepositionPhrasePreprocessor.preprocess(filteredEncodedSubpath, semanticPreprocessingData);
        nounPhrasePreprocessor.preprocess(filteredEncodedSubpath, semanticPreprocessingData);
        verbPhrasePreprocessor.preprocess(filteredEncodedSubpath, semanticPreprocessingData);

        LOGGER.info("LEAVING preprocess method of SemanticPreprocessorImpl....");
        LOGGER.info("*********************************************************************");

        return semanticPreprocessingData;
    }

    private int getAfterVerbPrepositionIndex(List<String> encodedTags, int verbIndex) {
        for (int i = 0; i <= encodedTags.size() - 1; i++) {
            if (EncodedTags.PREPOSITION.equals(encodedTags.get(i)) && i > verbIndex) {
                return i;
            }
        }
        return -1;
    }

}