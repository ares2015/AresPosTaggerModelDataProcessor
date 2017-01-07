package com.trainingdataprocessor.factories;


import com.trainingdataprocessor.data.syntax.SubPathData;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.trainingdataprocessor.cache.ConstantTagsCache.constantTagsCache;


public class SubPathDataListFactoryImpl implements SubPathDataListFactory {

    private final static Logger LOGGER = Logger.getLogger(SubPathDataListFactoryImpl.class.getName());

    public List<SubPathData> create(List<String> tags) {
        List<SubPathData> subPathDataList = new ArrayList<SubPathData>();
        LOGGER.info("ENTERING create method of SubPathDataListFactoryImpl... ");
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
                boolean containsConstant = subPathTemporaryObject.isConstantSubPath;
                String subPath = subPathTemporaryObject.subPath;
                LOGGER.info("Created new StartTagEndTag pair -> startIndex: " + startIndex + ", endIndex: " + endIndex +
                        ", startTag: " + startTag + ", endTag: " + endTag + ", isConstantSubPath: " + containsConstant +
                        ", subPath: " + subPathTemporaryObject.subPath);
                subPathDataList.add(new SubPathData(startTag, endTag, subPath, subPathLength, startIndex, endIndex, containsConstant));
            }
        }
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        LOGGER.info(subPathDataList.size() + " StarTagEndTag pairs were created in " + elapsedTime + " miliseconds.");
        LOGGER.info("LEAVING create method of SubPathDataListFactoryImpl... ");
        LOGGER.info("*********************************************************************");

        return subPathDataList;
    }

    private SubPathTemporaryObject getSubPathFromTagsList(int startIndex, int endIndex, List<String> tags) {
        boolean isConstantSubPath = false;
        List<String> subPath = tags.subList(startIndex, endIndex + 1);
        String subPathAsString = "";
        for (int i = 0; i < subPath.size(); i++) {
            if (i < subPath.size() - 1) {
                subPathAsString += subPath.get(i) + " ";
            } else {
                subPathAsString += subPath.get(i);
            }
            if (tags.size() > 2 && i > 0 && i < tags.size() - 1) {
                isConstantSubPath = constantTagsCache.contains(subPath.get(i));
            }
        }
        if (tags.size() == 2) {
            isConstantSubPath = isSubPathOfLength2ConstantSubPath(tags);
        }
        return new SubPathTemporaryObject(subPathAsString, isConstantSubPath);
    }

    private boolean isSubPathOfLength2ConstantSubPath(List<String> tags) {
        return constantTagsCache.contains(tags.get(0)) && constantTagsCache.contains(tags.get(1));
    }

    private class SubPathTemporaryObject {

        private String subPath;

        private boolean isConstantSubPath;

        public SubPathTemporaryObject(String subPath, boolean isConstantSubPath) {
            this.subPath = subPath;
            this.isConstantSubPath = isConstantSubPath;
        }
    }
}