package com.trainingdataprocessor.data;

import java.util.List;

/**
 * Created by Oliver on 8/5/2016.
 */
public final class TestDataRow {

    private boolean containsSubSentences;

    private String sentence;

    private String path;

    private String encodedPath;

    private List<String> tokensList;

    private List<String> tagsList;

    private List<String> encodedTagsList;

    private List<String> encodedSubPathsList;

    private List<List<String>> encodedTagsMultiList;

    private List<List<String>> tokensMultiList;

    private List<List<String>> tagsMultiList;

    public boolean containsSubSentences() {
        return containsSubSentences;
    }

    public void setContainsSubSentences(boolean containsSubSentences) {
        this.containsSubSentences = containsSubSentences;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getEncodedPath() {
        return encodedPath;
    }

    public void setEncodedPath(String encodedPath) {
        this.encodedPath = encodedPath;
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

    public List<String> getEncodedSubPathsList() {
        return encodedSubPathsList;
    }

    public void setEncodedSubPathsList(List<String> encodedSubPathsList) {
        this.encodedSubPathsList = encodedSubPathsList;
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
