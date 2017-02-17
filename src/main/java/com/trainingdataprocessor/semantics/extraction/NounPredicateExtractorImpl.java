package com.trainingdataprocessor.semantics.extraction;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.tags.EncodedTags;

import java.util.List;

/**
 * Created by Oliver on 2/16/2017.
 */
public class NounPredicateExtractorImpl implements NounPredicateExtractor {

    @Override
    public void extract(SemanticExtractionData semanticExtractionData, SemanticPreprocessingData semanticPreprocessingData) {
        List<String> tokensList = semanticPreprocessingData.getTokensList();
        List<String> tagsList = semanticPreprocessingData.getTagsList();
        int verbIndex = semanticPreprocessingData.getVerbIndex();
        int afterVerbPrepositionIndex = semanticPreprocessingData.getAfterVerbFirstPrepositionIndex();
        if (!semanticPreprocessingData.containsAfterVerbVerbIng()) {
            String atomicNounPredicate = extractAtomicNounPredicate(tokensList, tagsList, verbIndex, afterVerbPrepositionIndex);
            semanticExtractionData.setAtomicNounPredicate(atomicNounPredicate);
        }
        String extendedNounPredicate = extractExtendedNounPredicate(tokensList, verbIndex);
        semanticExtractionData.setExtendedNounPredicate(extendedNounPredicate);
    }

    private String extractAtomicNounPredicate(List<String> tokensList, List<String> encodedTagsList,
                                              int verbIndex, int afterVerbPrepositionIndex) {
        int lastNounIndex = getLastNounVerbEdIndex(encodedTagsList, verbIndex, afterVerbPrepositionIndex);
        if (lastNounIndex > -1) {
            return tokensList.get(lastNounIndex);
        } else {
            return "";
        }
    }

    private int getLastNounVerbEdIndex(List<String> encodedTagsList,
                                       int verbIndex, int afterVerbPrepositionIndex) {
        int lastNounIndex = -1;
        if (afterVerbPrepositionIndex > -1) {
            for (int i = verbIndex + 1; i < afterVerbPrepositionIndex; i++) {
                if (EncodedTags.NOUN.equals(encodedTagsList.get(i)) || EncodedTags.VERB_ED.equals(encodedTagsList.get(i))) {
                    lastNounIndex = i;
                }
            }
        } else {
            for (int i = verbIndex + 1; i < encodedTagsList.size(); i++) {
                if (EncodedTags.NOUN.equals(encodedTagsList.get(i))) {
                    lastNounIndex = i;
                }
            }
        }
        return lastNounIndex;
    }

    private String extractExtendedNounPredicate(List<String> tokensList, int verbIndex) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = verbIndex + 1; i < tokensList.size(); i++) {
            stringBuilder.append(tokensList.get(i));
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

}
