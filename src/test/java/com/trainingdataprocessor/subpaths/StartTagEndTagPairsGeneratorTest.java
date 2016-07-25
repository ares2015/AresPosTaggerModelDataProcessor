package com.trainingdataprocessor.subpaths;


import com.trainingdataprocessor.cache.ConstantTagsCache;
import com.trainingdataprocessor.cache.ConstantWordsCache;
import com.trainingdataprocessor.data.StartTagEndTagPair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StartTagEndTagPairsGeneratorTest {

    ConstantTagsCache constantTagsCache = new ConstantTagsCache();
    StartTagEndTagPairsGenerator startTagEndTagPairsGenerator = new StartTagEndTagPairsGeneratorImpl(constantTagsCache);

    @Test
    public void testGenerate(){

        List<String> tags = new ArrayList<String>();
        tags.add("N");
        tags.add("V");
        tags.add("DET");
        tags.add("N");

        List<StartTagEndTagPair> startTagEndTagPairList = startTagEndTagPairsGenerator.generate(tags);
        assertEquals(6, startTagEndTagPairList.size());


    }
}
