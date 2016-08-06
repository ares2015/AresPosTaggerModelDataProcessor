package com.trainingdataprocessor.tokenizing;

import java.util.List;

/**
 * Created by Oliver on 8/6/2016.
 */
public interface Tokenizer {

    List<Integer> getCommaIndexes(List<Integer> commaIndexes, final List<String> tokens);

    List<List<String>> getTagSubPaths(List<Integer> commaIndexes, List<String> tags);

}
