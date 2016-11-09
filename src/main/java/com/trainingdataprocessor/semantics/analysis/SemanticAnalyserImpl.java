package com.trainingdataprocessor.semantics.analysis;

import com.trainingdataprocessor.cache.SemanticAnalysisFilterCache;
import com.trainingdataprocessor.data.TestDataRow;
import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.database.TrainingDataAccessor;
import com.trainingdataprocessor.semantics.extraction.SemanticExtractor;
import com.trainingdataprocessor.semantics.preprocessing.SemanticPreprocessor;
import com.trainingdataprocessor.tags.EncodedTags;

import java.util.List;

/**
 * Created by Oliver on 10/31/2016.
 */
public class SemanticAnalyserImpl implements SemanticAnalyser, Runnable {

    private SemanticPreprocessor semanticPreprocessor;

    private SemanticExtractor semanticExtractor;

    private TrainingDataAccessor trainingDataAccessor;

    private SemanticAnalysisFilterCache semanticAnalysisFilterCache;

    private List<TestDataRow> testDataRowList;

    public SemanticAnalyserImpl(SemanticPreprocessor semanticPreprocessor, SemanticExtractor semanticExtractor,
                                TrainingDataAccessor trainingDataAccessor, SemanticAnalysisFilterCache semanticAnalysisFilterCache, List<TestDataRow> testDataRowList) {
        this.semanticPreprocessor = semanticPreprocessor;
        this.semanticExtractor = semanticExtractor;
        this.trainingDataAccessor = trainingDataAccessor;
        this.semanticAnalysisFilterCache = semanticAnalysisFilterCache;
        this.testDataRowList = testDataRowList;
    }

    @Override
    public void run() {
        analyse();
    }

    @Override
    public void analyse() {

        for (TestDataRow testDataRow : testDataRowList) {
            if (testDataRow.containsSubSentences()) {
                for (int i = 0; i <= testDataRow.getTokensMultiList().size() - 1; i++) {
                    if (canGoToSemanticAnalysis(testDataRow.getEncodedTagsMultiList().get(i))) {
                        analyseSentence(testDataRow.getEncodedSubPathsList().get(i), testDataRow.getTokensMultiList().get(i),
                                testDataRow.getEncodedTagsMultiList().get(i));
                    }
                }
            } else {
                if (canGoToSemanticAnalysis(testDataRow.getEncodedTagsList())) {
                    analyseSentence(testDataRow.getEncodedPath(), testDataRow.getTokensList(), testDataRow.getEncodedTagsList());
                }
            }
        }
    }

    private void analyseSentence(String sentencePattern, List<String> tokens, List<String> encodedTags) {
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(sentencePattern, tokens, encodedTags);
        if (canGoToSemanticExtraction(semanticPreprocessingData)) {
            SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
            trainingDataAccessor.insertSemanticData(semanticExtractionData);
        }
    }

    private boolean canGoToSemanticAnalysis(List<String> encodedTags) {
        boolean containsVerb = false;
        for (String tag : encodedTags) {
            if (semanticAnalysisFilterCache.getTagsToFilterCache().contains(tag)) {
                return false;
            }
            if (EncodedTags.VERB.equals(tag)) {
                containsVerb = true;
            }
        }
        return containsVerb;
    }

    private boolean canGoToSemanticExtraction(SemanticPreprocessingData semanticPreprocessingData) {
        return semanticPreprocessingData.containsBeforeVerbNounPhrase() || semanticPreprocessingData.containsBeforeVerbPrepositionPhrase();
    }
}
