package com.trainingdataprocessor.semantics.analysis;

import com.trainingdataprocessor.cache.SemanticAnalysisFilterCache;
import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.database.TrainingDataDatabaseAccessor;
import com.trainingdataprocessor.semantics.preprocessing.SemanticPreprocessingFilter;
import com.trainingdataprocessor.tags.EncodedTags;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Oliver on 10/31/2016.
 */
public class SemanticAnalyserImpl implements SemanticAnalyser, Runnable {

    private SemanticAnalysisExecutor semanticAnalysisExecutor;

    private SemanticPreprocessingFilter semanticPreprocessingFilter;

    private TrainingDataDatabaseAccessor trainingDataDatabaseAccessor;

    private List<TrainingDataRow> trainingDataRowList;

    public SemanticAnalyserImpl(SemanticAnalysisExecutor semanticAnalysisExecutor, SemanticPreprocessingFilter semanticPreprocessingFilter,
                                TrainingDataDatabaseAccessor trainingDataDatabaseAccessor, List<TrainingDataRow> trainingDataRowList) {
        this.semanticAnalysisExecutor = semanticAnalysisExecutor;
        this.semanticPreprocessingFilter = semanticPreprocessingFilter;
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
                    List<String> encodedTags = trainingDataRow.getEncodedTagsList();
                    List<Integer> verbIndexes = getVerbIndexes(encodedTags);
                    List<String> tokensList = trainingDataRow.getTokensList();
                    String filteredEncodedSubpath = semanticPreprocessingFilter.filterToString(encodedTags);
                    List<String> filteredTokensList = semanticPreprocessingFilter.filterToList(tokensList);
                    List<String> filteredEncodedTagsList = semanticPreprocessingFilter.filterToList(encodedTags);
                    if (canGoToSemanticAnalysis(encodedTags, verbIndexes)) {
                        Integer verbIndex = verbIndexes.get(0);
                        List<String> tokens = trainingDataRow.getTokensMultiList().get(i);
                        analyseSentence(filteredEncodedSubpath, filteredTokensList, filteredEncodedTagsList, verbIndex);
                    }
                }
            } else {
                List<String> encodedTags = trainingDataRow.getEncodedTagsList();
                List<Integer> verbIndexes = getVerbIndexes(encodedTags);
                List<String> tokensList = trainingDataRow.getTokensList();
                String filteredEncodedSubpath = semanticPreprocessingFilter.filterToString(encodedTags);
                List<String> filteredTokensList = semanticPreprocessingFilter.filterToList(tokensList);
                List<String> filteredEncodedTagsList = semanticPreprocessingFilter.filterToList(encodedTags);
                if (canGoToSemanticAnalysis(filteredEncodedTagsList, verbIndexes)) {
                    Integer verbIndex = verbIndexes.get(0);
                    analyseSentence(filteredEncodedSubpath, filteredTokensList, filteredEncodedTagsList, verbIndex);
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
            return true;
        } else {
            return false;
        }
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

    private void analyseSentence(String filteredEncodedSubPath, List<String> tokens, List<String> encodedTags, int verbIndex) {
        Optional<SemanticExtractionData> semanticExtractionData = semanticAnalysisExecutor.execute(filteredEncodedSubPath, tokens, encodedTags, verbIndex);
        if (semanticExtractionData.isPresent()) {
            trainingDataDatabaseAccessor.insertSemanticData(semanticExtractionData.get());
        }
    }

}