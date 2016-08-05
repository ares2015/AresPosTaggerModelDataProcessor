package com.trainingdataprocessor.data;

import java.util.List;

/**
 * Created by Oliver on 8/5/2016.
 */
public final class TestDataRow {

    private String sentence;

    private String tagPattern;

    private String encodedTagPattern;

    private List<List<String>> subSentences;

    private List<List<String>> tagSubPaths;

    private List<String> tags;

    public TestDataRow(String sentence, String tagPattern, String encodedTagPattern, List<List<String>> subSentences, List<List<String>> tagSubPaths, List<String> tags) {
        this.sentence = sentence;
        this.tagPattern = tagPattern;
        this.encodedTagPattern = encodedTagPattern;
        this.subSentences = subSentences;
        this.tagSubPaths = tagSubPaths;
        this.tags = tags;
    }

    public String getSentence() {
        return sentence;
    }

    public String getTagPattern() {
        return tagPattern;
    }

    public String getEncodedTagPattern() {
        return encodedTagPattern;
    }

    public List<List<String>> getSubSentences() {
        return subSentences;
    }

    public List<List<String>> getTagSubPaths() {
        return tagSubPaths;
    }

    public List<String> getTags() {
        return tags;
    }
}
