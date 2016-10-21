package com.trainingdataprocessor.semantics.deprecated;

import com.trainingdataprocessor.data.RegexPatternData;
import com.trainingdataprocessor.data.semantics.deprecated.SemanticRelationData;

import java.util.List;

/**
 * Created by Oliver on 8/31/2016.
 */
public interface SemanticRelationsExtractor<T>{

    SemanticRelationData extract(String constant, RegexPatternData regexPatternData, List<String> tokens, List<String> encodedTags,
                                 SemanticRelationConstantType constantType);

}
