package com.trainingdataprocessor.factories;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;

import java.util.List;

/**
 * Created by Oliver on 8/5/2016.
 */
public interface TrainingDataRowListFactory {

    List<TrainingDataRow> create(List<String> trainingDataRowList);
}
