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
            String level1object1 = "";
            String level1object2 = "";
            String level2object1 = "";
            String level2object2 = "";
            String level3object1 = "";
            String level3object2 = "";
            String level4object1 = "";
            String level4object2 = "";
            String level5object1 = "";
            String level5object2 = "";

            String extendedSubStringBeforeIsIndex = "";
            String extendedSubStringAfterIsIndex = "";


            int startIndex = indexData.getStartIndex();
            int endIndex = indexData.getEndIndex() + 1;
            List<String> subSentence = tokens.subList(startIndex, endIndex);

            IndexesLevelsTenseObject indexesLevelsTenseObject = getIndexesLevelsTenseObject(subSentence);
            extendedSubStringBeforeIsIndex = getSubStringFromZeroToISindex(subSentence, indexesLevelsTenseObject.isIndex);
            extendedSubStringAfterIsIndex = getSubStringFromISindexToEnd(subSentence, indexesLevelsTenseObject.isIndex);

            level1object1 = subSentence.get(indexesLevelsTenseObject.isIndex - 1);
            if (indexesLevelsTenseObject.containsPreposition) {
                level1object2 = subSentence.get(indexesLevelsTenseObject.prepositionIndexes.get(0) - 1);
            } else {
                level1object2 = subSentence.get(subSentence.size() - 1);
            }
            if (indexesLevelsTenseObject.hasLevel2) {
                level2object1 = extendedSubStringBeforeIsIndex;
                if (indexesLevelsTenseObject.containsPreposition) {
                    level2object2 = subSentence.get(indexesLevelsTenseObject.prepositionIndexes.get(0) - 1);
                } else {
                    level2object2 = subSentence.get(subSentence.size() - 1);
                }
            }
            if (indexesLevelsTenseObject.hasLevel3) {
                if (indexesLevelsTenseObject.hasLevel2) {
                    level3object1 = extendedSubStringBeforeIsIndex;
                } else {
                    level3object1 = level1object1;
                }
                if (indexesLevelsTenseObject.containsPreposition) {
                    level3object2 = getSubStringFromISindexToPrepositionIndex(subSentence, indexesLevelsTenseObject.isIndex,
                            indexesLevelsTenseObject.prepositionIndexes.get(0));
                } else {
                    level3object2 = extendedSubStringAfterIsIndex;
                }
            }
            if (indexesLevelsTenseObject.hasLevel4) {
                if (indexesLevelsTenseObject.hasLevel2) {
                    level4object1 = extendedSubStringBeforeIsIndex;
                } else {
                    level4object1 = level1object1;
                }
                if (indexesLevelsTenseObject.hasLevel5) {
                    level4object2 = getSubStringFromISindexToPrepositionIndex(subSentence, indexesLevelsTenseObject.isIndex,
                            indexesLevelsTenseObject.prepositionIndexes.get(1));
                } else {
                    level4object2 = extendedSubStringAfterIsIndex;
                }
            }
            if (indexesLevelsTenseObject.hasLevel5) {
                level5object1 = extendedSubStringBeforeIsIndex;
                level5object2 = extendedSubStringAfterIsIndex;
            }
            ISRelationshipData isRelationshipData = new ISRelationshipData(indexesLevelsTenseObject.isPresentTense, startIndex, endIndex,
                    level1object1, level1object2, level2object1, level2object2, level3object1, level3object2, level4object1, level4object2,
                    level5object1, level5object2);

            relationships.add(isRelationshipData);

        }
        return relationships;
    }

    private boolean subSentenceHasLevel2(int isIndex) {
        return isIndex > 1;
    }

    private boolean subSentenceHasLevel3(int isIndex, List<String> subSentence) {
        return isIndex != subSentence.size() - 2;
    }

    private String getSubStringFromZeroToISindex(List<String> subSentence, int isIndex) {
        String subString = "";
        for (int i = 0; i < isIndex; i++) {
            if (i < isIndex - 1)
                subString += subSentence.get(i) + " ";
            else
                subString += subSentence.get(i);
        }
        return subString;
    }

    private String getSubStringFromISindexToEnd(List<String> subSentence, int isIndex) {
        String subString = "";
        for (int i = isIndex + 1; i <= subSentence.size() - 1; i++) {
            if (i < subSentence.size() - 1)
                subString += subSentence.get(i) + " ";
            else
                subString += subSentence.get(i);
        }
        return subString;
    }

    private String getSubStringFromISindexToPrepositionIndex(List<String> subSentence, int isIndex, int prepositionIndex) {
        String subString = "";
        for (int i = isIndex + 1; i < prepositionIndex; i++) {
            if (i < subSentence.size() - 1)
                subString += subSentence.get(i) + " ";
            else
                subString += subSentence.get(i);
        }
        return subString;
    }

    private class IndexesLevelsTenseObject {

        private int isIndex;

        boolean containsPreposition;

        private List<Integer> prepositionIndexes;

        private boolean isPresentTense;

        private boolean hasLevel2 = false;

        private boolean hasLevel3 = false;

        private boolean hasLevel4 = false;

        private boolean hasLevel5 = false;

        public IndexesLevelsTenseObject(int isIndex, boolean containsPreposition, List<Integer> prepositionIndexes,
                                        boolean isPresentTense, boolean hasLevel2, boolean hasLevel3, boolean hasLevel4, boolean hasLevel5) {
            this.isIndex = isIndex;
            this.containsPreposition = containsPreposition;
            this.prepositionIndexes = prepositionIndexes;
            this.isPresentTense = isPresentTense;
            this.hasLevel2 = hasLevel2;
            this.hasLevel3 = hasLevel3;
            this.hasLevel4 = hasLevel4;
            this.hasLevel5 = hasLevel5;
        }
    }

    private IndexesLevelsTenseObject getIndexesLevelsTenseObject(List<String> subSentence) {
        boolean isConstantISFound = false;
        boolean isPresentTense = false;
        boolean containsPreposition = false;
        boolean hasLevel2 = false;
        boolean hasLevel3 = false;
        boolean hasLevel4 = false;
        boolean hasLevel5 = false;
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
            hasLevel2 = subSentenceHasLevel2(isIndex);
            hasLevel3 = subSentenceHasLevel3(isIndex, subSentence);

            if (prepositionIndexes.size() == 1) {
                hasLevel4 = true;
            } else if (prepositionIndexes.size() == 2) {
                hasLevel4 = true;
                hasLevel5 = true;
            }
            return new IndexesLevelsTenseObject(isIndex, containsPreposition, prepositionIndexes, isPresentTense,
                    hasLevel2, hasLevel3, hasLevel4, hasLevel5);
        }

        throw new IllegalStateException("IS pattern (subsentence) does not contain IS constant word.");
    }

}