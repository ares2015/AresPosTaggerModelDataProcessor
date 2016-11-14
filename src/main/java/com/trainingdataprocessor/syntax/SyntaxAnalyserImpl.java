package com.trainingdataprocessor.syntax;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.data.syntax.BigramData;
import com.trainingdataprocessor.data.syntax.SubPathData;
import com.trainingdataprocessor.database.TrainingDataDatabaseAccessor;
import com.trainingdataprocessor.factories.BigramDataListFactory;
import com.trainingdataprocessor.factories.SubPathDataListFactory;

import java.util.List;

/**
 * Created by Oliver on 11/9/2016.
 */
public class SyntaxAnalyserImpl implements SyntaxAnalyser, Runnable {

    private TrainingDataDatabaseAccessor trainingDataDatabaseAccessor;

    private BigramDataListFactory bigramDataListFactory;

    private SubPathDataListFactory subPathDataListFactory;

    private List<TrainingDataRow> trainingDataRowList;

    public SyntaxAnalyserImpl(TrainingDataDatabaseAccessor trainingDataDatabaseAccessor, BigramDataListFactory bigramDataListFactory,
                              SubPathDataListFactory subPathDataListFactory, List<TrainingDataRow> trainingDataRowList) {
        this.trainingDataDatabaseAccessor = trainingDataDatabaseAccessor;
        this.bigramDataListFactory = bigramDataListFactory;
        this.subPathDataListFactory = subPathDataListFactory;
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
        List<SubPathData> subPathDataList = subPathDataListFactory.create(tagsList);
        insertBigramDataList(bigramDataList);
        insertStartTagEndPairsList(subPathDataList);
    }


    private void insertBigramDataList(List<BigramData> bigramDataList) {
        for (BigramData bigramData : bigramDataList) {
            trainingDataDatabaseAccessor.insertBigramData(bigramData);
        }
    }

    private void insertStartTagEndPairsList(List<SubPathData> subPathDataList) {
        for (SubPathData subPathData : subPathDataList) {
            trainingDataDatabaseAccessor.insertSubPathData(subPathData);
        }
    }

}
