package com.trainingdataprocessor.writer.semantics;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;

import java.util.List;

/**
 * Created by oled on 2/22/2017.
 */
public interface SemanticsWriter {

    void write(List<SemanticExtractionData> semanticExtractionDataList);
}
