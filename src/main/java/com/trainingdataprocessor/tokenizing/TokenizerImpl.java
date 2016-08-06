package com.trainingdataprocessor.tokenizing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 8/6/2016.
 */
public class TokenizerImpl implements Tokenizer {

    @Override
    public List<Integer> getCommaIndexes(List<Integer> commaIndexes, List<String> tokens) {
        int index = 0;
        for (final String token : tokens) {
            if (token.endsWith(",")) {
                commaIndexes.add(index);
            }
            index++;
        }
        return commaIndexes;
    }

}
