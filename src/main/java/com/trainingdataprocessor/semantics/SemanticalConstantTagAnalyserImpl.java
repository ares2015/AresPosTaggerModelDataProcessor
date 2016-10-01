package com.trainingdataprocessor.semantics;

import com.trainingdataprocessor.cache.ConstantWordsCache;
import com.trainingdataprocessor.data.semantics.SemanticalConstantTagAnalysisData;
import com.trainingdataprocessor.tags.EncodedTags;
import com.trainingdataprocessor.tags.Tags;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 10/1/2016.
 */
public final class SemanticalConstantTagAnalyserImpl implements SemanticalConstantTagAnalyser {

    private ConstantWordsCache constantWordsCache;

    public SemanticalConstantTagAnalyserImpl(ConstantWordsCache constantWordsCache) {
        this.constantWordsCache = constantWordsCache;
    }

    @Override
    public SemanticalConstantTagAnalysisData analyse(String constantTag, List<String> subSentence, List<String> encodedTags) {
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
            hasExtendedSubject = hasExtendedSubject(constantIndex);
            hasExtendedPredicate = hasExtendedPredicate(constantIndex, subSentence);

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

    private boolean hasExtendedSubject(int isIndex) {
        return isIndex > 1;
    }

    private boolean hasExtendedPredicate(int isIndex, List<String> subSentence) {
        return isIndex != subSentence.size() - 2;
    }
}
