package com.trainingdataprocessor.semantics.analysis;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.semantics.extraction.SemanticExtractor;
import com.trainingdataprocessor.semantics.preprocessing.SemanticPreprocessor;

import java.util.List;
import java.util.Optional;

/**
 * Created by Oliver on 12/7/2016.
 */
public class SemanticAnalysisExecutorImpl implements SemanticAnalysisExecutor {

    private SemanticPreprocessor semanticPreprocessor;

    private SemanticExtractor semanticExtractor;

    public SemanticAnalysisExecutorImpl(SemanticPreprocessor semanticPreprocessor, SemanticExtractor semanticExtractor) {
        this.semanticPreprocessor = semanticPreprocessor;
        this.semanticExtractor = semanticExtractor;
    }

    @Override
    public Optional<SemanticExtractionData> execute(String filteredEncodedSubPath, List<String> tokens, List<String> encodedTags, int verbIndex) {
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(filteredEncodedSubPath, tokens, encodedTags, verbIndex);
        if (canGoToSemanticExtraction(semanticPreprocessingData)) {
            return Optional.of(semanticExtractor.extract(semanticPreprocessingData));
        } else {
            return Optional.empty();
        }
    }

    private boolean canGoToSemanticExtraction(SemanticPreprocessingData semanticPreprocessingData) {
        return semanticPreprocessingData.containsBeforeVerbNounPhrase() || semanticPreprocessingData.containsBeforeVerbPrepositionPhrase();
    }

}
