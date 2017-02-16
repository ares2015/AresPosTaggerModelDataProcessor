package com.trainingdataprocessor.data.semantics;

/**
 * Created by Oliver on 10/21/2016.
 */
public class SemanticExtractionData {

    private int verbIndex;

    private int beforeVerbIndex;

    private int afterVerbIndex;

    private String atomicSubject = "";

    private String extendedSubject = "";

    private String atomicVerbPredicate = "";

    private String extendedVerbPredicate = "";

    private String atomicNounPredicate = "";

    private String extendedNounPredicate = "";

    public int getVerbIndex() {
        return verbIndex;
    }

    public void setVerbIndex(int verbIndex) {
        this.verbIndex = verbIndex;
    }

    public int getBeforeVerbIndex() {
        return beforeVerbIndex;
    }

    public void setBeforeVerbIndex(int beforeVerbIndex) {
        this.beforeVerbIndex = beforeVerbIndex;
    }

    public int getAfterVerbIndex() {
        return afterVerbIndex;
    }

    public void setAfterVerbIndex(int afterVerbIndex) {
        this.afterVerbIndex = afterVerbIndex;
    }

    public String getAtomicSubject() {
        return atomicSubject;
    }

    public void setAtomicSubject(String atomicSubject) {
        this.atomicSubject = atomicSubject;
    }

    public String getExtendedSubject() {
        return extendedSubject;
    }

    public void setExtendedSubject(String extendedSubject) {
        this.extendedSubject = extendedSubject;
    }

    public String getAtomicVerbPredicate() {
        return atomicVerbPredicate;
    }

    public void setAtomicVerbPredicate(String atomicVerbPredicate) {
        this.atomicVerbPredicate = atomicVerbPredicate;
    }

    public String getExtendedVerbPredicate() {
        return extendedVerbPredicate;
    }

    public void setExtendedVerbPredicate(String extendedVerbPredicate) {
        this.extendedVerbPredicate = extendedVerbPredicate;
    }

    public String getAtomicNounPredicate() {
        return atomicNounPredicate;
    }

    public void setAtomicNounPredicate(String atomicNounPredicate) {
        this.atomicNounPredicate = atomicNounPredicate;
    }

    public String getExtendedNounPredicate() {
        return extendedNounPredicate;
    }

    public void setExtendedNounPredicate(String extendedNounPredicate) {
        this.extendedNounPredicate = extendedNounPredicate;
    }
}
