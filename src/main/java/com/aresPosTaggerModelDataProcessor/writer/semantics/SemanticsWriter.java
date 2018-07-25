package com.aresPosTaggerModelDataProcessor.writer.semantics;

import java.util.List;

/**
 * Created by oled on 2/22/2017.
 */
public interface SemanticsWriter {

    void write(List<com.semanticRelationsExtractor.data.SemanticExtractionData> semanticExtractionDataList);
}
