package com.aresPosTaggerModelDataProcessor.syntax;

import com.aresPosTaggerModelDataProcessor.data.preprocessing.TrainingDataRow;
import com.aresPosTaggerModelDataProcessor.data.syntax.BigramData;
import com.aresPosTaggerModelDataProcessor.data.syntax.SubPathData;
import com.aresPosTaggerModelDataProcessor.factories.bigram.BigramDataListFactory;
import com.aresPosTaggerModelDataProcessor.factories.subpath.SubPathDataListFactory;
import com.aresPosTaggerModelDataProcessor.writer.bigrams.BigramsWriter;
import com.aresPosTaggerModelDataProcessor.writer.subpaths.SubPathsWriter;

import java.util.List;

/**
 * Created by Oliver on 11/9/2016.
 */
public class SyntaxAnalyserImpl implements SyntaxAnalyser, Runnable {

    private BigramsWriter bigramsWriter;

    private SubPathsWriter subPathsWriter;

    private BigramDataListFactory bigramDataListFactory;

    private SubPathDataListFactory subPathDataListFactory;

    private List<TrainingDataRow> trainingDataRowList;

    public SyntaxAnalyserImpl(BigramsWriter bigramsWriter, SubPathsWriter subPathsWriter,
                              BigramDataListFactory bigramDataListFactory, SubPathDataListFactory subPathDataListFactory,
                              List<TrainingDataRow> trainingDataRowList) {
        this.bigramsWriter = bigramsWriter;
        this.subPathsWriter = subPathsWriter;
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
        bigramsWriter.write(bigramDataList);
        subPathsWriter.write(subPathDataList);
    }

}
