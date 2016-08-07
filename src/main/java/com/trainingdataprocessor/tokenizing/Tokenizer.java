package com.trainingdataprocessor.tokenizing;

import java.util.List;

/**
 * Created by Oliver on 8/6/2016.
 */
public interface Tokenizer {

    List<String> splitStringIntoList(String sentence);

    List<Integer> getCommaIndexes(List<String> tokens);

    String removeCommaAndDot(final String token);

}
