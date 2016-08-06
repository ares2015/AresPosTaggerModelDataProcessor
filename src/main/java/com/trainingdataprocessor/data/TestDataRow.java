package com.trainingdataprocessor.data;

import java.util.List;

/**
 * Created by Oliver on 8/5/2016.
 */
public final class TestDataRow {

    private String sentence;

    private String tagsAsString;

    private String encodedTagsAsString;

    private List<String> tokensList;

    private List<String> tagsList;

    private List<List<String>> subSentences;

    private List<List<String>> tagSubPaths;

    public TestDataRow(String sentence, String tagsAsString, String encodedTagsAsString, List<String> tokensList, List<String> tagsList) {
        this.sentence = sentence;
        this.tagsAsString = tagsAsString;
        this.encodedTagsAsString = encodedTagsAsString;
        this.tokensList = tokensList;
        this.tagsList = tagsList;
    }

    public TestDataRow(String sentence, String tagsAsString, String encodedTagsAsString, List<String> tokensList,
                       List<String> tagsList, List<List<String>> subSentences, List<List<String>> tagSubPaths) {
        this.sentence = sentence;
        this.tagsAsString = tagsAsString;
        this.encodedTagsAsString = encodedTagsAsString;
        this.tokensList = tokensList;
        this.tagsList = tagsList;
        this.subSentences = subSentences;
        this.tagSubPaths = tagSubPaths;
    }

    public String getSentence() {
        return sentence;
    }

    public String getTagsAsString() {
        return tagsAsString;
    }

    public String getEncodedTagsAsString() {
        return encodedTagsAsString;
    }

    public List<String> getTokensList() {
        return tokensList;
    }

    public List<String> getTagsList() {
        return tagsList;
    }

    public List<List<String>> getSubSentences() {
        return subSentences;
    }

    public List<List<String>> getTagSubPaths() {
        return tagSubPaths;
    }

}
