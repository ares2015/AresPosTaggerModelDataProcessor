package com.trainingdataprocessor.relationships;

import com.trainingdataprocessor.cache.ConstantWordsCache;
import com.trainingdataprocessor.data.ISRelationshipData;
import com.trainingdataprocessor.data.RegexPatternIndexData;
import com.trainingdataprocessor.tags.Tags;

import java.util.ArrayList;
import java.util.List;

public class ISRelationshipsExtractor implements RelationshipsExtractor<ISRelationshipData> {

    private ConstantWordsCache constantWordsCache;

    public ISRelationshipsExtractor(ConstantWordsCache constantWordsCache) {
        this.constantWordsCache = constantWordsCache;
    }

    @Override
    public List<ISRelationshipData> extract(List<RegexPatternIndexData> isPatternIndexDataList, List<String> tokens) {
        List<ISRelationshipData> relationships = new ArrayList<>();

        for (RegexPatternIndexData indexData : isPatternIndexDataList) {
            String atomicSubject;
            String extendedSubject = null;
            String atomicPredicate;
            String extendedPredicate;
            String prepositionPredicate = null;
            int startIndex = indexData.getStartIndex();
            int endIndex = indexData.getEndIndex() + 1;
            List<String> subSentence = tokens.subList(startIndex, endIndex);

            IndexesLevelsTenseObject indexesLevelsTenseObject = getIndexesLevelsTenseObject(subSentence);
            atomicSubject = subSentence.get(indexesLevelsTenseObject.isIndex - 1);
            if (indexesLevelsTenseObject.hasExtendedSubject) {
                extendedSubject = getExtendedSubject(subSentence, indexesLevelsTenseObject.isIndex);
            }
            if (indexesLevelsTenseObject.containsPreposition) {
                atomicPredicate = subSentence.get(indexesLevelsTenseObject.prepositionIndexes.get(0) - 1);
                extendedPredicate = getExtendedPredicate(subSentence, indexesLevelsTenseObject.isIndex,
                        indexesLevelsTenseObject.prepositionIndexes.get(0));
                prepositionPredicate = getPrepositionPredicate(subSentence, indexesLevelsTenseObject.isIndex);
            } else {
                atomicPredicate = subSentence.get(subSentence.size() - 1);
                extendedPredicate = getExtendedPredicate(subSentence, indexesLevelsTenseObject.isIndex);
            }
            ISRelationshipData isRelationshipData = new ISRelationshipData(indexesLevelsTenseObject.isPresentTense, startIndex, endIndex,
                    atomicSubject, extendedSubject, atomicPredicate, extendedPredicate, prepositionPredicate);

            relationships.add(isRelationshipData);
        }
        return relationships;
    }

    private boolean hasExtendedSubject(int isIndex) {
        return isIndex > 1;
    }

    private boolean hasExtendedPredicate(int isIndex, List<String> subSentence) {
        return isIndex != subSentence.size() - 2;
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

    private IndexesLevelsTenseObject getIndexesLevelsTenseObject(List<String> subSentence) {
        boolean isConstantISFound = false;
        boolean isPresentTense = false;
        boolean containsPreposition = false;
        boolean hasExtendedSubject = false;
        boolean hasExtendedPredicate = false;
        int index = 0;
        int isIndex = -1;
        List<Integer> prepositionIndexes = new ArrayList<>();

        for (String token : subSentence) {
            if ("is".equals(token) || "are".equals(token)) {
                isIndex = index;
                isPresentTense = true;
                isConstantISFound = true;
            } else if ("was".equals(token) || "were".equals(token)) {
                isIndex = index;
                isConstantISFound = true;
            }
            if (Tags.PREPOSITION.equals(constantWordsCache.getConstantWordsCache().get(token)) ||
                    Tags.TO.equals(constantWordsCache.getConstantWordsCache().get(token))) {
                containsPreposition = true;
                prepositionIndexes.add(index);
            }
            index++;
        }
        if (isConstantISFound) {
            hasExtendedSubject = hasExtendedSubject(isIndex);
            hasExtendedPredicate = hasExtendedPredicate(isIndex, subSentence);

            return new IndexesLevelsTenseObject(isIndex, containsPreposition, prepositionIndexes, isPresentTense,
                    hasExtendedSubject, hasExtendedPredicate);
        }

        throw new IllegalStateException("IS pattern (subsentence) does not contain IS constant word.");
    }

    private class IndexesLevelsTenseObject {

        private int isIndex;

        boolean containsPreposition;

        private List<Integer> prepositionIndexes;

        private boolean isPresentTense;

        boolean hasExtendedSubject = false;

        boolean hasExtendedPredicate = false;

        IndexesLevelsTenseObject(int isIndex, boolean containsPreposition, List<Integer> prepositionIndexes, boolean isPresentTense,
                                 boolean hasExtendedSubject, boolean hasExtendedPredicate) {
            this.isIndex = isIndex;
            this.containsPreposition = containsPreposition;
            this.prepositionIndexes = prepositionIndexes;
            this.isPresentTense = isPresentTense;
            this.hasExtendedSubject = hasExtendedSubject;
            this.hasExtendedPredicate = hasExtendedPredicate;
        }
    }

}