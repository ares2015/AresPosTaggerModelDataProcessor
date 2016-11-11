package com.trainingdataprocessor.syntax;

import com.trainingdataprocessor.data.BigramData;
import com.trainingdataprocessor.data.StartTagEndTagPair;
import com.trainingdataprocessor.data.TestDataRow;
import com.trainingdataprocessor.database.TrainingDataAccessor;
import com.trainingdataprocessor.factories.BigramDataListFactory;
import com.trainingdataprocessor.factories.StartTagEndTagPairsListFactory;

import java.util.List;

/**
 * Created by Oliver on 11/9/2016.
 */
public class SyntaxAnalyserImpl implements SyntaxAnalyser, Runnable {

    private TrainingDataAccessor trainingDataAccessor;

    private BigramDataListFactory bigramDataListFactory;

    private StartTagEndTagPairsListFactory startTagEndTagPairsListFactory;

    private List<TestDataRow> testDataRowList;

    public SyntaxAnalyserImpl(TrainingDataAccessor trainingDataAccessor, BigramDataListFactory bigramDataListFactory,
                              StartTagEndTagPairsListFactory startTagEndTagPairsListFactory, List<TestDataRow> testDataRowList) {
        this.trainingDataAccessor = trainingDataAccessor;
        this.bigramDataListFactory = bigramDataListFactory;
        this.startTagEndTagPairsListFactory = startTagEndTagPairsListFactory;
        this.testDataRowList = testDataRowList;
    }

    @Override
    public void run() {
        analyse();
    }

    @Override
    public void analyse() {
        for(TestDataRow testDataRow : testDataRowList){
            List<String> tagsList = testDataRow.getTagsList();
            List<BigramData> bigramDataList = bigramDataListFactory.create(tagsList);
            List<StartTagEndTagPair> startTagEndTagPairList = startTagEndTagPairsListFactory.create(tagsList);
        }
    }

    private void insertBigramDataList(List<BigramData> bigramDataList){
        for(BigramData bigramData : bigramDataList){
            trainingDataAccessor.insertBigramData(bigramData);
        }
    }

    private void insertStartTagEndPairsList(List<StartTagEndTagPair> startTagEndTagPairList){
        for(StartTagEndTagPair startTagEndTagPair : startTagEndTagPairList){
            trainingDataAccessor.insertStartTagEndTagPair(startTagEndTagPair);
        }
    }

}
