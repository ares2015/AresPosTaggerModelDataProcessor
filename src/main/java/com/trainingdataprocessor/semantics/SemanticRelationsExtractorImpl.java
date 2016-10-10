package com.trainingdataprocessor.semantics;

import com.trainingdataprocessor.data.semantics.SemanticRelationData;
import com.trainingdataprocessor.data.RegexPatternIndexData;
import com.trainingdataprocessor.data.semantics.SemanticalConstantTagAnalysisData;
import com.trainingdataprocessor.tags.EncodedTags;

import java.util.ArrayList;
import java.util.List;

public class SemanticRelationsExtractorImpl implements SemanticRelationsExtractor<SemanticRelationData> {

    private SemanticConstantTagAnalyser semanticConstantTagAnalyser;

    public SemanticRelationsExtractorImpl(SemanticConstantTagAnalyser semanticConstantTagAnalyser) {
        this.semanticConstantTagAnalyser = semanticConstantTagAnalyser;
    }

    @Override
    public List<SemanticRelationData> extract(String constant, List<RegexPatternIndexData> isPatternIndexDataList, List<String> tokens, List<String> encodedTags,
                                              SemanticRelationConstantType constantType) {
        List<SemanticRelationData> relationships = new ArrayList<>();

        for (RegexPatternIndexData indexData : isPatternIndexDataList) {
            SemanticRelationData semanticRelationData = new SemanticRelationData();

            semanticRelationData.setStartIndex(indexData.getStartIndex());
            semanticRelationData.setEndIndex(indexData.getEndIndex());

            List<String> subSentence = tokens.subList(indexData.getStartIndex(), indexData.getEndIndex() + 1);

            SemanticalConstantTagAnalysisData semanticalConstantTagAnalysisData = semanticConstantTagAnalyser.analyse(constant, subSentence, encodedTags, constantType);
            semanticRelationData.setPresentTense(semanticalConstantTagAnalysisData.isPresentTense());
            processSubject(subSentence, semanticRelationData, semanticalConstantTagAnalysisData, constantType);
            processPredicate(subSentence, encodedTags, semanticRelationData, semanticalConstantTagAnalysisData, constantType);
            if (semanticalConstantTagAnalysisData.hasVerbAuxiliaryVerbPhrase()) {
                semanticRelationData.setVerbAuxiliaryVerbPhrase(extractVerbAuxiliaryVerbPhrase(subSentence, encodedTags,
                        semanticalConstantTagAnalysisData.getConstantIndex(), constantType));
            }
            relationships.add(semanticRelationData);
        }
        return relationships;
    }

    private void processSubject(List<String> subSentence, SemanticRelationData semanticRelationData,
                                SemanticalConstantTagAnalysisData semanticalConstantTagAnalysisData, SemanticRelationConstantType constantType) {
        if (semanticalConstantTagAnalysisData.containsBeforeConstantTagPreposition()) {
            semanticRelationData.setExtendedSubject(extractExtendedSubject(subSentence, semanticalConstantTagAnalysisData.getConstantIndex(), constantType));
        } else {
            semanticRelationData.setAtomicSubject(subSentence.get(semanticalConstantTagAnalysisData.getConstantIndex() -
                    SemanticRelationConstantType.relationsExtractorAtomicSubjectIndexMap.get(constantType)));
            if (semanticalConstantTagAnalysisData.hasExtendedSubject()) {
                semanticRelationData.setExtendedSubject(extractExtendedSubject(subSentence, semanticalConstantTagAnalysisData.getConstantIndex(), constantType));
            }
        }
    }

    private void processPredicate(List<String> subSentence, List<String> encodedTags, SemanticRelationData semanticRelationData,
                                  SemanticalConstantTagAnalysisData semanticalConstantTagAnalysisData, SemanticRelationConstantType constantType) {
        if (semanticalConstantTagAnalysisData.containsAfterConstantTagPreposition()) {
            semanticRelationData.setAtomicPredicate(subSentence.get(semanticalConstantTagAnalysisData.getAfterConstantTagPrepositionIndexes().get(0) - 1));
            semanticRelationData.setExtendedPredicate(extractExtendedPredicate(subSentence, encodedTags, semanticalConstantTagAnalysisData.getConstantIndex(),
                    semanticalConstantTagAnalysisData.getAfterConstantTagPrepositionIndexes().get(0), constantType));
            semanticRelationData.setPrepositionPredicate(extractPrepositionPredicate(subSentence, encodedTags,
                    semanticalConstantTagAnalysisData.getConstantIndex(), constantType));
        } else {
            semanticRelationData.setAtomicPredicate(subSentence.get(subSentence.size() - 1));
            semanticRelationData.setExtendedPredicate(extractExtendedPredicate(subSentence, encodedTags, semanticalConstantTagAnalysisData.getConstantIndex(), constantType));
        }
    }


