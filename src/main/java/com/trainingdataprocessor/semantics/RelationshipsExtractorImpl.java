package com.trainingdataprocessor.semantics;

import com.trainingdataprocessor.data.semantics.RelationshipData;
import com.trainingdataprocessor.data.RegexPatternIndexData;
import com.trainingdataprocessor.data.semantics.SemanticalConstantTagAnalysisData;

import java.util.ArrayList;
import java.util.List;

public class RelationshipsExtractorImpl implements RelationshipsExtractor<RelationshipData> {

    private String constant;
    private SemanticConstantTagAnalyser semanticConstantTagAnalyser;

    public RelationshipsExtractorImpl(SemanticConstantTagAnalyser semanticConstantTagAnalyser) {
        this.semanticConstantTagAnalyser = semanticConstantTagAnalyser;
    }

    @Override
    public List<RelationshipData> extract(String constant, List<RegexPatternIndexData> isPatternIndexDataList, List<String> tokens, List<String> encodedTags,
                                          SemanticRelationConstantType constantType) {
        List<RelationshipData> relationships = new ArrayList<>();

        for (RegexPatternIndexData indexData : isPatternIndexDataList) {
            RelationshipData relationshipData = new RelationshipData();

            relationshipData.setStartIndex(indexData.getStartIndex());
            relationshipData.setEndIndex(indexData.getEndIndex());

            List<String> subSentence = tokens.subList(indexData.getStartIndex(), indexData.getEndIndex() + 1);

            SemanticalConstantTagAnalysisData semanticalConstantTagAnalysisData = semanticConstantTagAnalyser.analyse(constant,
                    subSentence, encodedTags,
                    constantType);
            relationshipData.setPresentTense(semanticalConstantTagAnalysisData.isPresentTense());
            processSubject(subSentence, relationshipData, semanticalConstantTagAnalysisData);
            processPredicate(subSentence, relationshipData, semanticalConstantTagAnalysisData);

            relationships.add(relationshipData);
        }
        return relationships;
    }

    private void processSubject(List<String> subSentence, RelationshipData relationshipData,
                                SemanticalConstantTagAnalysisData semanticalConstantTagAnalysisData) {
        if (semanticalConstantTagAnalysisData.containsBeforeConstantTagPreposition()) {
            relationshipData.setExtendedSubject(getExtendedSubject(subSentence, semanticalConstantTagAnalysisData.getConstantIndex()));
        } else {
            relationshipData.setAtomicSubject(subSentence.get(semanticalConstantTagAnalysisData.getConstantIndex() - 1));
            if (semanticalConstantTagAnalysisData.hasExtendedSubject()) {
                relationshipData.setExtendedSubject(getExtendedSubject(subSentence, semanticalConstantTagAnalysisData.getConstantIndex()));
            }
        }
    }

    private void processPredicate(List<String> subSentence, RelationshipData relationshipData,
                                  SemanticalConstantTagAnalysisData semanticalConstantTagAnalysisData) {
        if (semanticalConstantTagAnalysisData.containsAfterConstantTagPreposition()) {
            relationshipData.setAtomicPredicate(subSentence.get(semanticalConstantTagAnalysisData.getAfterConstantTagPrepositionIndexes().get(0) - 1));
            relationshipData.setExtendedPredicate(getExtendedPredicate(subSentence, semanticalConstantTagAnalysisData.getConstantIndex(),
                    semanticalConstantTagAnalysisData.getAfterConstantTagPrepositionIndexes().get(0)));
            relationshipData.setPrepositionPredicate(getPrepositionPredicate(subSentence, semanticalConstantTagAnalysisData.getConstantIndex()));
        } else {
            relationshipData.setAtomicPredicate(subSentence.get(subSentence.size() - 1));
            relationshipData.setExtendedPredicate(getExtendedPredicate(subSentence, semanticalConstantTagAnalysisData.getConstantIndex()));
        }
    }


    private String getExtendedSubject(List<String> subSentence, int isIndex) {
        String extendedSubject = "";
        for (int i = 0; i < isIndex; i++) {
            if (i < isIndex - 1)
                extendedSubject += subSentence.get(i) + " ";
            else
                extendedSubject += subSentence.get(i);
        }
        return extendedSubject;
    }

    private String getExtendedPredicate(List<String> subSentence, int isIndex) {
        String extendedPredicate = "";
        for (int i = isIndex + 1; i <= subSentence.size() - 1; i++) {
            if (i < subSentence.size() - 1)
                extendedPredicate += subSentence.get(i) + " ";
            else
                extendedPredicate += subSentence.get(i);
        }
        return extendedPredicate;
    }

    private String getExtendedPredicate(List<String> subSentence, int isIndex, int prepositionIndex) {
        String extendedPredicate = "";
        for (int i = isIndex + 1; i < prepositionIndex; i++) {
            if (i < subSentence.size() - 1)
                extendedPredicate += subSentence.get(i) + " ";
            else
                extendedPredicate += subSentence.get(i);
        }
        return extendedPredicate;
    }

    private String getPrepositionPredicate(List<String> subSentence, int isIndex) {
        String prepositionPredicate = "";
        for (int i = isIndex + 1; i <= subSentence.size() - 1; i++) {
            if (i < subSentence.size() - 1)
                prepositionPredicate += subSentence.get(i) + " ";
            else
                prepositionPredicate += subSentence.get(i);
        }
        return prepositionPredicate;
    }

}