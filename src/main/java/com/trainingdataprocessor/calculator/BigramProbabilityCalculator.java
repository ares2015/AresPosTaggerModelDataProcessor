package com.trainingdataprocessor.calculator;

/**
 * Created by Oliver on 8/1/2016.
 */
public interface BigramProbabilityCalculator {

    double calculate(int bigramFrequency, int tag1Frequency);
}
