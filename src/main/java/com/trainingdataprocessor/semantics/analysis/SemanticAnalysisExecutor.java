package com.trainingdataprocessor.semantics.analysis;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;

import java.util.List;
import java.util.Optional;

/**
 * Created by Oliver on 12/7/2016.
 */
public interface SemanticAnalysisExecutor {

    Optional<SemanticExtractionData> execute(String sentencePattern, List<String> tokens, List<String> encodedTags, int verbIndex);
}
