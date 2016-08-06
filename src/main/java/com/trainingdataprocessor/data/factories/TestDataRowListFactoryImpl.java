package com.trainingdataprocessor.data.factories;

import com.trainingdataprocessor.data.TestDataRow;
import com.trainingdataprocessor.encoding.TagsEncoder;
import com.trainingdataprocessor.tokenizing.Tokenizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Oliver on 8/5/2016.
 */
public class TestDataRowListFactoryImpl implements TestDataRowListFactory {

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
        List<TestDataRow> testDataRowList = new ArrayList<>();
        String sentence;
        List<String> tokensList;
        String tagsAsString;
        String encodedTagsAsString;
        List<String> tagsList;
        List<List<String>> tagSubPaths;
        List<List<String>> subSentences = null;

        String[] sentenceAndTags;
        String[] subSentencesArray;

        for(String testDataRowString : testDataRowStringList){
            sentenceAndTags = testDataRowString.split("#");
            String[] tokens = sentenceAndTags[0].split("\\ ");
            String[] tags = sentenceAndTags[1].split("\\ ");
            sentence = sentenceAndTags[0];
            tokensList = Arrays.asList(tokens);
            tagsList = Arrays.asList(tags);
            tagsAsString = sentenceAndTags[1];
            encodedTagsAsString  = tagsEncoder.encode(tagsList);
            if(sentenceAndTags[0].contains(", ")){
                subSentencesArray = sentenceAndTags[0].split("\\,");
//                subSentences = Arrays.asList(subSentencesArray);
                List<Integer> commaIndexes = new ArrayList<>();
                commaIndexes.add(0);
                commaIndexes = tokenizer.getCommaIndexes(commaIndexes, tagsList);
                commaIndexes.add(tags.length - 1);
                tagSubPaths = subPathsListFactory.create(commaIndexes, tagsList);
                TestDataRow testDataRow = new TestDataRow(sentence, tokensList, tagsAsString,
                        encodedTagsAsString, tagsList, subSentences, tagSubPaths);
                testDataRowList.add(testDataRow);
            }else{
                TestDataRow testDataRow = new TestDataRow(sentence, tokensList, tagsAsString, encodedTagsAsString, tagsList);
                testDataRowList.add(testDataRow);
            }
        }

        return testDataRowList;
    }


}
