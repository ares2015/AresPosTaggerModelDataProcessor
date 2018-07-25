package com.aresPosTaggerModelDataProcessor.validator;

/**
 * Created by Oliver on 8/5/2016.
 */
public interface TrainingDataValidator {

    boolean validate(String testDataRow, int lineNumber);

}
