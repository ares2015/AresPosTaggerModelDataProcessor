package com.trainingdataprocessor.semantics.preprocessing;

import java.util.List;

/**
 * Created by Oliver on 11/3/2016.
 */
public interface SemanticPreprocessingFilter {

    String filterToString(List<String> list);

    List<String> filterToList(List<String> list);


}
