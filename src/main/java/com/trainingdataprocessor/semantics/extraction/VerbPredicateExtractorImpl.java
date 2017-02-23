package com.trainingdataprocessor.semantics.extraction;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.tags.Tags;

import java.util.List;

/**
 * Created by Oliver on 2/17/2017.
 */
public class VerbPredicateExtractorImpl implements VerbPredicateExtractor {

    @Override
    public void extract(SemanticExtractionData semanticExtractionData, SemanticPreprocessingData semanticPreprocessingData) {
        List<String> tokensList = semanticPreprocessingData.getTokensList();
        List<String> tagsList = semanticPreprocessingData.getTagsList();
        int verbIndex = semanticPreprocessingData.getVerbIndex();
        int modalVerbIndex = semanticPreprocessingData.getModalVerbIndex();
        semanticExtractionData.setAtomicVerbPredicate(tokensList.get(verbIndex));
        if (modalVerbIndex > -1) {
            String extendedVerbPredicate = extractAtomicVerbPredicate(tokensList, tagsList, modalVerbIndex);
            semanticExtractionData.setExtendedVerbPredicate(extendedVerbPredicate);
        }
    }

    private String extractAtomicVerbPredicate(List<String> tokensList, List<String> encodedTagsList, int modalVerbIndex) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = modalVerbIndex; i < tokensList.size(); i++) {
            if (Tags.VERB.equals(encodedTagsList.get(i)) || Tags.MODAL_VERB.equals(encodedTagsList.get(i))
                    || Tags.IS_ARE.equals(encodedTagsList.get(i)) || Tags.ADVERB.equals(encodedTagsList.get(i))) {
                stringBuilder.append(tokensList.get(i));
                stringBuilder.append(" ");
            } else {
                break;
            }
        }
        return stringBuilder.toString();
    }

}