package com.trainingdataprocessor.semantics.preprocessing;

import com.trainingdataprocessor.data.RegexPatternData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.regex.RegexExpressions;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.semantics.SemanticRelationConstantType;

import java.util.List;

/**
 * Created by Oliver on 10/17/2016.
 */
public class SemanticPreprocessorImpl implements SemanticPreprocessor {

    private RegexPatternSearcher regexPatternSearcher;

    public SemanticPreprocessorImpl(RegexPatternSearcher regexPatternSearcher) {
        this.regexPatternSearcher = regexPatternSearcher;
    }

    public SemanticPreprocessingData preprocess(String foundPattern, String constantTag, List<String> subSentence, List<String> encodedTags,
                                                SemanticRelationConstantType constantType) {
        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();
//        List<RegexPatternData> regexPatternDataList;
//        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();
//
//        regexPatternDataList = regexPatternSearcher.search(foundPattern, RegexExpressions.PREPOSITION_PHRASE);
//        if (regexPatternDataList.size() > 0) {
//            semanticPreprocessingData.setBeforeVerbPrepositionPhrase(regexPatternDataList.get(0));
//            semanticPreprocessingData.setContainsBeforeVerbPrepositionPhrase(true);
//            semanticPreprocessingData.setContainsBeforeVerbNounPhrase(false);
//        }
//
//        if(!(semanticPreprocessingData.isContainsBeforeVerbPrepositionPhrase())){
//            regexPatternDataList = regexPatternSearcher.search(foundPattern, RegexExpressions.NOUN_PHRASE);
//            if (regexPatternDataList.size() > 0) {
//                semanticPreprocessingData.setBeforeVerbNounPhrase(regexPatternDataList.get(0));
//                semanticPreprocessingData.setContainsBeforeVerbNounPhrase(true);
//            }
//        }


        return semanticPreprocessingData;
    }

}
