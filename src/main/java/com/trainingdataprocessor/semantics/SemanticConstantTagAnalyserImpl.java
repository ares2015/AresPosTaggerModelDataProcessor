package com.trainingdataprocessor.semantics;

import com.trainingdataprocessor.cache.ConstantWordsCache;
import com.trainingdataprocessor.data.semantics.SemanticalConstantTagAnalysisData;
import com.trainingdataprocessor.tags.EncodedTags;
import com.trainingdataprocessor.tags.Tags;

import java.util.*;

/**
 * Created by Oliver on 10/1/2016.
 */
public final class SemanticConstantTagAnalyserImpl implements SemanticConstantTagAnalyser {

    private ConstantWordsCache constantWordsCache;

    public SemanticConstantTagAnalyserImpl(ConstantWordsCache constantWordsCache) {
        this.constantWordsCache = constantWordsCache;
    }

    @Override
    public SemanticalConstantTagAnalysisData analyse(String constantTag, List<String> subSentence, List<String> encodedTags,
                                                     SemanticRelationConstantType constantType) {
        SemanticalConstantTagAnalysisData analysisData = new SemanticalConstantTagAnalysisData();
        boolean isConstantFound = false;
        int constantIndex = -1;
        for (int index = 0; index <= encodedTags.size() - 1; index++) {
            if (constantTag.equals(encodedTags.get(index))) {
                analysisData.setConstantIndex(index);
                analysisData.setConstantTag(constantTag);
                analysisData.setConstantToken(subSentence.get(index));
                constantIndex = index;
                isConstantFound = true;
            }
            if (isTokenPreposition(index, subSentence)) {
                if (isConstantFound) {
                    analysisData.setContainsAfterConstantPreposition(true);
                    analysisData.getAfterConstantTagPrepositionIndexes().add(index);
                } else {
                    analysisData.setContainsBeforeConstantPreposition(true);
                    analysisData.getBeforeConstantTagPrepositionIndexes().add(index);
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

    private boolean isTokenPreposition(int index, List<String> subSentence){
        return Tags.PREPOSITION.equals(constantWordsCache.getConstantWordsCache().get(subSentence.get(index))) ||
                Tags.TO.equals(constantWordsCache.getConstantWordsCache().get(subSentence.get(index)));
    }

}
