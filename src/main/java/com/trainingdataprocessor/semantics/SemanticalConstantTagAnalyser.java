package com.trainingdataprocessor.semantics;

import com.trainingdataprocessor.data.semantics.SemanticalConstantTagAnalysisData;

import java.util.List;

/**
 * Created by Oliver on 10/1/2016.
 */
public interface SemanticalConstantTagAnalyser {

    SemanticalConstantTagAnalysisData analyse(String constantTag, List<String> subSentence, List<String> encodedTags);

}
