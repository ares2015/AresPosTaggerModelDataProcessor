package com.trainingdataprocessor.data;

public class BigramData {

    private String tag1;

    private String tag2;

    private int tag1Frequency;

    private int bigramFrequency;

    private double bigramProbability;

    private boolean isTag1Constant;

    private boolean isTag2Constant;

    private boolean existsInDatabase = false;

    public BigramData(String tag1, String tag2, boolean isTag1Constant, boolean isTag2Constant) {
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.isTag1Constant = isTag1Constant;
        this.isTag2Constant = isTag2Constant;
    }

    public String getTag1() {
        return tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public int getTag1Frequency() {
        return tag1Frequency;
    }

    public int getBigramFrequency() {
        return bigramFrequency;
    }

    public double getBigramProbability() {
        return bigramProbability;
    }

    public boolean isTag1Constant() {
        return isTag1Constant;
    }

    public boolean isTag2Constant() {
        return isTag2Constant;
    }

    public boolean isExistsInDatabase() {
        return existsInDatabase;
    }

    public void setTag1Frequency(int tag1Frequency) {
        this.tag1Frequency = tag1Frequency;
    }

    public void setBigramFrequency(int bigramFrequency) {
        this.bigramFrequency = bigramFrequency;
    }

    public void setBigramProbability(double bigramProbability) {
        this.bigramProbability = bigramProbability;
    }

    public void setExistsInDatabase(boolean existsInDatabase) {
        this.existsInDatabase = existsInDatabase;
    }
}
