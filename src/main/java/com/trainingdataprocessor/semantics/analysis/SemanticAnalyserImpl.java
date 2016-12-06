package com.trainingdataprocessor.semantics.analysis;

import com.trainingdataprocessor.cache.SemanticAnalysisFilterCache;
import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.database.TrainingDataDatabaseAccessor;
import com.trainingdataprocessor.semantics.extraction.SemanticExtractor;
import com.trainingdataprocessor.semantics.preprocessing.SemanticPreprocessor;
import com.trainingdataprocessor.tags.EncodedTags;

import java.util.ArrayList;
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
                    List<Integer> verbIndexes = getVerbIndexes(trainingDataRow.getEncodedTagsMultiList().get(i));
                    if (canGoToSemanticAnalysis(trainingDataRow.getEncodedTagsMultiList().get(i), verbIndexes)) {
                        Integer verbIndex = verbIndexes.get(0);
                        analyseSentence(trainingDataRow.getEncodedSubPathsAsStringList().get(i), trainingDataRow.getTokensMultiList().get(i),
                                trainingDataRow.getEncodedTagsMultiList().get(i), verbIndex);
                    }
                }
            } else {
                List<Integer> verbIndexes = getVerbIndexes(trainingDataRow.getEncodedTagsList());
                if (canGoToSemanticAnalysis(trainingDataRow.getEncodedTagsList(), verbIndexes)) {
                    Integer verbIndex = verbIndexes.get(0);
                    analyseSentence(trainingDataRow.getEncodedPathAsString(), trainingDataRow.getTokensList(), trainingDataRow.getEncodedTagsList(), verbIndex);
                }
            }
        }
    }

    private boolean canGoToSemanticAnalysis(List<String> encodedTags, List<Integer> verbIndexes) {
        if (verbIndexes.size() == 1) {
            for (String tag : encodedTags) {
                if (SemanticAnalysisFilterCache.tagsToFilterCache.contains(tag)) {
                    return false;
                }
            }
        }
        return false;
    }

    private List<Integer> getVerbIndexes(List<String> encodedTags) {
        List<Integer> verbIndexes = new ArrayList<>();
        for (int i = 0; i <= encodedTags.size() - 1; i++) {
            if (encodedTags.get(i).equals(EncodedTags.VERB) || encodedTags.get(i).equals(EncodedTags.IS_ARE)) {
                verbIndexes.add(i);
            }
        }
        return verbIndexes;
    }

    private void analyseSentence(String sentencePattern, List<String> tokens, List<String> encodedTags, int verbIndex) {
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(sentencePattern, tokens, encodedTags, verbIndex);
        if (canGoToSemanticExtraction(semanticPreprocessingData)) {
            SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
            trainingDataDatabaseAccessor.insertSemanticData(semanticExtractionData);
        }
    }

    private boolean canGoToSemanticExtraction(SemanticPreprocessingData semanticPreprocessingData) {
        return semanticPreprocessingData.containsBeforeVerbNounPhrase() || semanticPreprocessingData.containsBeforeVerbPrepositionPhrase();
    }
}