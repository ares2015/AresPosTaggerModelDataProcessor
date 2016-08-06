package com.trainingdataprocessor.data.factories;

import java.util.List;

/**
 * Created by Oliver on 8/6/2016.
 */
public interface SubPathsListFactory {

    List<List<String>> create(List<Integer> commaIndexes, List<String> tags);
}
