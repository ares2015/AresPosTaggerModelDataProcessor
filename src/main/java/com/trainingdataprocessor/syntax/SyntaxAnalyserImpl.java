package com.trainingdataprocessor.syntax;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.data.syntax.BigramData;
import com.trainingdataprocessor.data.syntax.StartTagEndTagPair;
import com.trainingdataprocessor.database.TrainingDataDatabaseAccessor;
import com.trainingdataprocessor.factories.BigramDataListFactory;
import com.trainingdataprocessor.factories.StartTagEndTagPairsListFactory;

import java.util.List;

/**
 * Created by Oliver on 11/9/2016.
 */
public class SyntaxAnalyserImpl implements SyntaxAnalyser, Runnable {

    private TrainingDataDatabaseAccessor trainingDataDatabaseAccessor;

    private BigramDataListFactory bigramDataListFactory;

    private StartTagEndTagPairsListFactory startTagEndTagPairsListFactory;

    private List<TrainingDataRow> trainingDataRowList;

    public SyntaxAnalyserImpl(TrainingDataDatabaseAccessor trainingDataDatabaseAccessor, BigramDataListFactory bigramDataListFactory,
                              StartTagEndTagPairsListFactory startTagEndTagPairsListFactory, List<TrainingDataRow> trainingDataRowList) {
        this.trainingDataDatabaseAccessor = trainingDataDatabaseAccessor;
        this.bigramDataListFactory = bigramDataListFactory;
        this.startTagEndTagPairsListFactory = startTagEndTagPairsListFactory;
        this.trainingDataRowList = trainingDataRowList;
    }

    @Override
    public void run() {
        analyse();
    }

    @Override
    public void analyse() {
        for (TrainingDataRow trainingDataRow : trainingDataRowList) {
            if (trainingDataRow.containsSubSentences()) {
                for (int i = 0; i <= trainingDataRow.getTagsMultiList().size() - 1; i++) {
                    List<String> tagsList = trainingDataRow.getTagsMultiList().get(i);
                    analyseSentence(tagsList);
                }
            } else {
                List<String> tagsList = trainingDataRow.getTagsList();
                analyseSentence(tagsList);
            }
        }
    }

    private void analyseSentence(List<String> tagsList) {
        List<BigramData> bigramDataList = bigramDataListFactory.create(tagsList);
        List<StartTagEndTagPair> startTagEndTagPairList = startTagEndTagPairsListFactory.create(tagsList);
        insertBigramDataList(bigramDataList);
        insertStartTagEndPairsList(startTagEndTagPairList);
    }


    private void insertBigramDataList(List<BigramData> bigramDataList) {
        for (BigramData bigramData : bigramDataList) {
            trainingDataDatabaseAccessor.insertBigramData(bigramData);
        }
    }

    private void insertStartTagEndPairsList(List<StartTagEndTagPair> startTagEndTagPairList) {
        for (StartTagEndTagPair startTagEndTagPair : startTagEndTagPairList) {
            trainingDataDatabaseAccessor.insertStartTagEndTagPair(startTagEndTagPair);
        }
    }

}
