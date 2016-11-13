package com.trainingdataprocessor.tags;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.database.TrainingDataDatabaseAccessor;

import java.util.List;

/**
 * Created by Oliver on 11/13/2016.
 */
public class TagsProcessorImpl implements TagsProcessor {

    private TrainingDataDatabaseAccessor trainingDataDatabaseAccessor;

    public TagsProcessorImpl(TrainingDataDatabaseAccessor trainingDataDatabaseAccessor) {
        this.trainingDataDatabaseAccessor = trainingDataDatabaseAccessor;
    }

    @Override
    public void process(List<TrainingDataRow> trainingDataRowList) {
        for(TrainingDataRow trainingDataRow : trainingDataRowList){
            for(String tag : trainingDataRow.getTagsList()){
                trainingDataDatabaseAccessor.insertTag(tag);
            }
        }
    }

}