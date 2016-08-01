package com.trainingdataprocessor.bigrams;

import com.trainingdataprocessor.cache.ConstantTagsCache;
import com.trainingdataprocessor.data.BigramData;
import com.trainingdataprocessor.database.TrainingDataAccessor;

import java.util.ArrayList;
import java.util.List;

public class BigramDataListGeneratorImpl implements BigramDataListGenerator {

    private ConstantTagsCache constantTagsCache;

    private TrainingDataAccessor trainingDataAccessor;

    private BigramProbabilityCalculator bigramProbabilityCalculator;

    public BigramDataListGeneratorImpl(ConstantTagsCache constantTagsCache, TrainingDataAccessor trainingDataAccessor, BigramProbabilityCalculator bigramProbabilityCalculator) {
        this.constantTagsCache = constantTagsCache;
        this.trainingDataAccessor = trainingDataAccessor;
        this.bigramProbabilityCalculator = bigramProbabilityCalculator;
    }

    public List<BigramData> generate(List<String> tags) {
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
            bigramDataList.add(bigramData);
        }
        return bigramDataList;
    }
}
