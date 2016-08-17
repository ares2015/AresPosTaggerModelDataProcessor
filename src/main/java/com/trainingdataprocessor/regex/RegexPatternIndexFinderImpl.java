package com.trainingdataprocessor.regex;

import com.trainingdataprocessor.data.RegexPatternIndexData;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Oliver on 8/17/2016.
 */
public class RegexPatternIndexFinderImpl implements RegexPatternIndexFinder {

    @Override
    public List<RegexPatternIndexData> find(String encodedPath, String regexPattern) {
        List<RegexPatternIndexData> regexPatternIndexDataList = new ArrayList<>();
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(encodedPath);
        while (matcher.find()) {
            RegexPatternIndexData regexPatternIndexData =
                    new RegexPatternIndexData(matcher.group(), matcher.start(), matcher.end());
            regexPatternIndexDataList.add(regexPatternIndexData);
        }
        return regexPatternIndexDataList;
    }

}
