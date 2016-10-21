package com.trainingdataprocessor.data.semantics;

import com.trainingdataprocessor.data.RegexPatternData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 10/17/2016.
 */
public final class SemanticPreprocessingData {

    public int verbIndex;

    public List<RegexPatternData> beforeVerbPrepositionPhrases = new ArrayList<>();

    public List<RegexPatternData> afterVerbPrepositionPhrases = new ArrayList<>();

    public boolean containsBeforeVerbPrepositionPhrase;

    public boolean containsAfterVerbPrepositionPhrase;

    public int getVerbIndex() {
        return verbIndex;
    }

    public void setVerbIndex(int verbIndex) {
        this.verbIndex = verbIndex;
    }

    public List<RegexPatternData> getBeforeVerbPrepositionPhrases() {
        return beforeVerbPrepositionPhrases;
    }

    public List<RegexPatternData> getAfterVerbPrepositionPhrases() {
        return afterVerbPrepositionPhrases;
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
