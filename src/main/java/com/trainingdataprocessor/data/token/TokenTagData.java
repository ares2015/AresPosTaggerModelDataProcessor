package com.trainingdataprocessor.data.token;

/**
 * Created by Oliver on 11/12/2016.
 */
public class TokenTagData {

    private String token;

    private String tag;

    private boolean tokenExistsInDB;

    private boolean isNoun;

    private int isNounFrequency;

    private boolean isAdjective;

    private int isAdjectiveFrequency;

    private boolean isVerb;

    private int isVerbFrequency;

    private boolean isVerbEd;

    private int isVerbEdFrequency;

    private boolean isVerbIng;

    private int isVerbIngFrequency;

    private boolean isAdverb;

    private int isAdverbFrequency;

    private boolean isCapitalized;

    private int totalFrequency;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isTokenExistsInDB() {
        return tokenExistsInDB;
    }

    public boolean tokenExistsInDB() {
        return tokenExistsInDB;
    }

    public void setTokenExistsInDB(boolean tokenExistsInDB) {
        this.tokenExistsInDB = tokenExistsInDB;
    }

    public boolean isNoun() {
        return isNoun;
    }

    public void setNoun(boolean noun) {
        isNoun = noun;
    }

    public int getIsNounFrequency() {
        return isNounFrequency;
    }

    public void setIsNounFrequency(int isNounFrequency) {
        this.isNounFrequency = isNounFrequency;
    }

    public boolean isAdjective() {
        return isAdjective;
    }

    public void setAdjective(boolean adjective) {
        isAdjective = adjective;
    }

    public int getIsAdjectiveFrequency() {
        return isAdjectiveFrequency;
    }

    public void setIsAdjectiveFrequency(int isAdjectiveFrequency) {
        this.isAdjectiveFrequency = isAdjectiveFrequency;
    }

    public boolean isVerb() {
        return isVerb;
    }

    public void setVerb(boolean verb) {
        isVerb = verb;
    }

    public int getIsVerbFrequency() {
        return isVerbFrequency;
    }

    public void setIsVerbFrequency(int isVerbFrequency) {
        this.isVerbFrequency = isVerbFrequency;
    }

    public boolean isVerbEd() {
        return isVerbEd;
    }

    public void setVerbEd(boolean verbEd) {
        isVerbEd = verbEd;
    }

    public int getIsVerbEdFrequency() {
        return isVerbEdFrequency;
    }

    public void setIsVerbEdFrequency(int isVerbEdFrequency) {
        this.isVerbEdFrequency = isVerbEdFrequency;
    }

    public boolean isVerbIng() {
        return isVerbIng;
    }

    public void setVerbIng(boolean verbIng) {
        isVerbIng = verbIng;
    }

    public int getIsVerbIngFrequency() {
        return isVerbIngFrequency;
    }

    public void setIsVerbIngFrequency(int isVerbIngFrequency) {
        this.isVerbIngFrequency = isVerbIngFrequency;
    }

    public boolean isAdverb() {
        return isAdverb;
    }

    public void setAdverb(boolean adverb) {
        isAdverb = adverb;
    }

    public int getIsAdverbFrequency() {
        return isAdverbFrequency;
    }

    public void setIsAdverbFrequency(int isAdverbFrequency) {
        this.isAdverbFrequency = isAdverbFrequency;
    }

    public boolean isCapitalized() {
        return isCapitalized;
    }

    public void setCapitalized(boolean capitalized) {
        isCapitalized = capitalized;
    }

    public int getTotalFrequency() {
        return totalFrequency;
    }

    public void setTotalFrequency(int totalFrequency) {
        this.totalFrequency = totalFrequency;
    }
}
