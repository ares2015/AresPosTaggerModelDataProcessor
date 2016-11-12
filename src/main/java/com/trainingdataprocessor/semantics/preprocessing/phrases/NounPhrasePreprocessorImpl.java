package com.trainingdataprocessor.semantics.preprocessing.phrases;

import com.trainingdataprocessor.data.regex.RegexPatternData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.regex.RegexExpressions;
import com.trainingdataprocessor.regex.RegexPatternSearcher;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Oliver on 10/21/2016.
 */
public class NounPhrasePreprocessorImpl implements PhrasePreprocessor {

    private final static Logger LOGGER = Logger.getLogger(NounPhrasePreprocessorImpl.class.getName());

    private RegexPatternSearcher regexPatternSearcher;

    public NounPhrasePreprocessorImpl(RegexPatternSearcher regexPatternSearcher) {
        this.regexPatternSearcher = regexPatternSearcher;
    }

    @Override
    public void preprocess(String sentencePattern, SemanticPreprocessingData semanticPreprocessingData) {
        List<RegexPatternData> regexPatternDataList = regexPatternSearcher.search(sentencePattern, RegexExpressions.NOUN_PHRASE);
        int verbIndex = semanticPreprocessingData.getVerbIndex();
        if (regexPatternDataList.size() > 0) {
            for (RegexPatternData regexPatternData : regexPatternDataList) {
                if (containsBeforeVerbNounPhraseWithoutPrepositionPhrase(regexPatternData, verbIndex, semanticPreprocessingData)) {
                    LOGGER.info("Sentence pattern: " + sentencePattern + " contains beforeVerbNounPhrase: " + regexPatternData.getPattern());
                    semanticPreprocessingData.setContainsBeforeVerbNounPhrase(true);
                    semanticPreprocessingData.setBeforeVerbNounPhrase(regexPatternData);
                } else if (containsAfterVerbNounPhraseWithoutPrepositionPhrase(regexPatternData, verbIndex, semanticPreprocessingData)) {
                    LOGGER.info("Sentence pattern: " + sentencePattern + " contains afterVerbNounPhrase: " + regexPatternData.getPattern());
                    semanticPreprocessingData.setContainsAfterVerbNounPhrase(true);
                    semanticPreprocessingData.setAfterVerbNounPhrase(regexPatternData);
                } else if (containsAfterVerbNounPhraseWithPrepositionPhrase(regexPatternData, verbIndex, semanticPreprocessingData)) {
                    LOGGER.info("Sentence pattern: " + sentencePattern + " contains afterVerbNounPhrase: " + regexPatternData.getPattern());
                    semanticPreprocessingData.setContainsAfterVerbNounPhrase(true);
                    semanticPreprocessingData.setAfterVerbNounPhrase(regexPatternData);
                }
            }
        }
    }

    private boolean containsBeforeVerbNounPhraseWithoutPrepositionPhrase(RegexPatternData regexPatternData, int verbIndex,
                                                                         SemanticPreprocessingData semanticPreprocessingData) {
        return regexPatternData.getEndIndex() <= verbIndex && (!semanticPreprocessingData.containsBeforeVerbPrepositionPhrase());
    }

    private boolean containsAfterVerbNounPhraseWithoutPrepositionPhrase(RegexPatternData regexPatternData, int verbIndex,
                                                                        SemanticPreprocessingData semanticPreprocessingData) {
        return regexPatternData.getEndIndex() >= verbIndex && (!semanticPreprocessingData.containsAfterVerbPreposition());
    }

    private boolean containsAfterVerbNounPhraseWithPrepositionPhrase(RegexPatternData regexPatternData, int verbIndex,
                                                                     SemanticPreprocessingData semanticPreprocessingData) {
        return regexPatternData.getEndIndex() >= verbIndex && semanticPreprocessingData.containsAfterVerbPreposition() &&
                regexPatternData.getEndIndex() <= semanticPreprocessingData.getAfterVerbFirstPrepositionIndex();
    }

}