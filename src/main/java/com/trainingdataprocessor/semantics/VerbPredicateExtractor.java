package com.trainingdataprocessor.semantics;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;

import java.util.List;

/**
 * Created by Oliver on 2/17/2017.
 */
public interface VerbPredicateExtractor {

    void extract(SemanticExtractionData semanticExtractionData, List<String> tokensList, List<String> encodedTagsList, int verbIndex, int modalVerbIndex);
}
