package com.trainingdataprocessor.factories;

import java.util.List;

/**
 * Created by Oliver on 8/6/2016.
 */
public interface MultiListFactory {

    List<List<String>> create(List<String> tags);

}
