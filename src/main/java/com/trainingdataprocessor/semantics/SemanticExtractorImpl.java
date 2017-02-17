package com.trainingdataprocessor.semantics;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;

import java.util.List;

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
    public SemanticExtractionData extract(List<String> tokensList, List<String> encodedTagsList, int verbIndex, int modalVerbIndex,
                                          int beforeVerbPrepositionIndex, int afterVerbPrepositionIndex) {
        SemanticExtractionData semanticExtractionData = new SemanticExtractionData();
        subjectExtractor.extract(semanticExtractionData, tokensList, encodedTagsList, verbIndex, beforeVerbPrepositionIndex);
        verbPredicateExtractor.extract(semanticExtractionData, tokensList, encodedTagsList, verbIndex, modalVerbIndex);
        nounPredicateExtractor.extract(semanticExtractionData, tokensList, encodedTagsList, verbIndex, afterVerbPrepositionIndex);
        return semanticExtractionData;
    }

}
