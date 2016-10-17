package com.trainingdataprocessor.semantics.preprocessing;

import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.semantics.SemanticRelationConstantType;

import java.util.List;

/**
 * Created by Oliver on 10/17/2016.
 */
public interface SemanticPreprocessor {

    SemanticPreprocessingData preprocess(String foundPattern, String constantTag, List<String> subSentence, List<String> encodedTags,
                                         SemanticRelationConstantType constantType);
}
