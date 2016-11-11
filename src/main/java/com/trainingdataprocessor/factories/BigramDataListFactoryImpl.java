package com.trainingdataprocessor.factories;

import com.trainingdataprocessor.calculator.BigramProbabilityCalculator;
import com.trainingdataprocessor.data.BigramData;
import com.trainingdataprocessor.database.TrainingDataAccessor;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.trainingdataprocessor.cache.ConstantTagsCache.constantTagsCache;

public class BigramDataListFactoryImpl implements BigramDataListFactory {

    private final static Logger LOGGER = Logger.getLogger(BigramDataListFactoryImpl.class.getName());

    private TrainingDataAccessor trainingDataAccessor;

    private BigramProbabilityCalculator bigramProbabilityCalculator;

    public BigramDataListFactoryImpl(TrainingDataAccessor trainingDataAccessor, BigramProbabilityCalculator bigramProbabilityCalculator) {
        this.trainingDataAccessor = trainingDataAccessor;
        this.bigramProbabilityCalculator = bigramProbabilityCalculator;
    }

    public List<BigramData> create(List<String> tags) {
        LOGGER.info("ENTERING create method of BigramDataListFactoryImpl... ");
        LOGGER.info("*********************************************************************");
        long startTime = System.currentTimeMillis();
        List<BigramData> bigramDataList = new ArrayList<BigramData>();
        for (int i = 0; i < tags.size() - 1; i++) {
            String tag1 = tags.get(i);
            String tag2 = tags.get(i + 1);
            boolean isTag1Constant = constantTagsCache.contains(tag1);
            boolean isTag2Constant = constantTagsCache.contains(tag2);
            BigramData bigramData = new BigramData(tag1, tag2, isTag1Constant, isTag2Constant);
            trainingDataAccessor.populateBigramFrequencyData(bigramData);
            trainingDataAccessor.populateBigramTag1FrequencyData(bigramData);
            double bigramProbability = bigramProbabilityCalculator.calculate(bigramData.getBigramFrequency(), bigramData.getTag1Frequency());
            bigramData.setBigramProbability(bigramProbability);

            LOGGER.info("Processing bigram -> tag1: " + tag1 + ", tag2: " + tag2 + ", " + "isTag1Constant: " + isTag1Constant +
                    ", isTag2Constant: " + isTag2Constant + ", bigramProbability: " + bigramProbability + ", bigramFrequency: " +
                    bigramData.getBigramFrequency() + ", tag1Frequency: " + bigramData.getTag1Frequency());

            bigramDataList.add(bigramData);
        }
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        LOGGER.info("BigramDataList with size " + bigramDataList.size() + " was created in " + elapsedTime + " miliseconds.");
        LOGGER.info("LEAVING create method of BigramDataListFactoryImpl... ");
        LOGGER.info("*********************************************************************");
        return bigramDataList;
    }
}
