package com.trainingdataprocessor.validator;

import com.trainingdataprocessor.tokens.Tokenizer;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.trainingdataprocessor.cache.TagsCache.tagsCache;

/**
 * Created by Oliver on 8/5/2016.
 */
public class TrainingDataValidatorImpl implements TrainingDataValidator {

    private final static Logger LOGGER = Logger.getLogger(TrainingDataValidatorImpl.class.getName());

    private Tokenizer tokenizer;

    private ListComparator listComparator;

    public TrainingDataValidatorImpl(Tokenizer tokenizer, ListComparator listComparator) {
        this.tokenizer = tokenizer;
        this.listComparator = listComparator;
    }

    @Override
    public boolean validate(String testDataRow, int lineNumber) {
        LOGGER.info("ENTERING validate method of TrainingDataValidatorImpl... ");
        LOGGER.info("*********************************************************************");

        LOGGER.info("Validating < " + testDataRow + " > on line " + lineNumber);

        if (!(testDataRow.contains("#"))) {
            LOGGER.log(Level.SEVERE, "EXCEPTION: Test data row < " + testDataRow + " > on line " + lineNumber + " does not contain # as separator " +
                    "between sentence and tags.");
            return false;
        }
        final String[] sentenceAndTags = testDataRow.split("#");

        String sentenceAsString = sentenceAndTags[0];
        String tagsAsString = sentenceAndTags[1];

        List<String> tokensList = tokenizer.splitStringIntoList(sentenceAsString);
        List<String> tagsList = tokenizer.splitStringIntoList(tagsAsString);

        if (sentenceAsString.contains(",") && !(tagsAsString.contains(",")) ||
                !(sentenceAsString.contains(",")) && tagsAsString.contains(",")) {
            LOGGER.log(Level.SEVERE, "EXCEPTION: Sentence or tags does not contain comma but it should probably. Please check" +
                    " the data.");
            return false;
        }
        if (sentenceAsString.contains(",") && tagsAsString.contains(",")) {
            List<Integer> tokensCommaIndexes = tokenizer.getCommaIndexes(tokensList);
            List<Integer> tagsCommaIndexes = tokenizer.getCommaIndexes(tagsList);
            if (!(listComparator.compare(tokensCommaIndexes, tagsCommaIndexes))) {
                LOGGER.log(Level.SEVERE, "EXCEPTION: Comma indexes for tokens and tags are not equal");
                return false;
            }
        }
        if (tokensList.size() != tagsList.size()) {
            LOGGER.log(Level.SEVERE, "EXCEPTION: Test data row < " + testDataRow + " > on line " + lineNumber + " " +
                    " -> number of words in sentence is not equal to number of tags.");
            return false;
        }
        for (String tag : tagsList) {
            if (tag.contains(",")) {
                tag = tokenizer.removeCommaAndDot(tag);
            }
            if (!(tagsCache.contains(tag))) {
                LOGGER.log(Level.SEVERE, "EXCEPTION: Test data row < " + testDataRow + " > on line " + lineNumber + " " +
                        " -> contains an invalid tag: " + tag);
                return false;
            }
        }
        LOGGER.info("Validation OK");
        LOGGER.info("LEAVING validate method of TrainingDataValidatorImpl... ");
        LOGGER.info("*********************************************************************");
        return true;
    }

}
