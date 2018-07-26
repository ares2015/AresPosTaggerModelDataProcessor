package com.aresPosTaggerModelDataProcessor.factories.row;

import com.aresPosTaggerModelDataProcessor.data.preprocessing.ModelDataRow;

import java.util.List;

/**
 * Created by Oliver on 8/5/2016.
 */
public interface ModelDataRowListFactory {

    List<ModelDataRow> create(List<String> trainingDataRowList);
}
