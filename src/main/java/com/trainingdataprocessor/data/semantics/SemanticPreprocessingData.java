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

}
