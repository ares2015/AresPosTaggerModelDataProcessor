package com.trainingdataprocessor.tokenizing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 8/6/2016.
 */
public class TokenizerImpl implements Tokenizer {

    @Override
    public List<Integer> getCommaIndexes(List<Integer> commaIndexes, List<String> tokens) {
        int index = 0;
        for (final String token : tokens) {
            if (token.endsWith(",")) {
                commaIndexes.add(index);
            }
            index++;
        }
        return commaIndexes;
    }

    @Override
    public List<List<String>> getTagSubPaths(List<Integer> commaIndexes, List<String> tags) {
        List<List<String>> tagSubPaths = new ArrayList<List<String>>();
        int startIndex;
        int endIndex;
        for (int i = 0; i < commaIndexes.size() - 1; i++) {
            if(commaIndexes.get(i) == 0){
                startIndex = commaIndexes.get(i);
            }else{
                startIndex = commaIndexes.get(i) + 1;
            }
            endIndex = commaIndexes.get(i + 1);
            List<String> tagSubPath = tags.subList(startIndex, endIndex + 1);
            tagSubPaths.add(tagSubPath);
        }
        return tagSubPaths;
    }

}
