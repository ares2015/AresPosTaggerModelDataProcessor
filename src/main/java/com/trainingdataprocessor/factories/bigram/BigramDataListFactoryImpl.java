package com.trainingdataprocessor.factories.bigram;

import com.trainingdataprocessor.data.syntax.BigramData;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.trainingdataprocessor.cache.ConstantTagsCache.constantTagsCache;

public class BigramDataListFactoryImpl implements BigramDataListFactory {

    private final static Logger LOGGER = Logger.getLogger(BigramDataListFactoryImpl.class.getName());

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
            LOGGER.info("Processing bigram -> tag1: " + tag1 + ", tag2: " + tag2 + ", " + "isTag1Constant: " + isTag1Constant +
                    ", isTag2Constant: " + isTag2Constant);
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