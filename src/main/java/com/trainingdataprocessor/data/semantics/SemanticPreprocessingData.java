package com.trainingdataprocessor.data.semantics;

import com.trainingdataprocessor.data.RegexPatternData;
import com.trainingdataprocessor.semantics.preprocessing.phrases.verb.VerbPhraseTypes;

import java.util.List;

/**
 * Created by Oliver on 10/17/2016.
 */
public final class SemanticPreprocessingData {

    private List<String> tokens;

    private List<String> encodedTags;

    private int verbIndex;

    private int afterVerbFirstPrepositionIndex;

    private RegexPatternData beforeVerbPrepositionPhrase;

    private RegexPatternData afterVerbPrepositionPhrase;

    private boolean containsBeforeVerbPrepositionPhrase;

    private boolean containsAfterVerbPrepositionPhrase;

    private RegexPatternData beforeVerbNounPhrase;

    private RegexPatternData afterVerbNounPhrase;

    private boolean containsBeforeVerbNounPhrase;

    private boolean containsAfterVerbNounPhrase;

    private RegexPatternData verbPhrase;

    private VerbPhraseTypes verbPhraseTypes;

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }

    public List<String> getEncodedTags() {
        return encodedTags;
    }

    public void setEncodedTags(List<String> encodedTags) {
        this.encodedTags = encodedTags;
    }

    public int getVerbIndex() {
        return verbIndex;
    }

    public void setVerbIndex(int verbIndex) {
        this.verbIndex = verbIndex;
    }

    public int getAfterVerbFirstPrepositionIndex() {
        return afterVerbFirstPrepositionIndex;
    }

    public void setAfterVerbFirstPrepositionIndex(int afterVerbFirstPrepositionIndex) {
        this.afterVerbFirstPrepositionIndex = afterVerbFirstPrepositionIndex;
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

    public RegexPatternData getBeforeVerbNounPhrase() {
        return beforeVerbNounPhrase;
    }

    public void setBeforeVerbNounPhrase(RegexPatternData beforeVerbNounPhrase) {
        this.beforeVerbNounPhrase = beforeVerbNounPhrase;
    }

    public RegexPatternData getAfterVerbNounPhrase() {
        return afterVerbNounPhrase;
    }

    public void setAfterVerbNounPhrase(RegexPatternData afterVerbNounPhrase) {
        this.afterVerbNounPhrase = afterVerbNounPhrase;
    }

    public boolean containsBeforeVerbNounPhrase() {
        return containsBeforeVerbNounPhrase;
    }

    public void setContainsBeforeVerbNounPhrase(boolean containsBeforeVerbNounPhrase) {
        this.containsBeforeVerbNounPhrase = containsBeforeVerbNounPhrase;
    }

    public boolean containsAfterVerbNounPhrase() {
        return containsAfterVerbNounPhrase;
    }

    public void setContainsAfterVerbNounPhrase(boolean containsAfterVerbNounPhrase) {
        this.containsAfterVerbNounPhrase = containsAfterVerbNounPhrase;
    }

    public RegexPatternData getVerbPhrase() {
        return verbPhrase;
    }

    public void setVerbPhrase(RegexPatternData verbPhrase) {
        this.verbPhrase = verbPhrase;
    }

    public VerbPhraseTypes getVerbPhraseTypes() {
        return verbPhraseTypes;
    }

    public void setVerbPhraseTypes(VerbPhraseTypes verbPhraseTypes) {
        this.verbPhraseTypes = verbPhraseTypes;
    }
}
