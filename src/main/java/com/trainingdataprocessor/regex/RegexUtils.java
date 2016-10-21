package com.trainingdataprocessor.regex;

import com.trainingdataprocessor.data.RegexPatternData;

import java.util.List;

/**
 * Created by Oliver on 10/20/2016.
 */
public final class RegexUtils {

    public static final boolean containsRegex(List<RegexPatternData> regexList, String regex, String regexType) {
        for (RegexPatternData regexPatternData : regexList) {
            if (regexPatternData.getPattern().equals(regex)) {
                System.out.println(regexType + "-> " + regex);
                return true;
            }
        }
        return false;
    }

    public static final boolean containSingleTagInRegex(List<RegexPatternData> regexList, String encodedTag) {
        for (RegexPatternData regexPatternData : regexList) {
            if (regexPatternData.getPattern().contains(encodedTag)) {
                return true;
            }
        }
        return false;
    }


}