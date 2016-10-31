package com.trainingdataprocessor.data;

import java.util.List;

/**
 * Created by Oliver on 8/5/2016.
 */
public final class TestDataRow {

    private boolean containsSubSentences;

    private String sentence;

    private String tagsAsString;

    private String encodedTagsSubPathAsString;

    private List<String> tokensList;

    private List<String> tagsList;

    private List<String> encodedTagsAsSingleList;

    private List<List<String>> encodedTagsListOfLists;

    private List<List<String>> subSentences;

    private List<List<String>> tagsListOfLists;

    private List<String> encodedTagSubPathsList;

    public TestDataRow(boolean containsSubSentences, String sentence, String tagsAsString, String encodedTagsSubPathAsString, List<String> tokensList, List<String> tagsList, List<String> encodedTagsAsSingleList) {
        this.containsSubSentences = containsSubSentences;
        this.sentence = sentence;
        this.tagsAsString = tagsAsString;
        this.encodedTagsSubPathAsString = encodedTagsSubPathAsString;
        this.tokensList = tokensList;
        this.tagsList = tagsList;
        this.encodedTagsAsSingleList = encodedTagsAsSingleList;
    }

    public TestDataRow(boolean containsSubSentences, String sentence, String tagsAsString, String encodedTagsSubPathAsString, List<String> tokensList,
                       List<String> tagsList, List<List<String>> subSentences, List<List<String>> tagsListOfLists, List<List<String>> encodedTagsListOfLists, List<String> encodedTagSubPathsList) {
        this.containsSubSentences = containsSubSentences;
        this.sentence = sentence;
        this.tagsAsString = tagsAsString;
        this.encodedTagsSubPathAsString = encodedTagsSubPathAsString;
        this.tokensList = tokensList;
        this.tagsList = tagsList;
        this.subSentences = subSentences;
        this.tagsListOfLists = tagsListOfLists;
        this.encodedTagsListOfLists = encodedTagsListOfLists;
        this.encodedTagSubPathsList = encodedTagSubPathsList;
    }

    public boolean containsSubSentences() {
        return containsSubSentences;
    }

    public String getSentence() {
        return sentence;
    }

    public String getTagsAsString() {
        return tagsAsString;
    }

    public String getEncodedTagsSubPathAsString() {
        return encodedTagsSubPathAsString;
    }

    public List<String> getTokensList() {
        return tokensList;
    }

    public List<String> getTagsList() {
        return tagsList;
    }

    public List<String> getEncodedTagsAsSingleList() {
        return encodedTagsAsSingleList;
    }

    public List<List<String>> getSubSentences() {
        return subSentences;
    }

    public List<List<String>> getTagsListOfLists() {
        return tagsListOfLists;
    }

    public List<List<String>> getEncodedTagsListOfLists() {
        return encodedTagsListOfLists;
    }

    public List<String> getEncodedTagSubPathsList() {
        return encodedTagSubPathsList;
    }

}
