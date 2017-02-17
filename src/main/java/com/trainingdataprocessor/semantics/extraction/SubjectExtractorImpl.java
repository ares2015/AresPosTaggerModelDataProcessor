package com.trainingdataprocessor.semantics.extraction;

import com.trainingdataprocessor.cache.SemanticExtractionFilterCache;
import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.tags.Tags;

import java.util.List;

/**
 * Created by Oliver on 2/16/2017.
 */
public class SubjectExtractorImpl implements SubjectExtractor {

    @Override
    public void extract(SemanticExtractionData semanticExtractionData, SemanticPreprocessingData semanticPreprocessingData) {
        List<String> tokensList = semanticPreprocessingData.getTokensList();
        List<String> tagsList = semanticPreprocessingData.getTagsList();
        int verbIndex = semanticPreprocessingData.getVerbIndex();
        if (!semanticPreprocessingData.containsBeforeVerbPreposition()) {
            String atomicSubject = extractAtomicSubject(tokensList, tagsList, verbIndex);
            semanticExtractionData.setAtomicSubject(atomicSubject);
        }
        if (verbIndex > 1) {
            String extendedSubject = extractExtendedSubject(tokensList, tagsList);
            semanticExtractionData.setExtendedSubject(extendedSubject);
        }
    }

    private String extractAtomicSubject(List<String> tokensList, List<String> tagsList, int verbIndex) {
        for (int i = verbIndex; i >= 0; i--) {
            if (Tags.NOUN.equals(tagsList.get(i)) || Tags.VERB_ED.equals(tagsList.get(i))) {
                return tokensList.get(i);
            }
        }
        throw new IllegalStateException("There is no subject in the sentence");
    }

    private String extractExtendedSubject(List<String> tokensList, List<String> encodedTagsList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < encodedTagsList.size(); i++) {
            if (SemanticExtractionFilterCache.subjectNounPredicateExtractionAllowedTags.contains(encodedTagsList.get(i))) {
                stringBuilder.append(tokensList.get(i));
                stringBuilder.append(" ");
            } else {
                break;
            }
        }
        return stringBuilder.toString();
    }
}
