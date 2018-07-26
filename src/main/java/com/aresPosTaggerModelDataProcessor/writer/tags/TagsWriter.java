package com.aresPosTaggerModelDataProcessor.writer.tags;

import com.aresPosTaggerModelDataProcessor.data.preprocessing.ModelDataRow;

import java.util.List;

/**
 * Created by Oliver on 2/7/2017.
 */
public interface TagsWriter {

    void write(List<ModelDataRow> modelDataRowList);

}
