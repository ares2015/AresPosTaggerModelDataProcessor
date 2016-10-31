package com.trainingdataprocessor.factories;

import com.trainingdataprocessor.data.TestDataRow;
import com.trainingdataprocessor.encoding.TagsEncoder;
import com.trainingdataprocessor.tokenizing.Tokenizer;

import java.util.ArrayList;
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

        for (String testDataRowString : testDataRowStringList) {
            String[] sentenceAndTagsTwoItemsArray = testDataRowString.split("#");
            String sentenceAsString = sentenceAndTagsTwoItemsArray[0];
            String tagsAsString = sentenceAndTagsTwoItemsArray[1];
            LOGGER.info("Processing sentence < " + sentenceAsString + " > and tags < " +
                    tagsAsString + " > ");

            List<String> tokensList = tokenizer.splitStringIntoList(sentenceAsString);
            List<String> tagsList = tokenizer.splitStringIntoList(tagsAsString);

            if (sentenceAndTagsTwoItemsArray[0].contains(", ")) {
                List<List<String>> subSentences = subPathsListFactory.create(tokensList);
                List<List<String>> tagsListOfLists = subPathsListFactory.create(tagsList);
                LOGGER.info("Sentence contains " + subSentences.size() + " subSentences.");
                tokensList = removeCommasAndDots(tokensList);
                tagsList = removeCommasAndDots(tagsList);
                tagsAsString = tokenizer.convertListToString(tagsList);
                String encodedTagsSubPathAsString = tagsEncoder.encodeTagSubPath(tagsList);
                LOGGER.info("Tags were encoded as:" + encodedTagsSubPathAsString);
                List<List<String>> encodedTagsListOfLists = tagsEncoder.encodeTagsAsListOfLists(tagsListOfLists);
                List<String> encodedTagSubPathsList = tagsEncoder.encodeTagSubPathList(tagsListOfLists);
                TestDataRow testDataRow = new TestDataRow(true, sentenceAsString, tagsAsString,
                        encodedTagsSubPathAsString, tokensList, tagsList, subSentences, tagsListOfLists, encodedTagsListOfLists, encodedTagSubPathsList);
                testDataRowList.add(testDataRow);
            } else {
                LOGGER.info("Sentence does not contain any subSentences.");
                String encodedTagsAsString = tagsEncoder.encodeTagSubPath(tagsList);
                List<String> encodedTagSubPathsAsSingleList = tokenizer.splitStringWithoutEmptySpaceToList(encodedTagsAsString);

                TestDataRow testDataRow = new TestDataRow(false, sentenceAsString, tagsAsString, encodedTagsAsString, tokensList, tagsList, encodedTagSubPathsAsSingleList);
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
