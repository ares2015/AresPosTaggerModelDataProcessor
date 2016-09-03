package com.trainingdataprocessor.relationships;

import com.trainingdataprocessor.data.RegexPatternIndexData;
import com.trainingdataprocessor.data.RelationshipData;

import java.util.List;

/**
 * Created by Oliver on 8/31/2016.
 */
public interface RelationshipsExtractor<T extends RelationshipData>{

    List<T> extract(List<RegexPatternIndexData> patternIndexDataList, List<String> tokens);

}
