package com.trainingdataprocessor.semantics.analysis;

import com.trainingdataprocessor.cache.SemanticAnalysisFilterCache;
import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.database.TrainingDataDatabaseAccessor;
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

    private TrainingDataDatabaseAccessor trainingDataDatabaseAccessor;

    private List<TrainingDataRow> trainingDataRowList;

    public SemanticAnalyserImpl(SemanticPreprocessor semanticPreprocessor, SemanticExtractor semanticExtractor,
                                TrainingDataDatabaseAccessor trainingDataDatabaseAccessor, List<TrainingDataRow> trainingDataRowList) {
        this.semanticPreprocessor = semanticPreprocessor;
        this.semanticExtractor = semanticExtractor;
        this.trainingDataDatabaseAccessor = trainingDataDatabaseAccessor;
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
                for (int i = 0; i <= trainingDataRow.getTokensMultiList().size() - 1; i++) {
                    if (canGoToSemanticAnalysis(trainingDataRow.getEncodedTagsMultiList().get(i))) {
                        analyseSentence(trainingDataRow.getEncodedSubPathsList().get(i), trainingDataRow.getTokensMultiList().get(i),
                                trainingDataRow.getEncodedTagsMultiList().get(i));
                    }
                }
            } else {
                if (canGoToSemanticAnalysis(trainingDataRow.getEncodedTagsList())) {
                    analyseSentence(trainingDataRow.getEncodedPath(), trainingDataRow.getTokensList(), trainingDataRow.getEncodedTagsList());
                }
            }
        }
    }

    private void analyseSentence(String sentencePattern, List<String> tokens, List<String> encodedTags) {
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(sentencePattern, tokens, encodedTags);
        if (canGoToSemanticExtraction(semanticPreprocessingData)) {
            SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
            trainingDataDatabaseAccessor.insertSemanticData(semanticExtractionData);
        }
    }

    private boolean canGoToSemanticAnalysis(List<String> encodedTags) {
        boolean containsVerb = false;
        for (String tag : encodedTags) {
            if (SemanticAnalysisFilterCache.tagsToFilterCache.contains(tag)) {
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
