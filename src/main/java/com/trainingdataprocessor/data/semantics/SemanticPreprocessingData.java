package com.trainingdataprocessor.data.semantics;

import com.trainingdataprocessor.data.RegexPatternData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 10/17/2016.
 */
public final class SemanticPreprocessingData {

    public String constantTag;

    public int constantTagIndex;

    public RegexPatternData beforeVerbNounPhrase;

    public RegexPatternData beforeVerbPrepositionPhrase;

    public boolean containsBeforeVerbNounPhrase;

    public boolean containsBeforeVerbPrepositionPhrase;

    public String getConstantTag() {
        return constantTag;
    }

    public void setConstantTag(String constantTag) {
        this.constantTag = constantTag;
    }

    public int getConstantTagIndex() {
        return constantTagIndex;
    }

    public void setConstantTagIndex(int constantTagIndex) {
        this.constantTagIndex = constantTagIndex;
    }

    public RegexPatternData getBeforeVerbNounPhrase() {
        return beforeVerbNounPhrase;
    }

    public void setBeforeVerbNounPhrase(RegexPatternData beforeVerbNounPhrase) {
        this.beforeVerbNounPhrase = beforeVerbNounPhrase;
    }

    public RegexPatternData getBeforeVerbPrepositionPhrase() {
        return beforeVerbPrepositionPhrase;
    }

    public void setBeforeVerbPrepositionPhrase(RegexPatternData beforeVerbPrepositionPhrase) {
        this.beforeVerbPrepositionPhrase = beforeVerbPrepositionPhrase;
    }

    public boolean isContainsBeforeVerbNounPhrase() {
        return containsBeforeVerbNounPhrase;
    }

    public void setContainsBeforeVerbNounPhrase(boolean containsBeforeVerbNounPhrase) {
        this.containsBeforeVerbNounPhrase = containsBeforeVerbNounPhrase;
    }

    public boolean isContainsBeforeVerbPrepositionPhrase() {
        return containsBeforeVerbPrepositionPhrase;
    }

    public void setContainsBeforeVerbPrepositionPhrase(boolean containsBeforeVerbPrepositionPhrase) {
        this.containsBeforeVerbPrepositionPhrase = containsBeforeVerbPrepositionPhrase;
    }
}
