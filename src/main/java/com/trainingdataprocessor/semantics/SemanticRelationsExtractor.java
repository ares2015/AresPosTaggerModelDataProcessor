package com.trainingdataprocessor.semantics;

import com.trainingdataprocessor.data.RegexPatternIndexData;
import com.trainingdataprocessor.data.semantics.SemanticRelationData;

import java.util.List;

/**
 * Created by Oliver on 8/31/2016.
 */
public interface SemanticRelationsExtractor<T>{

    SemanticRelationData extract(String constant, RegexPatternIndexData regexPatternIndexData, List<String> tokens, List<String> encodedTags,
                                 SemanticRelationConstantType constantType);

}
