package com.aresPosTaggerModelDataProcessor.semantics.analysis;

import com.aresPosTaggerModelDataProcessor.data.preprocessing.TrainingDataRow;
import com.aresPosTaggerModelDataProcessor.writer.semantics.SemanticsWriter;
import com.semanticRelationsExtractor.main.SemanticExtractionProcessor;
import com.semanticRelationsExtractor.main.SemanticExtractionProcessorImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Oliver on 10/31/2016.
 */
public class SemanticAnalyserImpl implements SemanticAnalyser, Runnable {

    private SemanticExtractionProcessor semanticExtractionProcessor;

    private List<TrainingDataRow> trainingDataRowList;

    private SemanticsWriter semanticsWriter;


    public SemanticAnalyserImpl(SemanticsWriter semanticsWriter, List<TrainingDataRow> trainingDataRowList) {
        semanticExtractionProcessor = new SemanticExtractionProcessorImpl();
        this.semanticsWriter = semanticsWriter;
        this.trainingDataRowList = trainingDataRowList;
    }

    @Override
    public void run() {
        try {
            analyse();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void analyse() throws InterruptedException {
        List<com.semanticRelationsExtractor.data.SemanticExtractionData> semanticExtractionDataList = new ArrayList<>();
        try {
            for (TrainingDataRow trainingDataRow : trainingDataRowList) {
                if (trainingDataRow.containsSubSentences()) {
                    for (int i = 0; i <= trainingDataRow.getTokensMultiList().size() - 1; i++) {
                        List<String> tokensList = trainingDataRow.getTokensMultiList().get(i);
                        List<String> tagsList = trainingDataRow.getTagsMultiList().get(i);
                        Optional<com.semanticRelationsExtractor.data.SemanticExtractionData> semanticExtractionData =
                                semanticExtractionProcessor.process(tokensList, tagsList);
                        if (semanticExtractionData.isPresent()) {
                            semanticExtractionDataList.add(semanticExtractionData.get());
                        }
                    }
                } else {
                    List<String> tagsList = trainingDataRow.getTagsList();
                    List<String> tokensList = trainingDataRow.getTokensList();
                    Optional<com.semanticRelationsExtractor.data.SemanticExtractionData> semanticExtractionData =
                            semanticExtractionProcessor.process(tokensList, tagsList);
                    if (semanticExtractionData.isPresent()) {
                        semanticExtractionDataList.add(semanticExtractionData.get());
                    }
                }
            }
        } catch (java.lang.IllegalStateException e) {
        }

        semanticsWriter.write(semanticExtractionDataList);
    }
}