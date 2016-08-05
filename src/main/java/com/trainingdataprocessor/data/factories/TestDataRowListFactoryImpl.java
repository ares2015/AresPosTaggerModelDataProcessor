package com.trainingdataprocessor.data.factories;

import com.trainingdataprocessor.data.TestDataRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 8/5/2016.
 */
public class TestDataRowListFactoryImpl implements TestDataRowListFactory {

    @Override
    public List<TestDataRow> create(List<String> testDataRowStringList) {
        List<TestDataRow> testDataRowList = new ArrayList<>();
        for(String testDataRowString : testDataRowStringList){
            final String[] sentenceAndTags = testDataRowString.split("#");
            final String[] tokens = sentenceAndTags[0].split("\\ ");
            final String[] tags = sentenceAndTags[1].split("\\ ");

//            final String[] subSentences = tokens.split("\\,");
//            final String[] tagSubPaths = tags[1].split("\\,");

        }



        return testDataRowList;
    }
}
