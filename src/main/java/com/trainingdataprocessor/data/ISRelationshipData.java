package com.trainingdataprocessor.data;

/**
 * Created by Oliver on 8/31/2016.
 */
public class ISRelationshipData extends RelationshipData {

    private boolean isPresentTense;

    private int startIndex;

    private int endIndex;

    private String level1object1;

    private String level1object2;

    private String level2object1;

    private String level2object2;

    private String level3object1;

    private String level3object2;

    public ISRelationshipData(boolean isPresentTense, int startIndex, int endIndex, String level1object1,
                              String level1object2) {
        this.isPresentTense = isPresentTense;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.level1object1 = level1object1;
        this.level1object2 = level1object2;
    }

    public ISRelationshipData(boolean isPresentTense, int startIndex, int endIndex, String level1object1,
                              String level1object2, String level2object1, String level2object2) {
        this.isPresentTense = isPresentTense;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.level1object1 = level1object1;
        this.level1object2 = level1object2;
        this.level2object1 = level2object1;
        this.level2object2 = level2object2;
    }

    public ISRelationshipData(boolean isPresentTense, int startIndex, int endIndex, String level1object1,
                              String level1object2,
                              String level2object1, String level2object2, String level3object1, String level3object2) {
        this.isPresentTense = isPresentTense;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.level1object1 = level1object1;
        this.level1object2 = level1object2;
        this.level2object1 = level2object1;
        this.level2object2 = level2object2;
        this.level3object1 = level3object1;
        this.level3object2 = level3object2;
    }

    public boolean isPresentTense() {
        return isPresentTense;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public String getLevel1object1() {
        return level1object1;
    }

    public String getLevel1object2() {
        return level1object2;
    }

    public String getLevel2object1() {
        return level2object1;
    }

    public String getLevel2object2() {
        return level2object2;
    }

    public String getLevel3object1() {
        return level3object1;
    }

    public String getLevel3object2() {
        return level3object2;
    }
}
