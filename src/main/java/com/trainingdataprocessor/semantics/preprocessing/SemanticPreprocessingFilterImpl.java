package com.trainingdataprocessor.semantics.preprocessing;

import com.trainingdataprocessor.cache.SemanticAnalysisFilterCache;
import com.trainingdataprocessor.tags.EncodedTags;
import com.trainingdataprocessor.tokenizing.Tokenizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 11/3/2016.
 */
public class SemanticPreprocessingFilterImpl implements SemanticPreprocessingFilter {

    private Tokenizer tokenizer;

    public SemanticPreprocessingFilterImpl(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    @Override
    public String filterToString(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String item : list) {
            if (!isStopItem(item)) {
                stringBuilder.append(item);
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public List<String> filterToList(List<String> list) {
        List<String> filteredList = new ArrayList<>();
        for (String item : list) {
            if (!isStopItem(item)) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    private boolean isStopItem(String item) {
        return (SemanticAnalysisFilterCache.wordsToFilterCache.contains(item))
                || (EncodedTags.DETERMINER.equals(item));
    }
}
