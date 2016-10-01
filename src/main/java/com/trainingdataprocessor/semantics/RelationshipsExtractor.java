package com.trainingdataprocessor.semantics;

import com.trainingdataprocessor.data.RegexPatternIndexData;

import java.util.List;

/**
 * Created by Oliver on 8/31/2016.
 */
public interface RelationshipsExtractor<T>{

    List<T> extract(List<RegexPatternIndexData> patternIndexDataList, List<String> tokens, List<String> encodedTags);

}