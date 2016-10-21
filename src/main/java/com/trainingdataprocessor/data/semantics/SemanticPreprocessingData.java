package com.trainingdataprocessor.data.semantics;

import com.trainingdataprocessor.data.RegexPatternData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 10/17/2016.
 */
public final class SemanticPreprocessingData {

    public int verbIndex;

    public RegexPatternData beforeVerbPrepositionPhrase;

    public RegexPatternData afterVerbPrepositionPhrase;

    public boolean containsBeforeVerbPrepositionPhrase;

    public boolean containsAfterVerbPrepositionPhrase;

    public int getVerbIndex() {
        return verbIndex;
    }

    public void setVerbIndex(int verbIndex) {
        this.verbIndex = verbIndex;
    }

    public RegexPatternData getBeforeVerbPrepositionPhrase() {
        return beforeVerbPrepositionPhrase;
    }

    public void setBeforeVerbPrepositionPhrase(RegexPatternData beforeVerbPrepositionPhrase) {
        this.beforeVerbPrepositionPhrase = beforeVerbPrepositionPhrase;
    }

    public RegexPatternData getAfterVerbPrepositionPhrase() {
        return afterVerbPrepositionPhrase;
    }

    public void setAfterVerbPrepositionPhrase(RegexPatternData afterVerbPrepositionPhrase) {
        this.afterVerbPrepositionPhrase = afterVerbPrepositionPhrase;
    }

    public boolean containsBeforeVerbPrepositionPhrase() {
        return containsBeforeVerbPrepositionPhrase;
    }

    public void setContainsBeforeVerbPrepositionPhrase(boolean containsBeforeVerbPrepositionPhrase) {
        this.containsBeforeVerbPrepositionPhrase = containsBeforeVerbPrepositionPhrase;
    }

    public boolean containsAfterVerbPrepositionPhrase() {
        return containsAfterVerbPrepositionPhrase;
    }

    public void setContainsAfterVerbPrepositionPhrase(boolean containsAfterVerbPrepositionPhrase) {
        this.containsAfterVerbPrepositionPhrase = containsAfterVerbPrepositionPhrase;
    }
}
