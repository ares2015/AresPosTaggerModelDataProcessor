package com.trainingdataprocessor.semantics.preprocessing;

import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.semantics.preprocessing.phrases.PhrasePreprocessor;
import com.trainingdataprocessor.tags.EncodedTags;

import java.util.List;

/**
 * Created by Oliver on 10/17/2016.
 */
public class SemanticPreprocessorImpl implements SemanticPreprocessor {

    private PhrasePreprocessor prepositionPhrasePreprocessor;

    private PhrasePreprocessor nounPhrasePreprocessor;

    private PhrasePreprocessor verbPhrasePreprocessor;

    public SemanticPreprocessorImpl(PhrasePreprocessor prepositionPhrasePreprocessor, PhrasePreprocessor nounPhrasePreprocessor,
                                    PhrasePreprocessor verbPhrasePreprocessor) {
        this.prepositionPhrasePreprocessor = prepositionPhrasePreprocessor;
        this.nounPhrasePreprocessor = nounPhrasePreprocessor;
        this.verbPhrasePreprocessor = verbPhrasePreprocessor;
    }

    public SemanticPreprocessingData preprocess(String encodedSubPath, List<String> tokensList, List<String> encodedTagsList) {

        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();
        int verbIndex = getVerbIndex(encodedTagsList);
        semanticPreprocessingData.setVerbIndex(verbIndex);

        int afterVerbPrepositionIndex = getAfterVerbPrepositionIndex(encodedTagsList, verbIndex);
        if (afterVerbPrepositionIndex == -1) {
            semanticPreprocessingData.setContainsAfterVerbPreposition(false);
        }
        semanticPreprocessingData.setAfterVerbFirstPrepositionIndex(afterVerbPrepositionIndex);
        semanticPreprocessingData.setTokens(tokensList);
        semanticPreprocessingData.setEncodedTags(encodedTagsList);

        prepositionPhrasePreprocessor.preprocess(encodedSubPath, semanticPreprocessingData);
        nounPhrasePreprocessor.preprocess(encodedSubPath, semanticPreprocessingData);
        verbPhrasePreprocessor.preprocess(encodedSubPath, semanticPreprocessingData);
        return semanticPreprocessingData;
    }

    private int getVerbIndex(List<String> encodedTags) {
        for (int i = 0; i <= encodedTags.size() - 1; i++) {
            if (EncodedTags.VERB.equals(encodedTags.get(i)) || EncodedTags.IS_ARE.equals(encodedTags.get(i))) {
                return i;
            }
        }
        throw new IllegalStateException("Sentence does not contain verb !");
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