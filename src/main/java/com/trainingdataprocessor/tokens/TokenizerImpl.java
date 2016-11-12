package com.trainingdataprocessor.tokens;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 8/6/2016.
 */
public class TokenizerImpl implements Tokenizer {


    @Override
    public List<String> splitStringIntoList(String sentence) {
        String[] tokTmp;
        tokTmp = sentence.split("\\ ");
        final List<String> tokens = removeEmptyStringInSentence(tokTmp);
        return tokens;
    }

    @Override
    public List<String> splitStringWithoutEmptySpaceToList(String token) {
        List<String> list = new ArrayList<>();
        char[] chars = token.toCharArray();
        for (char c : chars) {
            String s = String.valueOf(c);
            list.add(s);
        }
        return list;
    }

    @Override
    public List<Integer> getCommaIndexes(final List<String> tokens) {
        final List<Integer> commaIndexes = new ArrayList<Integer>();
        int index = 0;
        for (final String token : tokens) {
            if (token.endsWith(",")) {
                commaIndexes.add(index);
            }
            index++;
        }
        return commaIndexes;
    }

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

    @Override
    public String convertListToString(List<String> list) {
        String newString = "";
        int i = 0;
        for (String word : list) {
            if (i < list.size() - 1) {
                newString += word + " ";
            } else {
                newString += word;
            }
            i++;
        }
        return newString;
    }

    @Override
    public String convertSubListToString(List<String> list, int startIndex, int endIndex) {
        String s = "";
        for (int i = startIndex; i <= endIndex - 1; i++) {
            if (i < endIndex) {
                s += list.get(i) + " ";
            } else {
                s += list.get(i);
            }
        }
        return s;
    }

    /**
     * Removes empty String that is located in sentences with index > 0
     * that is created by empty space behind each sentence (space between
     * end of the sentence and the start of the new sentence). It would be more
     * efficient to remove only first token (empty string) but this loop guarantees
     * that all accidental empty strings are removed.
     *
     * @param tokens Array of tokens with empty strings.
     * @return List of tokens without empty strings.
     */
    private List<String> removeEmptyStringInSentence(final String[] tokens) {
        final List<String> listTokens = new ArrayList<String>();
        for (final String token : tokens) {
            if (!token.equals("")) {
                listTokens.add(token);
            }
        }
        return listTokens;
    }


}
