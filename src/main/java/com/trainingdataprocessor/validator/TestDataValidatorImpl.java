package com.trainingdataprocessor.validator;

import com.trainingdataprocessor.cache.TagsCache;

import java.util.IllegalFormatException;

/**
 * Created by Oliver on 8/5/2016.
 */
public class TestDataValidatorImpl implements TestDataValidator {

    private TagsCache tagsCache;

    public TestDataValidatorImpl(TagsCache tagsCache) {
        this.tagsCache = tagsCache;
    }

    @Override
    public void validate(String testDataRow, int lineNumber) {
        if (!(testDataRow.contains("#"))) {
            throw new IllegalStateException("Test data row: " + testDataRow + " on line: " + " does not contain # as separator " +
                    "between sentence and tags.");
        }
        final String[] sentenceAndTags = testDataRow.split("#");
        final String[] tokens = sentenceAndTags[0].split("\\ ");
        final String[] tags = sentenceAndTags[1].split("\\ ");
        if (tokens.length != tags.length) {
            throw new IllegalStateException("Test data row: " + testDataRow + " on line: " + lineNumber + " " +
                    " -> number of words in sentence is not equal to number of tags.");
        }
        for (String tag : tags) {
            if (!(tagsCache.getTagsCache().contains(tag))) {
                throw new IllegalStateException("Test data row: " + testDataRow + " on line: " + lineNumber + " " +
                        " -> this is an invalid tag: " + tag);
            }
        }
    }
}
