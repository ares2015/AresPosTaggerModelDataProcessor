package com.trainingdataprocessor.calculator;

/**
 * Created by Oliver on 8/1/2016.
 */
public final class BigramProbabilityCalculator {

    /*
    P( wi | wi-1 ) = count ( wi-1, wi ) / count ( wi-1 )
    In english..
    Probability that word i-1 is followed by word i = [Number of times we saw word i-1 followed by word i] / [Number of times we saw word i-1]
     */
    public static double calculate(int bigramFrequency, int tag1Frequency) {
        return (((double) bigramFrequency / (double) tag1Frequency)) * 100;
    }

}
