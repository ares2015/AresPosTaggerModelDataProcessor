package com.trainingdataprocessor.semantics.preprocessing.phrases;

import com.trainingdataprocessor.data.regex.RegexPatternData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.regex.RegexExpressions;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.tags.EncodedTags;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Oliver on 10/21/2016.
 */
public class PrepositionPhrasePreprocessorImpl implements PhrasePreprocessor {

    private final static Logger LOGGER = Logger.getLogger(PrepositionPhrasePreprocessorImpl.class.getName());

    private RegexPatternSearcher regexPatternSearcher;

    public PrepositionPhrasePreprocessorImpl(RegexPatternSearcher regexPatternSearcher) {
        this.regexPatternSearcher = regexPatternSearcher;
    }

    public void preprocess(String sentencePattern, SemanticPreprocessingData semanticPreprocessingData) {
        List<RegexPatternData> regexPatternDataList = regexPatternSearcher.search(sentencePattern, RegexExpressions.PREPOSITION_PHRASE);
        int verbIndex = semanticPreprocessingData.getVerbIndex();
        if (regexPatternDataList.size() > 0) {
            for (RegexPatternData regexPatternData : regexPatternDataList) {
                if (containsBeforeVerbPrepositionPhrase(regexPatternData, verbIndex)) {
                    LOGGER.info("Sentence pattern: " + sentencePattern + " contains beforeVerbPrepositionPhrase: " + regexPatternData.getPattern());
                    semanticPreprocessingData.setContainsBeforeVerbPrepositionPhrase(true);
                    semanticPreprocessingData.setBeforeVerbPrepositionPhrase(regexPatternData);
                } else if (containsAfterVerbPrepositionPhrase(regexPatternData, verbIndex)) {
                    LOGGER.info("Sentence pattern: " + sentencePattern + " contains afterVerbPrepositionPhrase: " + regexPatternData.getPattern());
                    semanticPreprocessingData.setContainsAfterVerbPrepositionPhrase(true);
                    semanticPreprocessingData.setAfterVerbPrepositionPhrase(regexPatternData);
                }
            }
        }
    }

    private boolean containsBeforeVerbPrepositionPhrase(RegexPatternData regexPatternData, int verbIndex) {
        return regexPatternData.getEndIndex() <= verbIndex && (regexPatternData.getPattern().contains(EncodedTags.PREPOSITION) || regexPatternData.getPattern().contains(EncodedTags.TO));
    }

    private boolean containsAfterVerbPrepositionPhrase(RegexPatternData regexPatternData, int verbIndex) {
        return regexPatternData.getEndIndex() >= verbIndex && (regexPatternData.getPattern().contains(EncodedTags.PREPOSITION) || regexPatternData.getPattern().contains(EncodedTags.TO));
    }

}