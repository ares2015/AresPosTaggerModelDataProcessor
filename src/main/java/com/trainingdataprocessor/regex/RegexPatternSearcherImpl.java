package com.trainingdataprocessor.regex;

import com.trainingdataprocessor.data.RegexPatternData;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Oliver on 8/17/2016.
 */
public class RegexPatternSearcherImpl implements RegexPatternSearcher {

    @Override
    public List<RegexPatternData> search(String encodedPath, String regexPattern) {
        List<RegexPatternData> regexPatternDataList = new ArrayList<>();
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(encodedPath);
        while (matcher.find()) {
            RegexPatternData regexPatternData =
                    new RegexPatternData(matcher.group(), matcher.start(), matcher.end());
            regexPatternDataList.add(regexPatternData);
        }
        return regexPatternDataList;
    }

}
