package com.trainingdataprocessor.subpaths;

import com.trainingdataprocessor.data.StartTagEndTagPair;

import java.util.List;

/**
 * Created by Oliver on 7/25/2016.
 */
public interface StartTagEndTagPairsGenerator {

    List<StartTagEndTagPair> generate(List<String> tags);
}
