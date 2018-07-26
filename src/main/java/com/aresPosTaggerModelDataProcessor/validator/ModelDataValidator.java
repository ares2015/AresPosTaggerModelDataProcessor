package com.aresPosTaggerModelDataProcessor.validator;

/**
 * Created by Oliver on 8/5/2016.
 */
public interface ModelDataValidator {

    boolean validate(String modelDataRow, int lineNumber);

}
