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
            ISRelationshipData isRelationshipData = new ISRelationshipData();

            isRelationshipData.setStartIndex(indexData.getStartIndex());
            isRelationshipData.setEndIndex(indexData.getEndIndex());

            List<String> subSentence = tokens.subList(indexData.getStartIndex(), indexData.getEndIndex() + 1);

            IndexesLevelsTenseObject indexesLevelsTenseObject = getIndexesLevelsTenseObject(subSentence);
            isRelationshipData.setPresentTense(indexesLevelsTenseObject.isPresentTense);
            processSubject(subSentence, isRelationshipData, indexesLevelsTenseObject);
            processPredicate(subSentence, isRelationshipData, indexesLevelsTenseObject);

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

    private void processSubject(List<String> subSentence, ISRelationshipData isRelationshipData,
                                IndexesLevelsTenseObject indexesLevelsTenseObject) {
        if (indexesLevelsTenseObject.containsBeforeISPreposition) {
            isRelationshipData.setExtendedSubject(getExtendedSubject(subSentence, indexesLevelsTenseObject.isIndex));
        } else {
            isRelationshipData.setAtomicSubject(subSentence.get(indexesLevelsTenseObject.isIndex - 1));
            if (indexesLevelsTenseObject.hasExtendedSubject) {
                isRelationshipData.setExtendedSubject(getExtendedSubject(subSentence, indexesLevelsTenseObject.isIndex));
            }
        }
    }

    private void processPredicate(List<String> subSentence, ISRelationshipData isRelationshipData,
                                  IndexesLevelsTenseObject indexesLevelsTenseObject) {
        if (indexesLevelsTenseObject.containsAfterISPrepositionWhDet) {
            isRelationshipData.setAtomicPredicate(subSentence.get(indexesLevelsTenseObject.afterISprepositionIndexes.get(0) - 1));
            isRelationshipData.setExtendedPredicate(getExtendedPredicate(subSentence, indexesLevelsTenseObject.isIndex,
                    indexesLevelsTenseObject.afterISprepositionIndexes.get(0)));
            isRelationshipData.setPrepositionWhDetPredicate(getPrepositionWhDetPredicate(subSentence, indexesLevelsTenseObject.isIndex));
        } else {
            isRelationshipData.setAtomicPredicate(subSentence.get(subSentence.size() - 1));
            isRelationshipData.setExtendedPredicate(getExtendedPredicate(subSentence, indexesLevelsTenseObject.isIndex));
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

    private String getPrepositionWhDetPredicate(List<String> subSentence, int isIndex) {
        String prepositionWhDetPredicate = "";
        for (int i = isIndex + 1; i <= subSentence.size() - 1; i++) {
            if (i < subSentence.size() - 1)
                prepositionWhDetPredicate += subSentence.get(i) + " ";
            else
                prepositionWhDetPredicate += subSentence.get(i);
        }
        return prepositionWhDetPredicate;
    }

    private IndexesLevelsTenseObject getIndexesLevelsTenseObject(List<String> subSentence) {
        boolean isConstantISFound = false;
        boolean isPresentTense = false;
        boolean containsBeforeISPreposition = false;
        boolean containsAfterISPrepositionWhDet = false;
        boolean hasExtendedSubject = false;
        boolean hasExtendedPredicate = false;
        int index = 0;
        int isIndex = -1;
        List<Integer> beforeISprepositionIndexes = new ArrayList<>();
        List<Integer> afterISprepositionWhDetIndexes = new ArrayList<>();

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
                    Tags.TO.equals(constantWordsCache.getConstantWordsCache().get(token)) ||
                    Tags.WH_DETERMINER.equals(constantWordsCache.getConstantWordsCache().get(token))) {
                if (isConstantISFound) {
                    containsAfterISPrepositionWhDet = true;
                    afterISprepositionWhDetIndexes.add(index);
                } else {
                    containsBeforeISPreposition = true;
                    beforeISprepositionIndexes.add(index);
                }
            }
            index++;
        }
        if (isConstantISFound) {
            hasExtendedSubject = hasExtendedSubject(isIndex);
            hasExtendedPredicate = hasExtendedPredicate(isIndex, subSentence);

            return new IndexesLevelsTenseObject(isIndex, containsBeforeISPreposition, containsAfterISPrepositionWhDet,
                    beforeISprepositionIndexes, afterISprepositionWhDetIndexes, isPresentTense,
                    hasExtendedSubject, hasExtendedPredicate);
        }

        throw new IllegalStateException("IS pattern (subsentence) does not contain IS constant word.");
    }

    private class IndexesLevelsTenseObject {

        private int isIndex;

        private boolean containsBeforeISPreposition;

        private boolean containsAfterISPrepositionWhDet;

        private List<Integer> beforeISprepositionIndexes;

        private List<Integer> afterISprepositionIndexes;

        private boolean isPresentTense;

        boolean hasExtendedSubject = false;

        boolean hasExtendedPredicate = false;

        IndexesLevelsTenseObject(int isIndex, boolean containsBeforeISPreposition, boolean containsAfterISPrepositionWhDet,
                                 List<Integer> beforeISprepositionIndexes,
                                 List<Integer> afterISprepositionIndexes, boolean isPresentTense,
                                 boolean hasExtendedSubject, boolean hasExtendedPredicate) {
            this.isIndex = isIndex;
            this.containsBeforeISPreposition = containsBeforeISPreposition;
            this.containsAfterISPrepositionWhDet = containsAfterISPrepositionWhDet;
            this.beforeISprepositionIndexes = beforeISprepositionIndexes;
            this.afterISprepositionIndexes = afterISprepositionIndexes;
            this.isPresentTense = isPresentTense;
            this.hasExtendedSubject = hasExtendedSubject;
            this.hasExtendedPredicate = hasExtendedPredicate;
        }
    }
}