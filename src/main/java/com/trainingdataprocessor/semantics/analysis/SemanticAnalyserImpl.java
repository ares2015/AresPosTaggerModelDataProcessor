package com.trainingdataprocessor.semantics.analysis;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.database.TrainingDataDatabaseAccessor;
import com.trainingdataprocessor.semantics.extraction.SemanticExtractor;
import com.trainingdataprocessor.semantics.preprocessing.SemanticPreprocessor;

import java.util.List;

/**
 * Created by Oliver on 10/31/2016.
 */
public class SemanticAnalyserImpl implements SemanticAnalyser, Runnable {

    private SemanticPreprocessor semanticPreprocessor;

    private SemanticExtractor semanticExtractor;

    private List<TrainingDataRow> trainingDataRowList;

    private TrainingDataDatabaseAccessor trainingDataDatabaseAccessor;

    public SemanticAnalyserImpl(SemanticPreprocessor semanticPreprocessor, SemanticExtractor semanticExtractor,
                                List<TrainingDataRow> trainingDataRowList, TrainingDataDatabaseAccessor trainingDataDatabaseAccessor) {
        this.semanticPreprocessor = semanticPreprocessor;
        this.semanticExtractor = semanticExtractor;
        this.trainingDataRowList = trainingDataRowList;
        this.trainingDataDatabaseAccessor = trainingDataDatabaseAccessor;
    }

    @Override
    public void run() {
        analyse();
    }

    @Override
    public void analyse() {
        for (TrainingDataRow trainingDataRow : trainingDataRowList) {
            if (trainingDataRow.containsSubSentences()) {
                for (int i = 0; i <= trainingDataRow.getTokensMultiList().size() - 1; i++) {
                    List<String> tokensList = trainingDataRow.getTokensMultiList().get(i);
                    List<String> tagsList = trainingDataRow.getTagsMultiList().get(i);
                    analyseSentence(tokensList, tagsList);
                }
            } else {
                List<String> tagsList = trainingDataRow.getTagsList();
                List<String> tokensList = trainingDataRow.getTokensList();
                analyseSentence(tokensList, tagsList);
            }
        }
    }

    private void analyseSentence(List<String> tokensList, List<String> tagsList) {
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokensList, tagsList);
        if (semanticPreprocessingData.canGoToExtraction()) {
            SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
            trainingDataDatabaseAccessor.insertSemanticData(semanticExtractionData);
        }
    }

}