package com.trainingdataprocessor.semantics.analysis;

import com.trainingdataprocessor.data.TestDataRow;

import java.util.List;

/**
 * Created by Oliver on 10/22/2016.
 */
public interface SemanticAnalyser {

    void analyse(List<TestDataRow> testDataRowList);

}
