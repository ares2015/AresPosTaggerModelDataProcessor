package com.trainingdataprocessor.preprocessing;

import com.trainingdataprocessor.data.TestDataRow;
import com.trainingdataprocessor.factories.TestDataRowListFactory;
import com.trainingdataprocessor.reader.TestDataReader;

import java.util.List;

/**
 * Created by Oliver on 11/12/2016.
 */
public class TestDataPreprocessorImpl implements TestDataPreprocessor {

    private TestDataReader testDataReader;

    private TestDataRowListFactory testDataRowListFactory;

    @Override
    public List<TestDataRow> preprocess() {
        return null;
    }
}
