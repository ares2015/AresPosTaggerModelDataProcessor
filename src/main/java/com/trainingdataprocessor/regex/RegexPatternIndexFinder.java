package com.trainingdataprocessor.regex;

import com.trainingdataprocessor.data.RegexPatternIndexData;

import java.util.List;

/**
 * Created by Oliver on 8/17/2016.
 */
public interface RegexPatternIndexFinder {

    List<RegexPatternIndexData> find(String encodedPath, String regexPattern);
}
