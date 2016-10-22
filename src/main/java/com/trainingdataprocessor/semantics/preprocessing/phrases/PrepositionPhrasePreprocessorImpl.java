package com.trainingdataprocessor.semantics.preprocessing.phrases;

import com.trainingdataprocessor.data.RegexPatternData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.regex.RegexExpressions;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.semantics.preprocessing.phrases.PhrasePreprocessor;
import com.trainingdataprocessor.tags.EncodedTags;

import java.util.List;

/**
 * Created by Oliver on 10/21/2016.
 */
public class PrepositionPhrasePreprocessorImpl implements PhrasePreprocessor {

    private RegexPatternSearcher regexPatternSearcher;

    public PrepositionPhrasePreprocessorImpl(RegexPatternSearcher regexPatternSearcher) {
        this.regexPatternSearcher = regexPatternSearcher;
    }

    public void preprocess(String sentencePattern, SemanticPreprocessingData semanticPreprocessingData) {
        List<RegexPatternData> regexPatternDataList = regexPatternSearcher.search(sentencePattern, RegexExpressions.PREPOSITION_PHRASE);
        int verbIndex = semanticPreprocessingData.getVerbIndex();
        if (regexPatternDataList.size() > 0) {
            for (RegexPatternData regexPatternData : regexPatternDataList) {
                if (regexPatternData.getEndIndex() <= verbIndex && regexPatternData.getPattern().contains(EncodedTags.PREPOSITION)) {
                    semanticPreprocessingData.setContainsBeforeVerbPrepositionPhrase(true);
                    semanticPreprocessingData.setBeforeVerbPrepositionPhrase(regexPatternData);
                } else if (regexPatternData.getEndIndex() >= verbIndex && regexPatternData.getPattern().contains(EncodedTags.PREPOSITION)) {
                    semanticPreprocessingData.setContainsAfterVerbPrepositionPhrase(true);
                    semanticPreprocessingData.setAfterVerbPrepositionPhrase(regexPatternData);
                }
            }
        }
    }

}
