package com.trainingdataprocessor.cache;

import com.trainingdataprocessor.tags.Tags;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Oliver on 8/5/2016.
 */
public final class TagsCache {

    private Set<String> tagsCache = new HashSet<String>();

    public Set<String> getTagsCache() {
        return tagsCache;
    }

    public TagsCache() {
        tagsCache.add(Tags.ADJECTIVE);
        tagsCache.add(Tags.ADVERB);
        tagsCache.add(Tags.AND_OR);
        tagsCache.add(Tags.CONJUNCTION);
        tagsCache.add(Tags.DETERMINER);
        tagsCache.add(Tags.HAVE);
        tagsCache.add(Tags.HERE);
        tagsCache.add(Tags.IS_ARE);
        tagsCache.add(Tags.MODAL_VERB);
        tagsCache.add(Tags.NOT);
        tagsCache.add(Tags.NOUN);
        tagsCache.add(Tags.NUMBER);
        tagsCache.add(Tags.PREPOSITION);
        tagsCache.add(Tags.PRONOUN_PERSONAL);
        tagsCache.add(Tags.PRONOUN_POSSESIVE);
        tagsCache.add(Tags.THERE);
        tagsCache.add(Tags.TO);
        tagsCache.add(Tags.VERB);
        tagsCache.add(Tags.VERB_ED);
        tagsCache.add(Tags.VERB_ING);
        tagsCache.add(Tags.WH_ADVERB);
        tagsCache.add(Tags.WH_DETERMINER);
        tagsCache.add(Tags.WH_PRONOUN);
        tagsCache.add(Tags.WH_PRONOUN_POSSESSIVE);
        tagsCache.add(Tags.QUANTIFIER);
    }

}
