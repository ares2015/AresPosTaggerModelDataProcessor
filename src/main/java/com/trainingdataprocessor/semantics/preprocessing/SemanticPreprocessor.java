package com.trainingdataprocessor.semantics.preprocessing;

import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;

import java.util.List;

/**
 * Created by Oliver on 10/17/2016.
 */
public interface SemanticPreprocessor {

    SemanticPreprocessingData preprocess(List<String> tokensList, List<String> encodedTagsList, int verbIndex);
}
