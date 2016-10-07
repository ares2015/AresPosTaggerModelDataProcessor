package com.trainingdataprocessor.data.semantics;

import java.util.List;

/**
 * Created by Oliver on 10/1/2016.
 */
public final class SemanticalConstantTagAnalysisData {

    private int constantIndex;

    private String constantTag = "";

    private String constantToken = "";

    private boolean containsBeforeISPreposition;

    private boolean containsAfterISPreposition;

    private List<Integer> beforeConstantTagPrepositionIndexes;

    private List<Integer> afterConstantTagPrepositionIndexes;

    private boolean isPresentTense;

    private boolean hasExtendedSubject = false;

    private boolean hasExtendedPredicate = false;

    private boolean hasVerbAuxiliaryVerbRelation = false;

    public SemanticalConstantTagAnalysisData(int constantIndex, String constantTag, String constantToken, boolean containsBeforeISPreposition,
                                             boolean containsAfterISPreposition, List<Integer> beforeConstantTagPrepositionIndexes,
                                             List<Integer> afterConstantTagPrepositionIndexes, boolean isPresentTense, boolean hasExtendedSubject,
                                             boolean hasExtendedPredicate, boolean hasVerbAuxiliaryVerbRelation) {
        this.constantIndex = constantIndex;
        this.constantTag = constantTag;
        this.constantToken = constantToken;
        this.containsBeforeISPreposition = containsBeforeISPreposition;
        this.containsAfterISPreposition = containsAfterISPreposition;
        this.beforeConstantTagPrepositionIndexes = beforeConstantTagPrepositionIndexes;
        this.afterConstantTagPrepositionIndexes = afterConstantTagPrepositionIndexes;
        this.isPresentTense = isPresentTense;
        this.hasExtendedSubject = hasExtendedSubject;
        this.hasExtendedPredicate = hasExtendedPredicate;
        this.hasVerbAuxiliaryVerbRelation = hasVerbAuxiliaryVerbRelation;
    }

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
        return containsBeforeISPreposition;
    }

    public boolean containsAfterConstantTagPreposition() {
        return containsAfterISPreposition;
    }

    public List<Integer> getBeforeConstantTagPrepositionIndexes() {
        return beforeConstantTagPrepositionIndexes;
    }

    public List<Integer> getAfterConstantTagPrepositionIndexes() {
        return afterConstantTagPrepositionIndexes;
    }

    public boolean isPresentTense() {
        return isPresentTense;
    }

    public boolean hasExtendedSubject() {
        return hasExtendedSubject;
    }

    public boolean hasExtendedPredicate() {
        return hasExtendedPredicate;
    }

    public boolean hasVerbAuxiliaryVerbRelation() {
        return hasVerbAuxiliaryVerbRelation;
    }
}