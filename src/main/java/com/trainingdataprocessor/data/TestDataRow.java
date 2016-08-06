package com.trainingdataprocessor.data;

import java.util.List;

/**
 * Created by Oliver on 8/5/2016.
 */
public final class TestDataRow {

    private String sentence;

    private List<String> tokensList;

    private String tagsAsString;

    private String encodedTagsAsString;

    private List<String> tagsList;

    private List<List<String>> subSentences;

    private List<List<String>> tagSubPaths;

    public TestDataRow(String sentence, List<String> tokensList, String tagsAsString, String encodedTagsAsString, List<String> tagsList) {
        this.sentence = sentence;
        this.tokensList = tokensList;
        this.tagsAsString = tagsAsString;
        this.encodedTagsAsString = encodedTagsAsString;
        this.tagsList = tagsList;
    }

    public TestDataRow(String sentence, List<String> tokensList, String tagsAsString, String encodedTagsAsString, List<String> tagsList, List<List<String>> subSentences, List<List<String>> tagSubPaths) {
        this.sentence = sentence;
        this.tokensList = tokensList;
        this.tagsAsString = tagsAsString;
        this.encodedTagsAsString = encodedTagsAsString;
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

    public List<List<String>> getSubSentences() {
        return subSentences;
    }

    public List<List<String>> getTagSubPaths() {
        return tagSubPaths;
    }

    public List<String> getTagsList() {
        return tagsList;
    }
}
