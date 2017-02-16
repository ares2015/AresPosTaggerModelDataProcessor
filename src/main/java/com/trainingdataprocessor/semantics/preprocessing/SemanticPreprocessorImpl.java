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

    private PhrasePreprocessor prepositionPhrasePreprocessor;

    private PhrasePreprocessor nounPhrasePreprocessor;

    private PhrasePreprocessor verbPhrasePreprocessor;

    public SemanticPreprocessorImpl(PhrasePreprocessor prepositionPhrasePreprocessor,
                                    PhrasePreprocessor nounPhrasePreprocessor, PhrasePreprocessor verbPhrasePreprocessor) {
        this.prepositionPhrasePreprocessor = prepositionPhrasePreprocessor;
        this.nounPhrasePreprocessor = nounPhrasePreprocessor;
        this.verbPhrasePreprocessor = verbPhrasePreprocessor;
    }

    public SemanticPreprocessingData preprocess(String filteredEncodedSubPath, List<String> tokensList, List<String> encodedTagsList, int verbIndex) {
        LOGGER.info("ENTERING preprocess method of SemanticPreprocessorImpl....");
        LOGGER.info("*********************************************************************");


        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();
        semanticPreprocessingData.setVerbIndex(verbIndex);
        LOGGER.info("Verb index in: " + filteredEncodedSubPath + " is on index: " + verbIndex);

        int afterVerbPrepositionIndex = getAfterVerbPrepositionIndex(encodedTagsList, verbIndex);
        semanticPreprocessingData.setContainsAfterVerbPreposition(afterVerbPrepositionIndex > -1);

        semanticPreprocessingData.setAfterVerbFirstPrepositionIndex(afterVerbPrepositionIndex);
        semanticPreprocessingData.setTokens(tokensList);
        semanticPreprocessingData.setEncodedTags(encodedTagsList);

        prepositionPhrasePreprocessor.preprocess(filteredEncodedSubPath, semanticPreprocessingData);
        nounPhrasePreprocessor.preprocess(filteredEncodedSubPath, semanticPreprocessingData);
        verbPhrasePreprocessor.preprocess(filteredEncodedSubPath, semanticPreprocessingData);

        LOGGER.info("LEAVING preprocess method of SemanticPreprocessorImpl....");
        LOGGER.info("*********************************************************************");

        return semanticPreprocessingData;
    }

    private int getAfterVerbPrepositionIndex(List<String> encodedTags, int verbIndex) {
        for (int i = 0; i <= encodedTags.size() - 1; i++) {
            if ((EncodedTags.PREPOSITION.equals(encodedTags.get(i)) || EncodedTags.TO.equals(encodedTags.get(i))) && i > verbIndex) {
                return i;
            }
        }
        return -1;
    }

}