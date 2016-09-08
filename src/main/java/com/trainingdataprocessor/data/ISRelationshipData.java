package com.trainingdataprocessor.data;

import java.util.List;

/**
 * Created by Oliver on 8/31/2016.
 */
public class ISRelationshipData extends RelationshipData {

    private boolean isPresentTense;

    private int startIndex;

    private int endIndex;

    private String atomicSubject;

    private String extendedSubject;

    private String atomicPredicate;

    private String extendedPredicate;

    private String prepositionPredicate;

    public ISRelationshipData(boolean isPresentTense, int startIndex, int endIndex, String atomicSubject,
                              String extendedSubject, String atomicPredicate, String extendedPredicate, String prepositionPredicate) {
        this.isPresentTense = isPresentTense;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.atomicSubject = atomicSubject;
        this.extendedSubject = extendedSubject;
        this.atomicPredicate = atomicPredicate;
        this.extendedPredicate = extendedPredicate;
        this.prepositionPredicate = prepositionPredicate;
    }

    public boolean isPresentTense() {
        return isPresentTense;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public String getAtomicSubject() {
        return atomicSubject;
    }

    public String getAtomicPredicate() {
        return atomicPredicate;
    }

    public String getExtendedSubject() {
        return extendedSubject;
    }

    public String getExtendedPredicate() {
        return extendedPredicate;
    }

    public String getPrepositionPredicate() {
        return prepositionPredicate;
    }
}
