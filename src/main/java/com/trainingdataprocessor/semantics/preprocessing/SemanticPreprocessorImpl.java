package com.trainingdataprocessor.semantics.preprocessing;

import com.trainingdataprocessor.data.RegexPatternData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.regex.RegexExpressions;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.semantics.SemanticRelationConstantType;
import com.trainingdataprocessor.semantics.preprocessing.phrases.PrepositionPhraseAnalyser;
import com.trainingdataprocessor.tags.EncodedTags;

import java.util.List;

/**
 * Created by Oliver on 10/17/2016.
 */
public class SemanticPreprocessorImpl implements SemanticPreprocessor {

    private PrepositionPhraseAnalyser prepositionPhraseAnalyser;


    public SemanticPreprocessingData preprocess(String sentencePattern, List<String> subSentence, List<String> encodedTags,
                                                SemanticRelationConstantType constantType) {

        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();
        semanticPreprocessingData.setVerbIndex(getVerbIndex(encodedTags));

        prepositionPhraseAnalyser.analyse(sentencePattern, semanticPreprocessingData);


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