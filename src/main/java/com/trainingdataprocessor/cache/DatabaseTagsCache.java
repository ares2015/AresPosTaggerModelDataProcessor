package com.trainingdataprocessor.cache;

import com.trainingdataprocessor.tags.Tags;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Oliver on 11/15/2016.
 */
public final class DatabaseTagsCache {

    public static Set<String> databaseTagsCache = new HashSet<String>();

    static  {
        databaseTagsCache.add(Tags.NOUN);
        databaseTagsCache.add(Tags.ADJECTIVE);
        databaseTagsCache.add(Tags.VERB);
        databaseTagsCache.add(Tags.VERB_ED);
        databaseTagsCache.add(Tags.VERB_ING);
        databaseTagsCache.add(Tags.ADVERB);
    }

}
