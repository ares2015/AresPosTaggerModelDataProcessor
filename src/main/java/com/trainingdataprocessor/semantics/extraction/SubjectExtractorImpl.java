package com.trainingdataprocessor.semantics.extraction;

import com.trainingdataprocessor.cache.SemanticExtractionFilterCache;
import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.tags.EncodedTags;

import java.util.List;

/**
 * Created by Oliver on 2/16/2017.
 */
public class SubjectExtractorImpl implements SubjectExtractor {

    @Override
    public SemanticExtractionData extract(List<String> tokensList, List<EncodedTags> encodedTagsList, int verbIndex) {
        String extendedSubject = extractExtendedSubject(tokensList, encodedTagsList);

        return null;
    }

    private String extractExtendedSubject(List<String> tokensList, List<EncodedTags> encodedTagsList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < encodedTagsList.size(); i++) {
            if (SemanticExtractionFilterCache.subjectExtractionAllowedTags.contains(encodedTagsList.get(i))) {
                stringBuilder.append(tokensList.get(i));
                stringBuilder.append(" ");
            } else {
                break;
            }
        }
        return stringBuilder.toString();
    }
}
