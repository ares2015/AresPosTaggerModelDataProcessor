package com.trainingdataprocessor.bigrams;

import com.trainingdataprocessor.data.BigramData;

import java.util.List;

/**
 * Created by Oliver on 8/1/2016.
 */
public interface BigramDataListGenerator {

    List<BigramData> generate(List<String> tags);
}
