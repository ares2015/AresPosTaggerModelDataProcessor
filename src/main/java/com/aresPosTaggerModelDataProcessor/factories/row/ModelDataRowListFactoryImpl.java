package com.aresPosTaggerModelDataProcessor.factories.row;

import com.aresPosTaggerModelDataProcessor.data.preprocessing.ModelDataRow;
import com.aresPosTaggerModelDataProcessor.factories.multilist.MultiListFactory;
import com.aresPosTaggerModelDataProcessor.tokens.Tokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Oliver on 8/5/2016.
 */
public class ModelDataRowListFactoryImpl implements ModelDataRowListFactory {

    private final static Logger LOGGER = Logger.getLogger(ModelDataRowListFactoryImpl.class.getName());

    private Tokenizer tokenizer;

    private MultiListFactory multiListFactory;

    public ModelDataRowListFactoryImpl(Tokenizer tokenizer, MultiListFactory multiListFactory) {
        this.tokenizer = tokenizer;
        this.multiListFactory = multiListFactory;
    }

    @Override
    public List<ModelDataRow> create(List<String> testDataRowStringList) {
        LOGGER.info("ENTERING create method of ModelDataRowListFactoryImpl... ");
        LOGGER.info("*********************************************************************");

        List<ModelDataRow> modelDataRowList = new ArrayList<>();

        for (String testDataRowString : testDataRowStringList) {
            ModelDataRow modelDataRow = new ModelDataRow();
            String[] sentenceAndTagsTwoItemsArray = testDataRowString.split("#");

            String sentence = sentenceAndTagsTwoItemsArray[0];
            String subPath = sentenceAndTagsTwoItemsArray[1];

            LOGGER.info("Processing sentence < " + sentence + " > and tags < " +
                    subPath + " > ");

            List<String> tokensList = tokenizer.splitStringIntoList(sentence);
            List<String> tagsList = tokenizer.splitStringIntoList(subPath);


            if (sentenceAndTagsTwoItemsArray[0].contains(", ")) {
                modelDataRow.setContainsSubSentences(true);
                //MULTILISTS ARE CREATED FIRST BEFORE COMMAS ARE REMOVED FROM TOKENS LIST AND TAGS LIST
                //SUB SENTENCES MULTILIST
                List<List<String>> subSentencesMultiList = multiListFactory.create(tokensList);
                modelDataRow.setTokensMultiList(subSentencesMultiList);

                //TAGS MULTILIST
                List<List<String>> tagsMultiList = multiListFactory.create(tagsList);
                modelDataRow.setTagsMultiList(tagsMultiList);

                LOGGER.info("Sentence contains " + subSentencesMultiList.size() + " subSentences.");

                //TOKENS LIST, TAGS LIST
                tokensList = removeCommasAndDots(tokensList);
                tagsList = removeCommasAndDots(tagsList);
                modelDataRow.setTokensList(tokensList);
                modelDataRow.setTagsList(tagsList);

                modelDataRowList.add(modelDataRow);
            } else {
                modelDataRow.setContainsSubSentences(false);
                LOGGER.info("Sentence does not contain any subSentences.");

                //TOKENS LIST, TAGS LIST, ENCODED TAGS LIST
                modelDataRow.setTokensList(tokensList);
                modelDataRow.setTagsList(tagsList);

                modelDataRowList.add(modelDataRow);
            }
        }
        LOGGER.info("LEAVING create method of SubPathDataListFactoryImpl... ");
        LOGGER.info("*********************************************************************");

        return modelDataRowList;
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
