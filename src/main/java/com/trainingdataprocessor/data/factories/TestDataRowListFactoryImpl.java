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

    public TestDataRowListFactoryImpl(Tokenizer tokenizer, TagsEncoder tagsEncoder) {
        this.tokenizer = tokenizer;
        this.tagsEncoder = tagsEncoder;
    }

    @Override
    public List<TestDataRow> create(List<String> testDataRowStringList) {
        List<TestDataRow> testDataRowList = new ArrayList<>();
        String tagsAsString;
        String[] sentenceAndTags;
        String[] tokens;
        String[] tags;
        List<List<String>> tagSubPaths;

        String[] subSentences;
        for(String testDataRowString : testDataRowStringList){
            sentenceAndTags = testDataRowString.split("#");
            tokens = sentenceAndTags[0].split("\\ ");
            tags = sentenceAndTags[1].split("\\ ");
            tagsAsString = sentenceAndTags[1];
            if(sentenceAndTags[0].contains(", ")){
                subSentences = sentenceAndTags[0].split("\\,");
                List<Integer> commaIndexes = new ArrayList<>();
                commaIndexes.add(0);
                commaIndexes = tokenizer.getCommaIndexes(commaIndexes, Arrays.asList(tags));
                commaIndexes.add(tags.length - 1);
                tagSubPaths = tokenizer.getTagSubPaths(commaIndexes, Arrays.asList(tags));
                String encodedTagPattern  = tagsEncoder.encode(tagsAsString);
            }

        }

        return testDataRowList;
    }


}
