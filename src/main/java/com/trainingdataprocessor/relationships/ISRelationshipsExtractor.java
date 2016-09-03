package com.trainingdataprocessor.relationships;

import com.trainingdataprocessor.data.ISRelationshipData;
import com.trainingdataprocessor.data.RegexPatternIndexData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 8/31/2016.
 */
public class ISRelationshipsExtractor implements RelationshipsExtractor<ISRelationshipData> {

    @Override
    public List<ISRelationshipData> extract(List<RegexPatternIndexData> isPatternIndexDataList, List<String> tokens) {
        List<ISRelationshipData> relationships = new ArrayList<>();
        String level2object1 = "";
        String level2object2 = "";
        String level3object1 = "";
        String level3object2 = "";
        boolean hasLevel2;
        boolean hasLevel3;
        boolean isPresentTense;

        for (RegexPatternIndexData indexData : isPatternIndexDataList) {

            int startIndex = indexData.getStartIndex();
            int endIndex = indexData.getEndIndex() + 1;

            List<String> subSentence = tokens.subList(startIndex, endIndex);

            ISindexLevelTenseObject iSindexLevelTenseObject = getISindexLevelTenseObject(subSentence);
            int isIndex = iSindexLevelTenseObject.index;
            isPresentTense = iSindexLevelTenseObject.isPresentTense;
            hasLevel2 = iSindexLevelTenseObject.hasLevel2;
            hasLevel3 = iSindexLevelTenseObject.hasLevel3;

            String level1object1 = subSentence.get(isIndex - 1);
            String level1object2 = subSentence.get(subSentence.size() - 1);

            if (hasLevel2) {
                level2object1 = getExtendedObject1(subSentence, isIndex);
                level2object2 = subSentence.get(subSentence.size() - 1);
            }

            if (hasLevel3) {
                level3object1 = getExtendedObject1(subSentence, isIndex);
                level3object2 = getExtendedObject2(subSentence, isIndex);
            }
            if (!hasLevel2 && !hasLevel3) {
                ISRelationshipData relationshipData = new ISRelationshipData(isPresentTense, startIndex, endIndex,
                        level1object1, level1object2);
                relationships.add(relationshipData);
            } else if (hasLevel2 && !hasLevel3) {
                ISRelationshipData relationshipData = new ISRelationshipData(isPresentTense, startIndex, endIndex,
                        level1object1, level1object2, level2object1, level2object2);
                relationships.add(relationshipData);
            } else if (hasLevel2 && hasLevel3) {
                ISRelationshipData relationshipData = new ISRelationshipData(isPresentTense, startIndex, endIndex,
                        level1object1, level1object2, level2object1, level2object2, level3object1, level3object2);
                relationships.add(relationshipData);
            }
        }
        return relationships;
    }

    private ISindexLevelTenseObject getISindexLevelTenseObject(List<String> subSentence) {
        boolean isConstantFound = false;
        boolean isPresentTense = false;
        boolean hasLevel2;
        boolean hasLevel3;
        int index = 0;
        for (String token : subSentence) {
            if ("is".equals(token) || "are".equals(token)) {
                isPresentTense = true;
                isConstantFound = true;
            } else if ("was".equals(token) || "were".equals(token)) {
                isConstantFound = true;
            }
            if (isConstantFound) {
                hasLevel2 = subSentenceHasLevel2(index);
                hasLevel3 = subSentenceHasLevel3(index, subSentence);
                return new ISindexLevelTenseObject(index, isPresentTense, hasLevel2, hasLevel3);
            }
            index++;
        }
        throw new IllegalStateException("IS pattern (subsentence) does not contain IS constant word.");
    }


    private boolean subSentenceHasLevel2(int isIndex) {
        return isIndex > 1;
    }

    private boolean subSentenceHasLevel3(int isIndex, List<String> subSentence) {
        return isIndex != subSentence.size() - 2;
    }

    private String getExtendedObject1(List<String> subSentence, int isIndex) {
        String level2object1 = "";
        for (int i = 0; i < isIndex; i++) {
            if (i < isIndex - 1)
                level2object1 += subSentence.get(i) + " ";
            else
                level2object1 += subSentence.get(i);
        }
        return level2object1;
    }

    private String getExtendedObject2(List<String> subSentence, int isIndex) {
        String level2object1 = "";
        for (int i = isIndex + 1; i <= subSentence.size() - 1; i++) {
            if (i < subSentence.size() - 1)
                level2object1 += subSentence.get(i) + " ";
            else
                level2object1 += subSentence.get(i);
        }
        return level2object1;
    }


    private class ISindexLevelTenseObject {

        private int index;

        private boolean isPresentTense;

        private boolean hasLevel2;

        private boolean hasLevel3;


        public ISindexLevelTenseObject(int index, boolean isPresentTense, boolean hasLevel2, boolean hasLevel3) {
            this.index = index;
            this.isPresentTense = isPresentTense;
            this.hasLevel2 = hasLevel2;
            this.hasLevel3 = hasLevel3;
        }
    }

}