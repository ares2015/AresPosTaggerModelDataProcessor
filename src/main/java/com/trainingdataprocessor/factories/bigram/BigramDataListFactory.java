package com.trainingdataprocessor.factories.bigram;

import com.trainingdataprocessor.data.syntax.BigramData;

import java.util.List;

/**
 * Created by Oliver on 8/1/2016.
 */
public interface BigramDataListFactory {

    List<BigramData> create(List<String> tags);
}
