package com.trainingdataprocessor.semantics;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;

import java.util.List;

/**
 * Created by Oliver on 2/16/2017.
 */
public interface SubjectExtractor {

    void extract(SemanticExtractionData semanticExtractionData, List<String> tokensList, List<String> encodedTagsList,
                 int verbIndex, int beforeVerbPrepositionIndex);

}
