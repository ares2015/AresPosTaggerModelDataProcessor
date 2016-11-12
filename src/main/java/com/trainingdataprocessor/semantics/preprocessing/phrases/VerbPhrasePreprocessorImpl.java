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
public class VerbPhrasePreprocessorImpl implements PhrasePreprocessor {

    private final static Logger LOGGER = Logger.getLogger(VerbPhrasePreprocessorImpl.class.getName());

    private RegexPatternSearcher regexPatternSearcher;

    public VerbPhrasePreprocessorImpl(RegexPatternSearcher regexPatternSearcher) {
        this.regexPatternSearcher = regexPatternSearcher;
    }

    @Override
    public void preprocess(String sentencePattern, SemanticPreprocessingData semanticPreprocessingData) {
        for (String verbPattern : RegexExpressions.verbPhrases) {
            List<RegexPatternData> regexPatternDataList = regexPatternSearcher.search(sentencePattern, verbPattern);
            if(regexPatternDataList.size() > 0){
                LOGGER.info("Sentence pattern: " + sentencePattern + " contains verbPhrase: " + regexPatternDataList.get(0).getPattern());
                semanticPreprocessingData.setVerbPhrase(regexPatternDataList.get(0));
            }
        }
    }

}