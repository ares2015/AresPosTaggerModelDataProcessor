package com.trainingdataprocessor.data.semantics;

import java.util.List;

/**
 * Created by Oliver on 2/17/2017.
 */
public class SemanticPreprocessingData {

    private boolean canGoToExtraction;

    private List<String> tokensList;

    private List<String> tagsList;

    private int verbIndex;

    private int modalVerbIndex;

    private int beforeVerbPrepositionIndex;

    private int afterVerbPrepositionIndex;

    public boolean isCanGoToExtraction() {
        return canGoToExtraction;
    }

    public void setCanGoToExtraction(boolean canGoToExtraction) {
        this.canGoToExtraction = canGoToExtraction;
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

    public int getVerbIndex() {
        return verbIndex;
    }

    public void setVerbIndex(int verbIndex) {
        this.verbIndex = verbIndex;
    }

    public int getModalVerbIndex() {
        return modalVerbIndex;
    }

    public void setModalVerbIndex(int modalVerbIndex) {
        this.modalVerbIndex = modalVerbIndex;
    }

    public int getBeforeVerbPrepositionIndex() {
        return beforeVerbPrepositionIndex;
    }

    public void setBeforeVerbPrepositionIndex(int beforeVerbPrepositionIndex) {
        this.beforeVerbPrepositionIndex = beforeVerbPrepositionIndex;
    }

    public int getAfterVerbPrepositionIndex() {
        return afterVerbPrepositionIndex;
    }

    public void setAfterVerbPrepositionIndex(int afterVerbPrepositionIndex) {
        this.afterVerbPrepositionIndex = afterVerbPrepositionIndex;
    }
}