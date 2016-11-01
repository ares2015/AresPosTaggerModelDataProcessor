package com.trainingdataprocessor.semantics.analysis;

import com.trainingdataprocessor.data.TestDataRow;
import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.database.TrainingDataAccessor;
import com.trainingdataprocessor.semantics.extraction.SemanticExtractor;
import com.trainingdataprocessor.semantics.preprocessing.SemanticPreprocessor;

import java.util.List;

/**
 * Created by Oliver on 10/31/2016.
 */
public class SemanticAnalyserImpl implements SemanticAnalyser {

    private SemanticPreprocessor semanticPreprocessor;

    private SemanticExtractor semanticExtractor;

    private TrainingDataAccessor trainingDataAccessor;

    public SemanticAnalyserImpl(SemanticPreprocessor semanticPreprocessor, SemanticExtractor semanticExtractor,
                                TrainingDataAccessor trainingDataAccessor) {
        this.semanticPreprocessor = semanticPreprocessor;
        this.semanticExtractor = semanticExtractor;
        this.trainingDataAccessor = trainingDataAccessor;
    }

    @Override
    public void analyse(List<TestDataRow> testDataRowList) {

        for (TestDataRow testDataRow : testDataRowList) {
            if (testDataRow.containsSubSentences()) {
                for (int i = 0; i <= testDataRow.getTokensMultiList().size() - 1; i++) {
                    analyseSentence(testDataRow.getEncodedSubPathsList().get(i), testDataRow.getTokensMultiList().get(i), testDataRow.getEncodedTagsMultiList().get(i));
                }
            } else {
                analyseSentence(testDataRow.getEncodedSubPath(), testDataRow.getTokensList(), testDataRow.getEncodedTagsList());
            }
        }
    }

    private void analyseSentence(String sentencePattern, List<String> tokens, List<String> encodedTags) {
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(sentencePattern, tokens, encodedTags);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);

    }
}
