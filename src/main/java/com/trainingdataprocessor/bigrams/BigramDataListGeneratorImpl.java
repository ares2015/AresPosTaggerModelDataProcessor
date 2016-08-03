package com.trainingdataprocessor.bigrams;

import com.trainingdataprocessor.cache.ConstantTagsCache;
import com.trainingdataprocessor.data.BigramData;
import com.trainingdataprocessor.database.TrainingDataAccessor;
import com.trainingdataprocessor.subpaths.StartTagEndTagPairsGeneratorImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BigramDataListGeneratorImpl implements BigramDataListGenerator {

    private final static Logger LOGGER = Logger.getLogger(BigramDataListGeneratorImpl.class.getName());

    private ConstantTagsCache constantTagsCache;

    private TrainingDataAccessor trainingDataAccessor;

    private BigramProbabilityCalculator bigramProbabilityCalculator;

    public BigramDataListGeneratorImpl(ConstantTagsCache constantTagsCache, TrainingDataAccessor trainingDataAccessor, BigramProbabilityCalculator bigramProbabilityCalculator) {
        this.constantTagsCache = constantTagsCache;
        this.trainingDataAccessor = trainingDataAccessor;
        this.bigramProbabilityCalculator = bigramProbabilityCalculator;
    }

    public List<BigramData> generate(List<String> tags) {
        LOGGER.info("ENTERING generate method of BigramDataListGeneratorImpl... ");
        LOGGER.info("*********************************************************************");
        long startTime = System.currentTimeMillis();
        List<BigramData> bigramDataList = new ArrayList<BigramData>();
        for (int i = 0; i < tags.size() - 1; i++) {
            String tag1 = tags.get(i);
            String tag2 = tags.get(i + 1);
            boolean isTag1Constant = constantTagsCache.getConstantTagsCache().contains(tag1);
            boolean isTag2Constant = constantTagsCache.getConstantTagsCache().contains(tag2);
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
        LOGGER.info("LEAVING generate method of BigramDataListGeneratorImpl... ");
        LOGGER.info("*********************************************************************");
        return bigramDataList;
    }
}