    private String extractExtendedSubject(List<String> subSentence, int constantIndex, SemanticRelationConstantType constantType) {
        String extendedSubject = "";
        int endIndex = constantIndex - SemanticRelationConstantType.relationsExtractorExtSubjectIndexMap.get(constantType);
        for (int i = 0; i < endIndex; i++) {
            if (i < endIndex - 1)
                extendedSubject += subSentence.get(i) + " ";
            else
                extendedSubject += subSentence.get(i);
        }
        return extendedSubject;
    }

    private String extractExtendedPredicate(List<String> subSentence, List<String> encodedTags,
                                            int constantIndex, SemanticRelationConstantType constantType) {
        String extendedPredicate = "";
        int startIndex = getStartIndexForPredicateExtraction(constantIndex, encodedTags, constantType);
        for (int i = startIndex; i <= subSentence.size() - 1; i++) {
            if (i < subSentence.size() - 1)
                extendedPredicate += subSentence.get(i) + " ";
            else
                extendedPredicate += subSentence.get(i);
        }
        return extendedPredicate;
    }

    private String extractExtendedPredicate(List<String> subSentence, List<String> encodedTags,
                                            int constantIndex, int prepositionIndex, SemanticRelationConstantType constantType) {
        String extendedPredicate = "";
        int startIndex = getStartIndexForPredicateExtraction(constantIndex, encodedTags, constantType);
        for (int i = startIndex; i < prepositionIndex; i++) {
            if (i < subSentence.size() - 1)
                extendedPredicate += subSentence.get(i) + " ";
            else
                extendedPredicate += subSentence.get(i);
        }
        return extendedPredicate;
    }

    private String extractPrepositionPredicate(List<String> subSentence, List<String> encodedTags, int constantIndex,
                                               SemanticRelationConstantType constantType) {
        String prepositionPredicate = "";
        int startIndex = getStartIndexForPredicateExtraction(constantIndex, encodedTags, constantType);
        for (int i = startIndex; i <= subSentence.size() - 1; i++) {
            if (i < subSentence.size() - 1)
                prepositionPredicate += subSentence.get(i) + " ";
            else
                prepositionPredicate += subSentence.get(i);
        }
        return prepositionPredicate;
    }

    private String extractVerbAuxiliaryVerbPhrase(List<String> subSentence, List<String> encodedTags, int constantIndex, SemanticRelationConstantType constantType) {
        String verbAuxiliaryVerbPhrase = "";
        int verbIndex = getVerbIndex(encodedTags, constantIndex);
        if (SemanticRelationConstantType.MODAL_VERB == constantType || SemanticRelationConstantType.MODAL_VERB_NOT == constantType) {
            for (int i = constantIndex; i <= verbIndex; i++) {
                if (i < verbIndex) {
                    verbAuxiliaryVerbPhrase += subSentence.get(i) + " ";
                } else {
                    verbAuxiliaryVerbPhrase += subSentence.get(i);
                }
            }
        } else if (SemanticRelationConstantType.VERB_DONT == constantType) {
            verbAuxiliaryVerbPhrase = subSentence.get(constantIndex - 1) + " " + subSentence.get(constantIndex);
        } else if (SemanticRelationConstantType.VERB_DO_NOT == constantType) {
            verbAuxiliaryVerbPhrase = subSentence.get(constantIndex - 2) + " " + subSentence.get(constantIndex - 1) + " " + subSentence.get(constantIndex);
        } else if (SemanticRelationConstantType.IS_NOT == constantType) {
            verbAuxiliaryVerbPhrase = subSentence.get(constantIndex) + " " + subSentence.get(constantIndex + 1);
        }
        return verbAuxiliaryVerbPhrase;
    }

    private int getStartIndexForPredicateExtraction(int constantIndex, List<String> encodedTags, SemanticRelationConstantType constantType) {
        if (SemanticRelationConstantType.MODAL_VERB == constantType || SemanticRelationConstantType.MODAL_VERB_NOT == constantType) {
            return getVerbIndex(encodedTags, constantIndex) + 1;
        } else {
            return constantIndex + 1;
        }
    }

    private int getVerbIndex(List<String> encodedTags, int modalVerbIndex) {
        for (int i = modalVerbIndex; i <= encodedTags.size() - 1; i++) {
            if (EncodedTags.VERB.equals(encodedTags.get(i))) {
                return i;
            }
        }
        throw new IllegalStateException("There is no verb in the list of tags.");
    }

}