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
        boolean isConstantFound = false;
        String constantToken = "";
        boolean isPresentTense = false;
        boolean containsBeforeConstantTagPreposition = false;
        boolean containsAfterConstantTagPreposition = false;
        boolean hasExtendedSubject = false;
        boolean hasExtendedPredicate = false;
        boolean hasVerbAuxiliaryVerbRelation = false;
        int constantIndex = -1;
        List<Integer> beforeConstantTagPrepositionIndexes = new ArrayList<>();
        List<Integer> afterConstantTagPrepositionIndexes = new ArrayList<>();

        for (int index = 0; index <= encodedTags.size() - 1; index++) {
            if (constantTag.equals(encodedTags.get(index))) {
                constantToken = subSentence.get(index);
                constantIndex = index;
                isConstantFound = true;
                isPresentTense = isPresentTense(subSentence.get(index), encodedTags.get(index));
            }
            if (Tags.PREPOSITION.equals(constantWordsCache.getConstantWordsCache().get(subSentence.get(index))) ||
                    Tags.TO.equals(constantWordsCache.getConstantWordsCache().get(subSentence.get(index)))) {
                if (isConstantFound) {
                    containsAfterConstantTagPreposition = true;
                    afterConstantTagPrepositionIndexes.add(index);
                } else {
                    containsBeforeConstantTagPreposition = true;
                    beforeConstantTagPrepositionIndexes.add(index);
                }
            }
        }
        if (isConstantFound) {

            hasExtendedSubject = hasExtendedSubject(constantIndex, constantType);
            hasExtendedPredicate = hasExtendedPredicate(constantIndex, subSentence, constantType);
            hasVerbAuxiliaryVerbRelation = hasVerbAuxiliaryVerbRelation(constantType);

            return new SemanticalConstantTagAnalysisData(constantIndex, constantTag, constantToken,
                    containsBeforeConstantTagPreposition, containsAfterConstantTagPreposition,
                    beforeConstantTagPrepositionIndexes, afterConstantTagPrepositionIndexes, isPresentTense,
                    hasExtendedSubject, hasExtendedPredicate, hasVerbAuxiliaryVerbRelation);
        }

        throw new IllegalStateException("IS pattern (subsentence) does not contain IS constant word.");
    }

    private boolean isPresentTense(String token, String encodedTag) {
        return ("is".equals(token) || "are".equals(token)) || EncodedTags.VERB.equals(encodedTag);
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

}
