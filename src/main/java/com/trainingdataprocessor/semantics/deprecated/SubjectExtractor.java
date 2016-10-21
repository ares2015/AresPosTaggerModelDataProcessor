package com.trainingdataprocessor.semantics.deprecated;

import java.util.List;

/**
 * Created by Oliver on 10/15/2016.
 */
public interface SubjectExtractor {

    String extract(List<String> subSentence, int constantIndex, SemanticRelationConstantType constantType);
}
