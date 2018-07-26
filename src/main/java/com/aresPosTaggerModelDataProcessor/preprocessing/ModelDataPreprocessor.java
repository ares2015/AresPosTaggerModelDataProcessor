package com.aresPosTaggerModelDataProcessor.preprocessing;

import com.aresPosTaggerModelDataProcessor.data.preprocessing.ModelDataRow;

import java.util.List;

/**
 * Created by Oliver on 11/12/2016.
 */
public interface ModelDataPreprocessor {

    List<ModelDataRow> preprocess();
}
