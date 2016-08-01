package com.trainingdataprocessor.bigrams;

/**
 * Created by Oliver on 8/1/2016.
 */
public class BigramProbabilityCalculatorImpl implements BigramProbabilityCalculator {

    /*
    P( wi | wi-1 ) = count ( wi-1, wi ) / count ( wi-1 )
    In english..
    Probability that word i-1 is followed by word i = [Number of times we saw word i-1 followed by word i] / [Number of times we saw word i-1]
     */
    public double calculate(int bigramFrequency, int tag1Frequency) {
        return (((double) bigramFrequency / (double) tag1Frequency)) * 100;
    }

}
