package com.trainingdataprocessor.preprocessing;

import com.trainingdataprocessor.data.TestDataRow;

import java.util.List;

/**
 * Created by Oliver on 11/12/2016.
 */
public interface TestDataPreprocessor {

    List<TestDataRow> preprocess();
}
