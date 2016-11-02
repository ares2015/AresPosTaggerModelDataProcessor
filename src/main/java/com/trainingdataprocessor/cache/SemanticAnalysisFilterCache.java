package com.trainingdataprocessor.cache;

import com.trainingdataprocessor.tags.EncodedTags;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Oliver on 11/2/2016.
 */
public final class SemanticAnalysisFilterCache {

    private final Set<String> stopTagsCache = new HashSet<>();

    private final Set<String> databaseFilterWordsCache = new HashSet<>();

    {
        stopTagsCache.add(EncodedTags.AND_OR);
        stopTagsCache.add(EncodedTags.HAVE);
        stopTagsCache.add(EncodedTags.CONJUNCTION);
        stopTagsCache.add(EncodedTags.PRONOUN_PERSONAL);
        stopTagsCache.add(EncodedTags.WH_DETERMINER);
        stopTagsCache.add(EncodedTags.WH_PRONOUN);
        stopTagsCache.add(EncodedTags.WH_ADVERB);
        stopTagsCache.add(EncodedTags.THERE);
        stopTagsCache.add(EncodedTags.HERE);

        databaseFilterWordsCache.add("the");
        databaseFilterWordsCache.add("a");
        databaseFilterWordsCache.add("an");

    }

    public Set<String> getStopTagsCache() {
        return stopTagsCache;
    }

    public Set<String> getDatabaseFilterWordsCache() {
        return databaseFilterWordsCache;
    }
}
