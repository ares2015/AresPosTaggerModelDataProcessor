package com.trainingdataprocessor.cache;

import com.trainingdataprocessor.tags.EncodedTags;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Oliver on 11/2/2016.
 */
public final class SemanticAnalysisFilterCache {

    private final Set<String> tagsToFilterCache = new HashSet<>();

    private final Set<String> wordsToFilterCache = new HashSet<>();

    {
        tagsToFilterCache.add(EncodedTags.AND_OR);
        tagsToFilterCache.add(EncodedTags.HAVE);
        tagsToFilterCache.add(EncodedTags.CONJUNCTION);
        tagsToFilterCache.add(EncodedTags.PRONOUN_PERSONAL);
        tagsToFilterCache.add(EncodedTags.WH_DETERMINER);
        tagsToFilterCache.add(EncodedTags.WH_PRONOUN);
        tagsToFilterCache.add(EncodedTags.WH_ADVERB);
        tagsToFilterCache.add(EncodedTags.THERE);
        tagsToFilterCache.add(EncodedTags.HERE);

        wordsToFilterCache.add("the");
        wordsToFilterCache.add("a");
        wordsToFilterCache.add("an");

    }

    public Set<String> getTagsToFilterCache() {
        return tagsToFilterCache;
    }

    public Set<String> getWordsToFilterCache() {
        return wordsToFilterCache;
    }
}
