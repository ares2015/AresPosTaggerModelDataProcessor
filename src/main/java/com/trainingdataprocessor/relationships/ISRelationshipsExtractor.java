package com.trainingdataprocessor.relationships;

import com.trainingdataprocessor.data.ISRelationshipData;
import com.trainingdataprocessor.data.RegexPatternIndexData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 8/31/2016.
 */
public class ISRelationshipsExtractor /*implements RelationshipsExtractor*/ {

    //    @Override
    public List<ISRelationshipData> extract(List<RegexPatternIndexData> isPatternIndexDataList, List<String> tokens) {
        List<ISRelationshipData> relationships = new ArrayList<>();
        for (RegexPatternIndexData indexData : isPatternIndexDataList) {
            int startIndex = indexData.getStartIndex();
            int endIndex = indexData.getEndIndex() + 1;
            List<String> subSentence = tokens.subList(startIndex, endIndex);
            ISindexTensePair isIndexTensePair = getISindexTensePair(subSentence);
            int isIndex = isIndexTensePair.index;
            String level1object1 = subSentence.get(isIndex - 1);
            String level1object2 = subSentence.get(subSentence.size() - 1);
            String level2object1 = "";
            String level2object2 = "";
            String level3object1 = "";
            String level3object2 = "";

            if (isIndex > 1) {
                level2object1 = getExtendedObject1(subSentence, isIndex);
                level2object2 = subSentence.get(subSentence.size() - 1);

            }
            if (isIndex != subSentence.size() - 2) {
                level3object1 = getExtendedObject1(subSentence, isIndex);
                level3object2 = getExtendedObject2(subSentence, isIndex);
            }
//            ISRelationshipData relationshipData = new ISRelationshipData(isIndexTensePair.isPresentTesne, startIndex, endIndex,
//                    level1object1, level1object2, level2object1, level2object2);
            ISRelationshipData relationshipData = new ISRelationshipData(isIndexTensePair.isPresentTesne, startIndex, endIndex,
                    level1object1, level1object2, level2object1, level2object2, level3object1, level3object2);
            relationships.add(relationshipData);


//            ISRelationshipData relationshipData = new ISRelationshipData(isIndexTensePair.isPresentTesne, startIndex, endIndex,
//                    level1object1, level1object2);
//            relationships.add(relationshipData);
        }
        return relationships;
    }

    private ISindexTensePair getISindexTensePair(List<String> subSentence) {
        int index = 0;
        for (String token : subSentence) {
            if ("is".equals(token) || "are".equals(token)) {
                return new ISindexTensePair(index, true);
            } else if ("was".equals(token) || "were".equals(token)) {
                return new ISindexTensePair(index, false);
            }
            index++;
        }
        throw new IllegalStateException("IS pattern (subsentence) does not contain IS constant word.");
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

    private class ISindexTensePair {

        private int index;

        private boolean isPresentTesne;

        public ISindexTensePair(int index, boolean isPresentTesne) {
            this.index = index;
            this.isPresentTesne = isPresentTesne;
        }
    }

}
