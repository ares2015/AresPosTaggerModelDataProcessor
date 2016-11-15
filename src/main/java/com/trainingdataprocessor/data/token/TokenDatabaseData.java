package com.trainingdataprocessor.data.token;

/**
 * Created by Oliver on 11/15/2016.
 */
public class TokenDatabaseData {

    private boolean isNoun;

    private boolean isAdjective;

    private boolean isVerb;

    private boolean isVerbEd;

    private boolean isVerbIng;

    private boolean isAdverb;

    private int isNounFrequency;

    private int isAdjectiveFrequency;

    private int isVerbFrequency;

    private int isVerbEdFrequency;

    private int isVerbIngFrequency;

    private int isAdverbFrequency;

    private int totalFrequency;

    public boolean isNoun() {
        return isNoun;
    }

    public void setNoun(boolean noun) {
        isNoun = noun;
    }

    public boolean isAdjective() {
        return isAdjective;
    }

    public void setAdjective(boolean adjective) {
        isAdjective = adjective;
    }

    public boolean isVerb() {
        return isVerb;
    }

    public void setVerb(boolean verb) {
        isVerb = verb;
    }

    public boolean isVerbEd() {
        return isVerbEd;
    }

    public void setVerbEd(boolean verbEd) {
        isVerbEd = verbEd;
    }

    public boolean isVerbIng() {
        return isVerbIng;
    }

    public void setVerbIng(boolean verbIng) {
        isVerbIng = verbIng;
    }

    public boolean isAdverb() {
        return isAdverb;
    }

    public void setAdverb(boolean adverb) {
        isAdverb = adverb;
    }

    public int getIsNounFrequency() {
        return isNounFrequency;
    }

    public void setIsNounFrequency(int isNounFrequency) {
        this.isNounFrequency = isNounFrequency;
    }

    public int getIsAdjectiveFrequency() {
        return isAdjectiveFrequency;
    }

    public void setIsAdjectiveFrequency(int isAdjectiveFrequency) {
        this.isAdjectiveFrequency = isAdjectiveFrequency;
    }

    public int getIsVerbFrequency() {
        return isVerbFrequency;
    }

    public void setIsVerbFrequency(int isVerbFrequency) {
        this.isVerbFrequency = isVerbFrequency;
    }

    public int getIsVerbEdFrequency() {
        return isVerbEdFrequency;
    }

    public void setIsVerbEdFrequency(int isVerbEdFrequency) {
        this.isVerbEdFrequency = isVerbEdFrequency;
    }

    public int getIsVerbIngFrequency() {
        return isVerbIngFrequency;
    }

    public void setIsVerbIngFrequency(int isVerbIngFrequency) {
        this.isVerbIngFrequency = isVerbIngFrequency;
    }

    public int getIsAdverbFrequency() {
        return isAdverbFrequency;
    }

    public void setIsAdverbFrequency(int isAdverbFrequency) {
        this.isAdverbFrequency = isAdverbFrequency;
    }

    public int getTotalFrequency() {
        return totalFrequency;
    }

    public void setTotalFrequency(int totalFrequency) {
        this.totalFrequency = totalFrequency;
    }
}
