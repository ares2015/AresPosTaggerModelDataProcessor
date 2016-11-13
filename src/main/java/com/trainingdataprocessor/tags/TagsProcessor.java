package com.trainingdataprocessor.tags;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;

import java.util.List;

/**
 * Created by Oliver on 11/13/2016.
 */
public interface TagsProcessor {

    void process(List<TrainingDataRow> trainingDataRowList);
}
