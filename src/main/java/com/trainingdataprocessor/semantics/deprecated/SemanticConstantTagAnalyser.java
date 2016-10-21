package com.trainingdataprocessor.semantics.deprecated;

import com.trainingdataprocessor.data.semantics.SemanticalConstantTagAnalysisData;

import java.util.List;

/**
 * Created by Oliver on 10/1/2016.
 */
public interface SemanticConstantTagAnalyser {

    SemanticalConstantTagAnalysisData analyse(String constantTag, List<String> subSentence, List<String> encodedTags,
                                              SemanticRelationConstantType constantType);

}
