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

    private MultiListFactory multiListFactory;

    public TestDataRowListFactoryImpl(Tokenizer tokenizer, TagsEncoder tagsEncoder, MultiListFactory multiListFactory) {
        this.tokenizer = tokenizer;
        this.tagsEncoder = tagsEncoder;
        this.multiListFactory = multiListFactory;
    }

    @Override
    public List<TestDataRow> create(List<String> testDataRowStringList) {
        LOGGER.info("ENTERING create method of TestDataRowListFactoryImpl... ");
        LOGGER.info("*********************************************************************");

        List<TestDataRow> testDataRowList = new ArrayList<>();

        for (String testDataRowString : testDataRowStringList) {
            TestDataRow testDataRow = new TestDataRow();
            String[] sentenceAndTagsTwoItemsArray = testDataRowString.split("#");

            String sentence = sentenceAndTagsTwoItemsArray[0];
            String subPath = sentenceAndTagsTwoItemsArray[1];

            LOGGER.info("Processing sentence < " + sentence + " > and tags < " +
                    subPath + " > ");

            List<String> tokensList = tokenizer.splitStringIntoList(sentence);
            List<String> tagsList = tokenizer.splitStringIntoList(subPath);


            if (sentenceAndTagsTwoItemsArray[0].contains(", ")) {
                testDataRow.setContainsSubSentences(true);
                //MULTILISTS ARE CREATED FIRST BEFORE COMMAS ARE REMOVED FROM TOKENS LIST AND TAGS LIST
                //SUB SENTENCES MULTILIST
                List<List<String>> subSentencesMultiList = multiListFactory.create(tokensList);
                testDataRow.setTokensMultiList(subSentencesMultiList);

                //TAGS MULTILIST
                List<List<String>> tagsMultiList = multiListFactory.create(tagsList);
                testDataRow.setTagsMultiList(tagsMultiList);

                LOGGER.info("Sentence contains " + subSentencesMultiList.size() + " subSentences.");

                tokensList = removeCommasAndDots(tokensList);
                tagsList = removeCommasAndDots(tagsList);

                //TOKENS LIST, TAGS LIST
                testDataRow.setTokensList(tokensList);
                testDataRow.setTagsList(tagsList);

                //SENTENCE, SUBPATH AND ENCODED SUBPATH
                testDataRow.setSentence(sentence);
                testDataRow.setSubPath(subPath);
                testDataRow.setEncodedSubPath(tagsEncoder.encodeTagsListToEncodedSubPath(tagsList));

                //ENCODED TAGS MULTILIST
                List<List<String>> encodedTagsMultiList = tagsEncoder.encodeTagsMultiListToEncodedTagsMultiList(tagsMultiList);
                testDataRow.setEncodedTagsMultiList(encodedTagsMultiList);

                //ENCODED SUBPATHS LIST
                List<String> encodedSubPathsList = tagsEncoder.encodeTagMultiListToEncodedSubPathsList(tagsMultiList);
                testDataRow.setEncodedSubPathsList(encodedSubPathsList);

                testDataRowList.add(testDataRow);
            } else {
                testDataRow.setContainsSubSentences(false);
                LOGGER.info("Sentence does not contain any subSentences.");

                //SENTENCE, SUBPATH AND ENCODED SUBPATH
                testDataRow.setSentence(sentence);
                testDataRow.setSubPath(subPath);
                String encodedSubPath = tagsEncoder.encodeTagsListToEncodedSubPath(tagsList);
                testDataRow.setEncodedSubPath(encodedSubPath);

                //TOKENS LIST, TAGS LIST, ENCODED TAGS LIST
                testDataRow.setTokensList(tokensList);
                testDataRow.setTagsList(tagsList);
                testDataRow.setEncodedTagsList(tagsEncoder.encodeTagsListToEncodedTagsList(tagsList));

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
