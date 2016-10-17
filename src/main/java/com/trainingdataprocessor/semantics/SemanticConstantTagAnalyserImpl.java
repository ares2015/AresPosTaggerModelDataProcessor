package com.trainingdataprocessor.semantics;

import com.trainingdataprocessor.cache.ConstantWordsCache;
import com.trainingdataprocessor.data.semantics.SemanticalConstantTagAnalysisData;
import com.trainingdataprocessor.tags.EncodedTags;

import java.util.*;

/**
 * Created by Oliver on 10/1/2016.
 */
public final class SemanticConstantTagAnalyserImpl implements SemanticConstantTagAnalyser {

    @Override
    public SemanticalConstantTagAnalysisData analyse(String constantTag, List<String> subSentence, List<String> encodedTags,
                                                     SemanticRelationConstantType constantType) {
        SemanticalConstantTagAnalysisData analysisData = new SemanticalConstantTagAnalysisData();
        boolean isConstantFound = false;
        int constantIndex = -1;
        for (int index = 0; index <= encodedTags.size() - 1; index++) {
            String encodedTag = encodedTags.get(index);
            if (constantTag.equals(encodedTag)) {
                analysisData.setConstantIndex(index);
                analysisData.setConstantTag(constantTag);
                analysisData.setConstantToken(subSentence.get(index));
                constantIndex = index;
                isConstantFound = true;
            }
            if (isTagPreposition(encodedTag)) {
                if (isConstantFound) {
                    analysisData.setContainsAfterConstantPreposition(true);
                    analysisData.getAfterConstantTagPrepositionIndexes().add(index);
                } else {
                    analysisData.setContainsBeforeConstantPreposition(true);
                    analysisData.getBeforeConstantTagPrepositionIndexes().add(index);
                }
            }
            if (EncodedTags.ADVERB.equals(encodedTag)) {
                if (isConstantFound) {
                    analysisData.setContainsAfterConstantAdverb(true);
                    analysisData.getAfterConstantTagAdverbsIndexes().add(index);
                } else {
                    analysisData.setContainsBeforeConstantAdverb(true);
                    analysisData.getBeforeConstantTagAdverbsIndexes().add(index);
                }
            }
        }
        if (isConstantFound) {
            analysisData.setHasExtendedSubject(hasExtendedSubject(constantIndex, constantType));
            if (!(SemanticRelationConstantType.nounVerbRelationTypes.contains(constantType))) {
                analysisData.setHasExtendedPredicate(hasExtendedPredicate(constantIndex, subSentence, constantType));
            }
            analysisData.setHasVerbAuxiliaryVerbPhrase(hasVerbAuxiliaryVerbRelation(constantType));

            return analysisData;
        }
        throw new IllegalStateException("IS pattern (subsentence) does not contain IS constant word.");
    }

    private boolean hasExtendedSubject(int constantIndex, SemanticRelationConstantType constantType) {
        return constantIndex > SemanticRelationConstantType.constantTagAnalyserExtSubjectIndexMap.get(constantType);
    }

    private boolean hasExtendedPredicate(int constantIndex, List<String> subSentence, SemanticRelationConstantType constantType) {
        return constantIndex != subSentence.size() - SemanticRelationConstantType.constantTagAnalyserExtPredicateIndexMap.get(constantType);
    }

    private boolean hasVerbAuxiliaryVerbRelation(SemanticRelationConstantType constantType) {
        return SemanticRelationConstantType.auxiliaryTypes.contains(constantType);
    }

    private boolean isTagPreposition(String encodedTag) {
        return EncodedTags.PREPOSITION.equals((encodedTag)) || EncodedTags.TO.equals(encodedTag);
    }

}
