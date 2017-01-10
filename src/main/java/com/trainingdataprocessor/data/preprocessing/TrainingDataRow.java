package com.trainingdataprocessor.data.preprocessing;

import java.util.List;

/**
 * Created by Oliver on 8/5/2016.
 */
public final class TrainingDataRow {

    private boolean containsSubSentences;

    private String encodedPathAsString;

    private List<String> tokensList;

    private List<String> tagsList;

    private List<String> encodedTagsList;

    private List<String> encodedPathsAsStringList;

    private List<List<String>> encodedTagsMultiList;

    private List<List<String>> tokensMultiList;

    private List<List<String>> tagsMultiList;

    public boolean containsSubSentences() {
        return containsSubSentences;
    }

    public void setContainsSubSentences(boolean containsSubSentences) {
        this.containsSubSentences = containsSubSentences;
    }

    public String getEncodedPathAsString() {
        return encodedPathAsString;
    }

    public void setEncodedPathAsString(String encodedPathAsString) {
        this.encodedPathAsString = encodedPathAsString;
    }

    public List<String> getTokensList() {
        return tokensList;
    }

    public void setTokensList(List<String> tokensList) {
        this.tokensList = tokensList;
    }

    public List<String> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<String> tagsList) {
        this.tagsList = tagsList;
    }

    public List<String> getEncodedTagsList() {
        return encodedTagsList;
    }

    public void setEncodedTagsList(List<String> encodedTagsList) {
        this.encodedTagsList = encodedTagsList;
    }

    public List<String> getEncodedPathsAsStringList() {
        return encodedPathsAsStringList;
    }

    public void setEncodedPathsAsStringList(List<String> encodedPathsAsStringList) {
        this.encodedPathsAsStringList = encodedPathsAsStringList;
    }

    public List<List<String>> getEncodedTagsMultiList() {
        return encodedTagsMultiList;
    }

    public void setEncodedTagsMultiList(List<List<String>> encodedTagsMultiList) {
        this.encodedTagsMultiList = encodedTagsMultiList;
    }

    public List<List<String>> getTokensMultiList() {
        return tokensMultiList;
    }

    public void setTokensMultiList(List<List<String>> tokensMultiList) {
        this.tokensMultiList = tokensMultiList;
    }

    public List<List<String>> getTagsMultiList() {
        return tagsMultiList;
    }

    public void setTagsMultiList(List<List<String>> tagsMultiList) {
        this.tagsMultiList = tagsMultiList;
    }
}
