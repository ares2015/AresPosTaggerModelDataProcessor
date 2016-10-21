package com.trainingdataprocessor.semantics.preprocessing.phrases.noun;

import com.trainingdataprocessor.data.RegexPatternData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.regex.RegexExpressions;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.semantics.preprocessing.phrases.PhrasePreprocessor;

import java.util.List;

/**
 * Created by Oliver on 10/21/2016.
 */
public class NounPhrasePreprocessorImpl implements PhrasePreprocessor {

    private RegexPatternSearcher regexPatternSearcher;

    public NounPhrasePreprocessorImpl(RegexPatternSearcher regexPatternSearcher) {
        this.regexPatternSearcher = regexPatternSearcher;
    }

    @Override
    public void analyse(String sentencePattern, SemanticPreprocessingData semanticPreprocessingData) {
        List<RegexPatternData> regexPatternDataList = regexPatternSearcher.search(sentencePattern, RegexExpressions.NOUN_PHRASE);
        int verbIndex = semanticPreprocessingData.getVerbIndex();
        if (regexPatternDataList.size() > 0) {
            int index = 0;
            for (RegexPatternData regexPatternData : regexPatternDataList) {
                if (regexPatternData.getEndIndex() <= verbIndex) {
                    semanticPreprocessingData.setContainsBeforeVerbNounPhrase(true);
                    semanticPreprocessingData.setBeforeVerbNounPhrase(regexPatternData);
                } else if (regexPatternData.getEndIndex() >= verbIndex) {
                    semanticPreprocessingData.setContainsAfterVerbNounPhrase(true);
                    semanticPreprocessingData.setAfterVerbNounPhrase(regexPatternData);
                }
            }
        }
    }

}
