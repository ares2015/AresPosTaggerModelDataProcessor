package com.trainingdataprocessor.semantics.extraction;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;

/**
 * Created by Oliver on 2/17/2017.
 */
public class SemanticExtractorImpl implements SemanticExtractor {

    private SubjectExtractor subjectExtractor;

    private VerbPredicateExtractor verbPredicateExtractor;

    private NounPredicateExtractor nounPredicateExtractor;

    public SemanticExtractorImpl(SubjectExtractor subjectExtractor, VerbPredicateExtractor verbPredicateExtractor,
                                 NounPredicateExtractor nounPredicateExtractor) {
        this.subjectExtractor = subjectExtractor;
        this.verbPredicateExtractor = verbPredicateExtractor;
        this.nounPredicateExtractor = nounPredicateExtractor;
    }

    @Override
    public SemanticExtractionData extract(SemanticPreprocessingData semanticPreprocessingData) {
        SemanticExtractionData semanticExtractionData = new SemanticExtractionData();
        subjectExtractor.extract(semanticExtractionData, semanticPreprocessingData);
        verbPredicateExtractor.extract(semanticExtractionData, semanticPreprocessingData);
        nounPredicateExtractor.extract(semanticExtractionData, semanticPreprocessingData);
        return semanticExtractionData;
    }

}
