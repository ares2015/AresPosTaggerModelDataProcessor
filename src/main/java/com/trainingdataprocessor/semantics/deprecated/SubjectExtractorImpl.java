package com.trainingdataprocessor.semantics.deprecated;

import java.util.List;

/**
 * Created by Oliver on 10/15/2016.
 */
public class SubjectExtractorImpl implements SubjectExtractor {

    public String extract(List<String> subSentence, int constantIndex, SemanticRelationConstantType constantType) {
        String extendedSubject = "";
        int endIndex = constantIndex - SemanticRelationConstantType.relationsExtractorExtSubjectIndexMap.get(constantType);
        for (int i = 0; i < endIndex; i++) {
            if (i < endIndex - 1)
                extendedSubject += subSentence.get(i) + " ";
            else
                extendedSubject += subSentence.get(i);
        }
        return extendedSubject;
    }
}
