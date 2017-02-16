package com.trainingdataprocessor.semantics.extraction;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.tags.EncodedTags;

import java.util.List;

/**
 * Created by Oliver on 10/21/2016.
 */
public interface SemanticExtractor {

    SemanticExtractionData extract(List<String> tagsList, List<EncodedTags> encodedTagsList, int verbIndex, int beforeVerbIndex, int afterVerbIndex);
}
