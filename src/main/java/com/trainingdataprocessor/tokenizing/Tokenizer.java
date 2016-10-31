package com.trainingdataprocessor.tokenizing;

import java.util.List;

/**
 * Created by Oliver on 8/6/2016.
 */
public interface Tokenizer {

    List<String> splitStringIntoList(String sentence);

    List<String> splitStringWithoutEmptySpaceToList(String token);

    List<Integer> getCommaIndexes(List<String> tokens);

    String removeCommaAndDot(final String token);

    String convertListToString(List<String> list);

    String convertSubListToString(List<String> list, int startIndex, int endIndex);

}
