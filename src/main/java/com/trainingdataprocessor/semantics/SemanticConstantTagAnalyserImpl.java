package com.trainingdataprocessor.semantics;

import com.trainingdataprocessor.cache.ConstantWordsCache;
import com.trainingdataprocessor.data.semantics.SemanticalConstantTagAnalysisData;
import com.trainingdataprocessor.tags.EncodedTags;
import com.trainingdataprocessor.tags.Tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Oliver on 10/1/2016.
 */
public final class SemanticConstantTagAnalyserImpl implements SemanticConstantTagAnalyser {

    private Map<SemanticRelationConstantType, Integer> constantTagTypeExtSubjectIndexMap = new HashMap<SemanticRelationConstantType, Integer>();

    private Map<SemanticRelationConstantType, Integer> constantTagTypeExtPredicateIndexMap = new HashMap<SemanticRelationConstantType, Integer>();

    {
        constantTagTypeExtSubjectIndexMap.put(SemanticRelationConstantType.IS_ISNT, 1);
        constantTagTypeExtSubjectIndexMap.put(SemanticRelationConstantType.IS_NOT, 2);
        constantTagTypeExtSubjectIndexMap.put(SemanticRelationConstantType.VERB, 1);
        constantTagTypeExtSubjectIndexMap.put(SemanticRelationConstantType.VERB_DONT, 2);
        constantTagTypeExtSubjectIndexMap.put(SemanticRelationConstantType.VERB_DO_NOT, 3);
        constantTagTypeExtSubjectIndexMap.put(SemanticRelationConstantType.MODAL_VERB, 1);
        constantTagTypeExtSubjectIndexMap.put(SemanticRelationConstantType.MODAL_VERB_NOT, 2);

        constantTagTypeExtPredicateIndexMap.put(SemanticRelationConstantType.IS_ISNT, 2);
        constantTagTypeExtPredicateIndexMap.put(SemanticRelationConstantType.IS_NOT, 3);
        constantTagTypeExtPredicateIndexMap.put(SemanticRelationConstantType.VERB, 2);
        constantTagTypeExtPredicateIndexMap.put(SemanticRelationConstantType.VERB_DONT, 3);
        constantTagTypeExtPredicateIndexMap.put(SemanticRelationConstantType.VERB_DO_NOT, 4);
        constantTagTypeExtPredicateIndexMap.put(SemanticRelationConstantType.MODAL_VERB, 2);
        constantTagTypeExtPredicateIndexMap.put(SemanticRelationConstantType.MODAL_VERB_NOT, 3);

    }

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

            return new SemanticalConstantTagAnalysisData(constantIndex, constantTag, constantToken,
                    containsBeforeConstantTagPreposition, containsAfterConstantTagPreposition,
                    beforeConstantTagPrepositionIndexes, afterConstantTagPrepositionIndexes, isPresentTense,
                    hasExtendedSubject, hasExtendedPredicate);
        }

        throw new IllegalStateException("IS pattern (subsentence) does not contain IS constant word.");
    }

    private boolean isPresentTense(String token, String encodedTag) {
        return ("is".equals(token) || "are".equals(token)) || EncodedTags.VERB.equals(encodedTag);
    }

    private boolean hasExtendedSubject(int constantIndex, SemanticRelationConstantType constantType) {
        return constantIndex > constantTagTypeExtSubjectIndexMap.get(constantType);
    }

    private boolean hasExtendedPredicate(int constantIndex, List<String> subSentence, SemanticRelationConstantType constantType) {
        return constantIndex != subSentence.size() - constantTagTypeExtPredicateIndexMap.get(constantType);
    }

}
