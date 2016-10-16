package com.trainingdataprocessor.data.semantics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 10/1/2016.
 */
public final class SemanticalConstantTagAnalysisData {

    private int constantIndex;

    private String constantTag = "";

    private String constantToken = "";

    private boolean containsBeforeConstantPreposition;

    private boolean containsAfterConstantPreposition;

    private List<Integer> beforeConstantTagPrepositionIndexes = new ArrayList<>();

    private List<Integer> afterConstantTagPrepositionIndexes = new ArrayList<>();

    public void setConstantIndex(int constantIndex) {
        this.constantIndex = constantIndex;
    }

    public void setConstantTag(String constantTag) {
        this.constantTag = constantTag;
    }

    public void setConstantToken(String constantToken) {
        this.constantToken = constantToken;
    }

    public void setContainsBeforeConstantPreposition(boolean containsBeforeConstantPreposition) {
        this.containsBeforeConstantPreposition = containsBeforeConstantPreposition;
    }

    public void setContainsAfterConstantPreposition(boolean containsAfterConstantPreposition) {
        this.containsAfterConstantPreposition = containsAfterConstantPreposition;
    }

    public void setHasExtendedSubject(boolean hasExtendedSubject) {
        this.hasExtendedSubject = hasExtendedSubject;
    }

    public void setHasExtendedPredicate(boolean hasExtendedPredicate) {
        this.hasExtendedPredicate = hasExtendedPredicate;
    }

    public void setHasVerbAuxiliaryVerbPhrase(boolean hasVerbAuxiliaryVerbPhrase) {
        this.hasVerbAuxiliaryVerbPhrase = hasVerbAuxiliaryVerbPhrase;
    }

    private boolean hasExtendedSubject = false;

    private boolean hasExtendedPredicate = false;

    private boolean hasVerbAuxiliaryVerbPhrase = false;

    public int getConstantIndex() {
        return constantIndex;
    }

    public String getConstantTag() {
        return constantTag;
    }

    public String getConstantToken() {
        return constantToken;
    }

    public boolean containsBeforeConstantTagPreposition() {
        return containsBeforeConstantPreposition;
    }

    public boolean containsAfterConstantTagPreposition() {
        return containsAfterConstantPreposition;
    }

    public List<Integer> getBeforeConstantTagPrepositionIndexes() {
        return beforeConstantTagPrepositionIndexes;
    }

    public List<Integer> getAfterConstantTagPrepositionIndexes() {
        return afterConstantTagPrepositionIndexes;
    }

    public boolean hasExtendedSubject() {
        return hasExtendedSubject;
    }

    public boolean hasExtendedPredicate() {
        return hasExtendedPredicate;
    }

    public boolean hasVerbAuxiliaryVerbPhrase() {
        return hasVerbAuxiliaryVerbPhrase;
    }
}