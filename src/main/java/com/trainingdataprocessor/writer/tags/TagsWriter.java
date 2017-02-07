package com.trainingdataprocessor.writer.tags;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;

import java.util.List;

/**
 * Created by Oliver on 2/7/2017.
 */
public interface TagsWriter {

    void write(List<TrainingDataRow> trainingDataRowList);

}
