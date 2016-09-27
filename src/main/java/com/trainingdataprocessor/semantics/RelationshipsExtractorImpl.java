package com.trainingdataprocessor.semantics;

import com.trainingdataprocessor.cache.ConstantWordsCache;
import com.trainingdataprocessor.data.RelationshipData;
import com.trainingdataprocessor.data.RegexPatternIndexData;
import com.trainingdataprocessor.tags.EncodedTags;
import com.trainingdataprocessor.tags.Tags;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RelationshipsExtractorImpl implements RelationshipsExtractor<RelationshipData> {

    private ConstantWordsCache constantWordsCache;

    private String constant;

    public RelationshipsExtractorImpl(ConstantWordsCache constantWordsCache, String constant) {
        this.constantWordsCache = constantWordsCache;
        this.constant = constant;
    }

    @Override
    public List<RelationshipData> extract(List<RegexPatternIndexData> isPatternIndexDataList, List<String> tokens, List<String> encodedTags) {
        List<RelationshipData> relationships = new ArrayList<>();

        for (RegexPatternIndexData indexData : isPatternIndexDataList) {
            RelationshipData relationshipData = new RelationshipData();

            relationshipData.setStartIndex(indexData.getStartIndex());
            relationshipData.setEndIndex(indexData.getEndIndex());

            List<String> subSentence = tokens.subList(indexData.getStartIndex(), indexData.getEndIndex() + 1);

            IndexesLevelsTenseObject indexesLevelsTenseObject = getIndexesLevelsTenseObject(subSentence, encodedTags);
            relationshipData.setPresentTense(indexesLevelsTenseObject.isPresentTense);
            processSubject(subSentence, relationshipData, indexesLevelsTenseObject);
            processPredicate(subSentence, relationshipData, indexesLevelsTenseObject);

            relationships.add(relationshipData);
        }
        return relationships;
    }

    private boolean hasExtendedSubject(int isIndex) {
        return isIndex > 1;
    }

    private boolean hasExtendedPredicate(int isIndex, List<String> subSentence) {
        return isIndex != subSentence.size() - 2;
    }

    private void processSubject(List<String> subSentence, RelationshipData relationshipData,
                                IndexesLevelsTenseObject indexesLevelsTenseObject) {
        if (indexesLevelsTenseObject.containsBeforeISPreposition) {
            relationshipData.setExtendedSubject(getExtendedSubject(subSentence, indexesLevelsTenseObject.constantIndex));
        } else {
            relationshipData.setAtomicSubject(subSentence.get(indexesLevelsTenseObject.constantIndex - 1));
            if (indexesLevelsTenseObject.hasExtendedSubject) {
                relationshipData.setExtendedSubject(getExtendedSubject(subSentence, indexesLevelsTenseObject.constantIndex));
            }
        }
    }

    private void processPredicate(List<String> subSentence, RelationshipData relationshipData,
                                  IndexesLevelsTenseObject indexesLevelsTenseObject) {
        if (indexesLevelsTenseObject.containsAfterISPreposition) {
            relationshipData.setAtomicPredicate(subSentence.get(indexesLevelsTenseObject.afterISprepositionIndexes.get(0) - 1));
            relationshipData.setExtendedPredicate(getExtendedPredicate(subSentence, indexesLevelsTenseObject.constantIndex,
                    indexesLevelsTenseObject.afterISprepositionIndexes.get(0)));
            relationshipData.setPrepositionPredicate(getPrepositionPredicate(subSentence, indexesLevelsTenseObject.constantIndex));
        } else {
            relationshipData.setAtomicPredicate(subSentence.get(subSentence.size() - 1));
            relationshipData.setExtendedPredicate(getExtendedPredicate(subSentence, indexesLevelsTenseObject.constantIndex));
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

    private IndexesLevelsTenseObject getIndexesLevelsTenseObject(List<String> subSentence, List<String> encodedTags) {
        boolean isConstantFound = false;
        String constantTag = "";
        String constantToken = "";
        boolean isPresentTense = false;
        boolean containsBeforeISPreposition = false;
        boolean containsAfterISPreposition = false;
        boolean hasExtendedSubject = false;
        boolean hasExtendedPredicate = false;
        int constantIndex = -1;
        List<Integer> beforeISprepositionIndexes = new ArrayList<>();
        List<Integer> afterISprepositionIndexes = new ArrayList<>();

        for(int index = 0; index <= encodedTags.size() - 1; index++){
            if(constant.equals(encodedTags.get(index))){
                constantTag = encodedTags.get(index);
                constantToken = subSentence.get(index);
                constantIndex = index;
                isConstantFound = true;
                isPresentTense = isPresentTense(subSentence.get(index), encodedTags.get(index));
            }
            if (Tags.PREPOSITION.equals(constantWordsCache.getConstantWordsCache().get(subSentence.get(index))) ||
                    Tags.TO.equals(constantWordsCache.getConstantWordsCache().get(subSentence.get(index)))) {
                if (isConstantFound) {
                    containsAfterISPreposition = true;
                    afterISprepositionIndexes.add(index);
                } else {
                    containsBeforeISPreposition = true;
                    beforeISprepositionIndexes.add(index);
                }
            }
        }

//        for (String token : subSentence) {
//            if ("is".equals(token) || "are".equals(token)) {
//                constantIndex = index;
//                isConstantFound = true;
//            } else if ("was".equals(token) || "were".equals(token)) {
//                constantIndex = index;
//                isConstantFound = true;
//            }
//            if (Tags.PREPOSITION.equals(constantWordsCache.getConstantWordsCache().get(token)) ||
//                    Tags.TO.equals(constantWordsCache.getConstantWordsCache().get(token))) {
//                if (isConstantFound) {
//                    containsAfterISPreposition = true;
//                    afterISprepositionIndexes.add(index);
//                } else {
//                    containsBeforeISPreposition = true;
//                    beforeISprepositionIndexes.add(index);
//                }
//            }
//            index++;
//        }
        if (isConstantFound) {
            hasExtendedSubject = hasExtendedSubject(constantIndex);
            hasExtendedPredicate = hasExtendedPredicate(constantIndex, subSentence);

            return new IndexesLevelsTenseObject(constantIndex, constantTag, constantToken, containsBeforeISPreposition, containsAfterISPreposition,
                    beforeISprepositionIndexes, afterISprepositionIndexes, isPresentTense,
                    hasExtendedSubject, hasExtendedPredicate);
        }

        throw new IllegalStateException("IS pattern (subsentence) does not contain IS constant word.");
    }

    private boolean isPresentTense(String token, String encodedTag){
        return ("is".equals(token) || "are".equals(token)) || EncodedTags.VERB.equals(encodedTag);
    }

    private class IndexesLevelsTenseObject {

        private int constantIndex;

        private String constantTag = "";

        private String constantToken = "";

        private boolean containsBeforeISPreposition;

        private boolean containsAfterISPreposition;

        private List<Integer> beforeISprepositionIndexes;

        private List<Integer> afterISprepositionIndexes;

        private boolean isPresentTense;

        boolean hasExtendedSubject = false;

        boolean hasExtendedPredicate = false;

        public IndexesLevelsTenseObject(int constantIndex, String constantTag, String constantToken, boolean containsBeforeISPreposition,
                                        boolean containsAfterISPreposition, List<Integer> beforeISprepositionIndexes, List<Integer> afterISprepositionIndexes,
                                        boolean isPresentTense, boolean hasExtendedSubject, boolean hasExtendedPredicate) {
            this.constantIndex = constantIndex;
            this.constantTag = constantTag;
            this.constantToken = constantToken;
            this.containsBeforeISPreposition = containsBeforeISPreposition;
            this.containsAfterISPreposition = containsAfterISPreposition;
            this.beforeISprepositionIndexes = beforeISprepositionIndexes;
            this.afterISprepositionIndexes = afterISprepositionIndexes;
            this.isPresentTense = isPresentTense;
            this.hasExtendedSubject = hasExtendedSubject;
            this.hasExtendedPredicate = hasExtendedPredicate;
        }
    }
}