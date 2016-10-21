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

    public SemanticPreprocessorImpl(PhrasePreprocessor prepositionPhrasePreprocessor, PhrasePreprocessor nounPhrasePreprocessor, PhrasePreprocessor verbPhrasePreprocessor) {
        this.prepositionPhrasePreprocessor = prepositionPhrasePreprocessor;
        this.nounPhrasePreprocessor = nounPhrasePreprocessor;
        this.verbPhrasePreprocessor = verbPhrasePreprocessor;
    }

    public SemanticPreprocessingData preprocess(String sentencePattern, List<String> subSentence, List<String> encodedTags) {

        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();
        semanticPreprocessingData.setVerbIndex(getVerbIndex(encodedTags));
        semanticPreprocessingData.setTokens(subSentence);
        semanticPreprocessingData.setEncodedTags(encodedTags);

        prepositionPhrasePreprocessor.analyse(sentencePattern, semanticPreprocessingData);
        nounPhrasePreprocessor.analyse(sentencePattern, semanticPreprocessingData);
        verbPhrasePreprocessor.analyse(sentencePattern, semanticPreprocessingData);
        return semanticPreprocessingData;
    }

    private int getVerbIndex(List<String> encodedTags) {
        for (int i = 0; i <= encodedTags.size() - 1; i++) {
            if (EncodedTags.VERB.equals(encodedTags.get(i)) || EncodedTags.IS_ARE.equals(encodedTags.get(i))
                    || EncodedTags.VERB_ED.equals(encodedTags.get(i))) {
                return i;
            }
        }
        throw new IllegalStateException("Sentence does not contain verb !");
    }

}