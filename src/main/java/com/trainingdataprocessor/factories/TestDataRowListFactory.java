package com.trainingdataprocessor.factories;

import com.trainingdataprocessor.data.TestDataRow;

import java.util.List;

/**
 * Created by Oliver on 8/5/2016.
 */
public interface TestDataRowListFactory {

    List<TestDataRow> create(List<String> testDataRowList);
}
