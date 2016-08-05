package com.trainingdataprocessor.validator;

import com.trainingdataprocessor.cache.TagsCache;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Oliver on 8/5/2016.
 */
public class TestDataValidatorImpl implements TestDataValidator {

    private final static Logger LOGGER = Logger.getLogger(TestDataValidatorImpl.class.getName());

    private TagsCache tagsCache;

    public TestDataValidatorImpl(TagsCache tagsCache) {
        this.tagsCache = tagsCache;
    }

    @Override
    public void validate(String testDataRow, int lineNumber) {
        LOGGER.info("Validating < " + testDataRow + " > on line " + lineNumber);

        if (!(testDataRow.contains("#"))) {
            LOGGER.log(Level.SEVERE, "EXCEPTION: Test data row < " + testDataRow + " > on line " + lineNumber + " does not contain # as separator " +
                    "between sentence and tags.");
            throw new IllegalStateException("Test data row: " + testDataRow + " on line " + " does not contain # as separator " +
                    "between sentence and tags.");
        }
        final String[] sentenceAndTags = testDataRow.split("#");
        final String[] tokens = sentenceAndTags[0].split("\\ ");
        final String[] tags = sentenceAndTags[1].split("\\ ");
        if (tokens.length != tags.length) {
            LOGGER.log(Level.SEVERE, "EXCEPTION: Test data row < " + testDataRow + " > on line " + lineNumber + " " +
                    " -> number of words in sentence is not equal to number of tags.");
            throw new IllegalStateException("Test data row < " + testDataRow + " > on line " + lineNumber + " " +
                    " -> number of words in sentence is not equal to number of tags.");
        }
        for (String tag : tags) {
            if (!(tagsCache.getTagsCache().contains(tag))) {
                LOGGER.log(Level.SEVERE, "EXCEPTION: Test data row < " + testDataRow + " > on line " + lineNumber + " " +
                        " -> contains an invalid tag: " + tag);
                throw new IllegalStateException("Test data row < " + testDataRow + " > on line " + lineNumber + " " +
                        " -> contains an invalid tag: " + tag);
            }
        }
        LOGGER.info("Validation OK");
    }
}
