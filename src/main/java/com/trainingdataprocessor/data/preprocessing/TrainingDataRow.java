package com.trainingdataprocessor.data.preprocessing;

import java.util.List;

/**
 * Created by Oliver on 8/5/2016.
 */
public final class TrainingDataRow {

    private boolean containsSubSentences;

    private List<String> tokensList;

    private List<String> tagsList;

    private List<List<String>> tokensMultiList;

    private List<List<String>> tagsMultiList;

    public boolean containsSubSentences() {
        return containsSubSentences;
    }

    public void setContainsSubSentences(boolean containsSubSentences) {
        this.containsSubSentences = containsSubSentences;
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
