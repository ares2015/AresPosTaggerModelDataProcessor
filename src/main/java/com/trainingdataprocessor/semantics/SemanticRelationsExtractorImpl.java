package com.trainingdataprocessor.semantics;

import com.trainingdataprocessor.data.semantics.SemanticRelationData;
import com.trainingdataprocessor.data.RegexPatternIndexData;
import com.trainingdataprocessor.data.semantics.SemanticalConstantTagAnalysisData;

import java.util.List;

public class SemanticRelationsExtractorImpl implements SemanticRelationsExtractor<SemanticRelationData> {

    private SemanticConstantTagAnalyser semanticConstantTagAnalyser;

    private SubjectExtractor subjectExtractor;

    private PredicateExtractor predicateExtractor;

    private VerbExtractor verbExtractor;

    public SemanticRelationsExtractorImpl(SemanticConstantTagAnalyser semanticConstantTagAnalyser, SubjectExtractor subjectExtractor,
                                          PredicateExtractor predicateExtractor, VerbExtractor verbExtractor) {
        this.semanticConstantTagAnalyser = semanticConstantTagAnalyser;
        this.subjectExtractor = subjectExtractor;
        this.predicateExtractor = predicateExtractor;
        this.verbExtractor = verbExtractor;
    }

    @Override
    public SemanticRelationData extract(String constant, RegexPatternIndexData regexPatternIndexData, List<String> tokens, List<String> encodedTags,
                                        SemanticRelationConstantType constantType) {

        SemanticRelationData semanticRelationData = new SemanticRelationData();

        semanticRelationData.setStartIndex(regexPatternIndexData.getStartIndex());
        semanticRelationData.setEndIndex(regexPatternIndexData.getEndIndex());

        List<String> subSentence = tokens.subList(regexPatternIndexData.getStartIndex(), regexPatternIndexData.getEndIndex());

        SemanticalConstantTagAnalysisData semanticalConstantTagAnalysisData = semanticConstantTagAnalyser.analyse(constant, subSentence,
                encodedTags, constantType);

        processSubject(subSentence, semanticRelationData, semanticalConstantTagAnalysisData, constantType);
        processPredicate(subSentence, encodedTags, semanticRelationData, semanticalConstantTagAnalysisData, constantType);
        processVerb(semanticRelationData, semanticalConstantTagAnalysisData, subSentence, encodedTags, constantType);

        return semanticRelationData;
    }

    private void processSubject(List<String> subSentence, SemanticRelationData semanticRelationData,
                                SemanticalConstantTagAnalysisData semanticalConstantTagAnalysisData, SemanticRelationConstantType constantType) {
        if (semanticalConstantTagAnalysisData.containsBeforeConstantTagPreposition()) {
            semanticRelationData.setExtendedSubject(subjectExtractor.extract(subSentence, semanticalConstantTagAnalysisData.getConstantIndex(), constantType));
        } else {
            semanticRelationData.setAtomicSubject(subSentence.get(semanticalConstantTagAnalysisData.getConstantIndex() -
                    SemanticRelationConstantType.relationsExtractorAtomicSubjectIndexMap.get(constantType)));
            if (semanticalConstantTagAnalysisData.hasExtendedSubject()) {
                semanticRelationData.setExtendedSubject(subjectExtractor.extract(subSentence, semanticalConstantTagAnalysisData.getConstantIndex(), constantType));
            }
        }
    }

    private void processPredicate(List<String> subSentence, List<String> encodedTags, SemanticRelationData semanticRelationData,
                                  SemanticalConstantTagAnalysisData semanticalConstantTagAnalysisData, SemanticRelationConstantType constantType) {
        if (SemanticRelationConstantType.nounVerbRelationTypes.contains(constantType) && semanticalConstantTagAnalysisData.containsAfterConstantTagPreposition()) {
            semanticRelationData.setPrepositionPredicate(predicateExtractor.extractPrepositionPredicate(subSentence, encodedTags,
                    semanticalConstantTagAnalysisData.getConstantIndex(), constantType));
        } else if (!(SemanticRelationConstantType.nounVerbRelationTypes.contains(constantType)) && semanticalConstantTagAnalysisData.containsAfterConstantTagPreposition()) {
            semanticRelationData.setAtomicPredicate(subSentence.get(semanticalConstantTagAnalysisData.getAfterConstantTagPrepositionIndexes().get(0) - 1));
            semanticRelationData.setExtendedPredicate(predicateExtractor.extractExtendedPredicate(subSentence, encodedTags, semanticalConstantTagAnalysisData.getConstantIndex(),
                    semanticalConstantTagAnalysisData.getAfterConstantTagPrepositionIndexes().get(0), constantType));
            semanticRelationData.setPrepositionPredicate(predicateExtractor.extractPrepositionPredicate(subSentence, encodedTags,
                    semanticalConstantTagAnalysisData.getConstantIndex(), constantType));
        } else {
            semanticRelationData.setAtomicPredicate(subSentence.get(subSentence.size() - 1));
            semanticRelationData.setExtendedPredicate(predicateExtractor.extractExtendedPredicate(subSentence, encodedTags, semanticalConstantTagAnalysisData.getConstantIndex(), constantType));
        }
    }

    private void processVerb(SemanticRelationData semanticRelationData, SemanticalConstantTagAnalysisData semanticalConstantTagAnalysisData,
                             List<String> subSentence, List<String> encodedTags, SemanticRelationConstantType constantType) {
        int constantIndex = semanticalConstantTagAnalysisData.getConstantIndex();
        semanticRelationData.setPositiveVerb(verbExtractor.isPositiveVerb(subSentence, constantIndex, constantType));
        semanticRelationData.setPresentTense(verbExtractor.isPresentTense(subSentence, encodedTags, constantIndex, constantType));
        if (semanticalConstantTagAnalysisData.hasVerbAuxiliaryVerbPhrase()) {
            semanticRelationData.setExtendedVerb(verbExtractor.extractExtendedVerb(subSentence, encodedTags,
                    constantIndex, constantType));
        }
        if (SemanticRelationConstantType.modalVerbs.contains(constantType)) {
            semanticRelationData.setAtomicModalVerb(subSentence.get(constantIndex));
            semanticRelationData.setAtomicVerb(verbExtractor.extractVerbFromModalVerbPhrase(subSentence, encodedTags, constantIndex));
        } else {
            semanticRelationData.setAtomicVerb(semanticalConstantTagAnalysisData.getConstantToken());
        }
    }

}