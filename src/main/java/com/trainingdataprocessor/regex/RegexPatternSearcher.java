package com.trainingdataprocessor.regex;

import com.trainingdataprocessor.data.regex.RegexPatternData;

import java.util.List;

/**
 * Created by Oliver on 8/17/2016.
 */
public interface RegexPatternSearcher {

    List<RegexPatternData> search(String encodedPath, String regexPattern);

}
