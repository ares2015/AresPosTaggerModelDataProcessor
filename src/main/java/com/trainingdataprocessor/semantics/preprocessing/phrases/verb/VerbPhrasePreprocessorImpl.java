package com.trainingdataprocessor.semantics.preprocessing.phrases.verb;

import com.trainingdataprocessor.data.RegexPatternData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.regex.RegexExpressions;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.semantics.preprocessing.phrases.PhrasePreprocessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 10/21/2016.
 */
public class VerbPhrasePreprocessorImpl implements PhrasePreprocessor {

    private RegexPatternSearcher regexPatternSearcher;

    public VerbPhrasePreprocessorImpl(RegexPatternSearcher regexPatternSearcher) {
        this.regexPatternSearcher = regexPatternSearcher;
    }

    @Override
    public void analyse(String sentencePattern, SemanticPreprocessingData semanticPreprocessingData) {
        List<RegexPatternData> regexPatternDataList = new ArrayList<>();
        for (String verbPattern : RegexExpressions.verbPhrases) {
            regexPatternDataList = regexPatternSearcher.search(sentencePattern, verbPattern);
            if(regexPatternDataList.size() > 0){
                semanticPreprocessingData.setVerbPhrase(regexPatternDataList.get(0));
            }
        }
    }


}