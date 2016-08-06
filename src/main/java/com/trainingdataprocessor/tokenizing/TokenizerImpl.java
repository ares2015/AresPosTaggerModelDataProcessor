package com.trainingdataprocessor.tokenizing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 8/6/2016.
 */
public class TokenizerImpl implements Tokenizer {

    /**
     * If token ends with "," then it is converted to the char array
     * and comma is removed by copying the char array into new array
     * ignoring the last comma element.
     *
     * @param token ending with comma.
     * @return String token without comma.
     */
    @Override
    public String removeCommaAndDot(final String token) {
        char[] charTmp;
        charTmp = token.toCharArray();
        final char[] charToken = new char[charTmp.length - 1];
        for (int i = 0; i < charTmp.length - 1; i++) {
            charToken[i] = charTmp[i];
        }
        final String tokenWithoutComma = new String(charToken);
        return tokenWithoutComma;
    }


}
