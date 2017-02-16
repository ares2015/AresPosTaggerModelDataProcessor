package com.trainingdataprocessor.semantics.extraction;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.tags.EncodedTags;

import java.util.List;

/**
 * Created by Oliver on 2/16/2017.
 */
public interface SubjectExtractor {

    SemanticExtractionData extract(List<String> tokensList, List<EncodedTags> encodedTagsList, int verbIndex);

}
