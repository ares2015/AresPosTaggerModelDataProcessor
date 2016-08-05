package com.trainingdataprocessor.data.factories;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.trainingdataprocessor.cache.ConstantTagsCache;
import com.trainingdataprocessor.data.StartTagEndTagPair;


public class StartTagEndTagPairsListFactoryImpl implements StartTagEndTagPairsListFactory {

    private final static Logger LOGGER = Logger.getLogger(StartTagEndTagPairsListFactoryImpl.class.getName());

    private ConstantTagsCache constantTagsCache;

    public StartTagEndTagPairsListFactoryImpl(ConstantTagsCache constantTagsCache) {
        this.constantTagsCache = constantTagsCache;
    }

    public List<StartTagEndTagPair> create(List<String> tags) {
        List<StartTagEndTagPair> startTagEndTagPairList = new ArrayList<StartTagEndTagPair>();
        LOGGER.info("ENTERING create method of StartTagEndTagPairsListFactoryImpl... ");
        LOGGER.info("*********************************************************************");

        long startTime = System.currentTimeMillis();

        for (int i = 0; i <= tags.size(); i++) {
            for (int j = i + 1; j < tags.size(); j++) {
                int subPathLength = (j - i) + 1;
                int startIndex = i;
                int endIndex = j;
                String startTag = tags.get(i);
                String endTag = tags.get(j);
                SubPathTemporaryObject subPathTemporaryObject = getSubPathFromTagsList(i, j, tags);
                boolean containsConstant = subPathTemporaryObject.containsConstant;
                String subPath = subPathTemporaryObject.subPath;
                LOGGER.info("Created new StartTagEndTag pair -> startIndex: " + startIndex + ", endIndex: " + endIndex +
                        ", startTag: " + startTag + ", endTag: " + endTag + ", containsConstant: " + containsConstant +
                        ", subPath: " + subPathTemporaryObject.subPath);
                startTagEndTagPairList.add(new StartTagEndTagPair(startTag, endTag, subPath, subPathLength, startIndex, endIndex, containsConstant));
            }
        }
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        LOGGER.info(startTagEndTagPairList.size() + " StarTagEndTag pairs were created in " + elapsedTime + " miliseconds.");
        LOGGER.info("LEAVING create method of StartTagEndTagPairsListFactoryImpl... ");
        LOGGER.info("*********************************************************************");

        return startTagEndTagPairList;
    }

    private SubPathTemporaryObject getSubPathFromTagsList(int startIndex, int endIndex, List<String> tags) {
        boolean containsConstant = false;
        List<String> subPath = tags.subList(startIndex, endIndex + 1);
        String subPathAsString = "";
        for (int i = 0; i < subPath.size(); i++) {
            if (i < subPath.size() - 1) {
                subPathAsString += subPath.get(i) + " ";
            } else {
                subPathAsString += subPath.get(i);
            }
            if (i > 0 && i < tags.size() - 1) {
                containsConstant = constantTagsCache.getConstantTagsCache().contains(subPath.get(i));
            }
        }
        return new SubPathTemporaryObject(subPathAsString, containsConstant);
    }

    private class SubPathTemporaryObject {

        private String subPath;

        private boolean containsConstant;

        public SubPathTemporaryObject(String subPath, boolean containsConstant) {
            this.subPath = subPath;
            this.containsConstant = containsConstant;
        }
    }
}
