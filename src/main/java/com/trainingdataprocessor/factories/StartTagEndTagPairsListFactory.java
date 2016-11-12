package com.trainingdataprocessor.factories;

import com.trainingdataprocessor.data.syntax.StartTagEndTagPair;

import java.util.List;

/**
 * Created by Oliver on 7/25/2016.
 */
public interface StartTagEndTagPairsListFactory {

    List<StartTagEndTagPair> create(List<String> tags);

}
