package com.trainingdataprocessor.factories;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.encoding.TagsEncoder;
import com.trainingdataprocessor.tokens.Tokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Oliver on 8/5/2016.
 */
public class TrainingDataRowListFactoryImpl implements TrainingDataRowListFactory {

    private final static Logger LOGGER = Logger.getLogger(TrainingDataRowListFactoryImpl.class.getName());

    private Tokenizer tokenizer;

    private TagsEncoder tagsEncoder;

    private MultiListFactory multiListFactory;

    public TrainingDataRowListFactoryImpl(Tokenizer tokenizer, TagsEncoder tagsEncoder, MultiListFactory multiListFactory) {
        this.tokenizer = tokenizer;
        this.tagsEncoder = tagsEncoder;
        this.multiListFactory = multiListFactory;
    }

    @Override
    public List<TrainingDataRow> create(List<String> testDataRowStringList) {
        LOGGER.info("ENTERING create method of TrainingDataRowListFactoryImpl... ");
        LOGGER.info("*********************************************************************");

        List<TrainingDataRow> trainingDataRowList = new ArrayList<>();

        for (String testDataRowString : testDataRowStringList) {
            TrainingDataRow trainingDataRow = new TrainingDataRow();
            String[] sentenceAndTagsTwoItemsArray = testDataRowString.split("#");

            String sentence = sentenceAndTagsTwoItemsArray[0];
            String subPath = sentenceAndTagsTwoItemsArray[1];

            LOGGER.info("Processing sentence < " + sentence + " > and tags < " +
                    subPath + " > ");

            List<String> tokensList = tokenizer.splitStringIntoList(sentence);
            List<String> tagsList = tokenizer.splitStringIntoList(subPath);


            if (sentenceAndTagsTwoItemsArray[0].contains(", ")) {
                trainingDataRow.setContainsSubSentences(true);
                //MULTILISTS ARE CREATED FIRST BEFORE COMMAS ARE REMOVED FROM TOKENS LIST AND TAGS LIST
                //SUB SENTENCES MULTILIST
                List<List<String>> subSentencesMultiList = multiListFactory.create(tokensList);
                trainingDataRow.setTokensMultiList(subSentencesMultiList);

                //TAGS MULTILIST
                List<List<String>> tagsMultiList = multiListFactory.create(tagsList);
                trainingDataRow.setTagsMultiList(tagsMultiList);

                LOGGER.info("Sentence contains " + subSentencesMultiList.size() + " subSentences.");

                tokensList = removeCommasAndDots(tokensList);
                tagsList = removeCommasAndDots(tagsList);

                //TOKENS LIST, TAGS LIST
                trainingDataRow.setTokensList(tokensList);
                trainingDataRow.setTagsList(tagsList);

                trainingDataRow.setEncodedPathAsString(tagsEncoder.encodeTagsListToEncodedSubPath(tagsList));

                //ENCODED TAGS MULTILIST
                List<List<String>> encodedTagsMultiList = tagsEncoder.encodeTagsMultiListToEncodedTagsMultiList(tagsMultiList);
                trainingDataRow.setEncodedTagsMultiList(encodedTagsMultiList);

                //ENCODED SUBPATHS LIST
                List<String> encodedSubPathsList = tagsEncoder.encodeTagMultiListToEncodedSubPathsList(tagsMultiList);
                trainingDataRow.setEncodedSubPathsAsStringList(encodedSubPathsList);

                trainingDataRowList.add(trainingDataRow);
            } else {
                trainingDataRow.setContainsSubSentences(false);
                LOGGER.info("Sentence does not contain any subSentences.");

                String encodedSubPath = tagsEncoder.encodeTagsListToEncodedSubPath(tagsList);
                trainingDataRow.setEncodedPathAsString(encodedSubPath);

                //TOKENS LIST, TAGS LIST, ENCODED TAGS LIST
                trainingDataRow.setTokensList(tokensList);
                trainingDataRow.setTagsList(tagsList);
                trainingDataRow.setEncodedTagsList(tagsEncoder.encodeTagsListToEncodedTagsList(tagsList));

                trainingDataRowList.add(trainingDataRow);
            }
        }
        LOGGER.info("LEAVING create method of SubPathDataListFactoryImpl... ");
        LOGGER.info("*********************************************************************");

        return trainingDataRowList;
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
