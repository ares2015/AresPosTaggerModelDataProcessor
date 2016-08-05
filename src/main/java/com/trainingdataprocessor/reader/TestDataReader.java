package com.trainingdataprocessor.reader;

import com.trainingdataprocessor.data.TestDataRow;

import java.util.List;

/**
 * Created by Oliver on 8/5/2016.
 */
public interface TestDataReader {

    List<TestDataRow> read();

}
