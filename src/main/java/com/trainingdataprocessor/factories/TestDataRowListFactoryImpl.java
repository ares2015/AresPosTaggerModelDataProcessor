package com.trainingdataprocessor.factories;

import com.trainingdataprocessor.data.TestDataRow;
import com.trainingdataprocessor.encoding.TagsEncoder;
import com.trainingdataprocessor.tokenizing.Tokenizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Oliver on 8/5/2016.
 */
public class TestDataRowListFactoryImpl implements TestDataRowListFactory {

    private final static Logger LOGGER = Logger.getLogger(TestDataRowListFactoryImpl.class.getName());

    private Tokenizer tokenizer;

    private TagsEncoder tagsEncoder;

    private SubPathsListFactory subPathsListFactory;

    public TestDataRowListFactoryImpl(Tokenizer tokenizer, TagsEncoder tagsEncoder, SubPathsListFactory subPathsListFactory) {
        this.tokenizer = tokenizer;
        this.tagsEncoder = tagsEncoder;
        this.subPathsListFactory = subPathsListFactory;
    }

    @Override
    public List<TestDataRow> create(List<String> testDataRowStringList) {
        LOGGER.info("ENTERING create method of TestDataRowListFactoryImpl... ");
        LOGGER.info("*********************************************************************");

        List<TestDataRow> testDataRowList = new ArrayList<>();
        String sentenceAsString;
        String tagsAsString;
        String encodedTagsAsString;
        List<String> tokensList;
        List<String> tagsList;
        List<List<String>> tagSubPaths;
        List<List<String>> subSentences;
        String[] sentenceAndTagsTwoItemsArray;

        for (String testDataRowString : testDataRowStringList) {
            sentenceAndTagsTwoItemsArray = testDataRowString.split("#");
            sentenceAsString = sentenceAndTagsTwoItemsArray[0];
            tagsAsString = sentenceAndTagsTwoItemsArray[1];
            LOGGER.info("Processing sentence < " + sentenceAsString + " > and tags < " +
                    tagsAsString + " > ");

            tokensList = tokenizer.splitStringIntoList(sentenceAsString);
            tagsList = tokenizer.splitStringIntoList(tagsAsString);

            if (sentenceAndTagsTwoItemsArray[0].contains(", ")) {
                subSentences = subPathsListFactory.create(tokensList);
                tagSubPaths = subPathsListFactory.create(tagsList);
                LOGGER.info("Sentence contains " + subSentences.size() + " subSentences.");
                tokensList = removeCommasAndDots(tokensList);
                tagsList = removeCommasAndDots(tagsList);
                tagsAsString = tokenizer.convertListToString(tagsList);
                encodedTagsAsString = tagsEncoder.encode(tagsList);
                LOGGER.info("Tags were encoded as:" + encodedTagsAsString);
                TestDataRow testDataRow = new TestDataRow(sentenceAsString, tagsAsString,
                        encodedTagsAsString, tokensList, tagsList, subSentences, tagSubPaths);
                testDataRowList.add(testDataRow);
            } else {
                LOGGER.info("Sentence does not contain any subSentences.");
                encodedTagsAsString = tagsEncoder.encode(tagsList);

                TestDataRow testDataRow = new TestDataRow(sentenceAsString, tagsAsString, encodedTagsAsString, tokensList, tagsList);
                testDataRowList.add(testDataRow);
            }
        }
        LOGGER.info("LEAVING create method of StartTagEndTagPairsListFactoryImpl... ");
        LOGGER.info("*********************************************************************");

        return testDataRowList;
    }

    private List<String> removeCommasAndDots(List<String> words) {
        List<String> newWords = new ArrayList<>();
        for (String word : words) {
            if (word.endsWith(",")) {
                String newWord = tokenizer.removeCommaAndDot(word);
                newWords.add(newWord);
            } else {
                newWords.add(word);
            }
        }
        return newWords;
    }

}
