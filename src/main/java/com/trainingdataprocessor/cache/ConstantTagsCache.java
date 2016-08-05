package com.trainingdataprocessor.cache;


import com.trainingdataprocessor.tags.Tags;

import java.util.HashSet;
import java.util.Set;

public final class ConstantTagsCache {

    private Set<String> constantTagsCache = new HashSet<String>();

    public Set<String> getConstantTagsCache() {
        return constantTagsCache;
    }

    public ConstantTagsCache() {
        constantTagsCache.add(Tags.AND_OR);
        constantTagsCache.add(Tags.CONJUNCTION);
        constantTagsCache.add(Tags.DETERMINER);
        constantTagsCache.add(Tags.HAVE);
        constantTagsCache.add(Tags.HERE);
        constantTagsCache.add(Tags.IS_ARE);
        constantTagsCache.add(Tags.MODAL_VERB);
        constantTagsCache.add(Tags.NOT);
        constantTagsCache.add(Tags.NUMBER);
        constantTagsCache.add(Tags.PREPOSITION);
        constantTagsCache.add(Tags.PRONOUN_PERSONAL);
        constantTagsCache.add(Tags.PRONOUN_POSSESIVE);
        constantTagsCache.add(Tags.THERE);
        constantTagsCache.add(Tags.TO);
        constantTagsCache.add(Tags.WH_ADVERB);
        constantTagsCache.add(Tags.WH_DETERMINER);
        constantTagsCache.add(Tags.WH_PRONOUN);
        constantTagsCache.add(Tags.WH_PRONOUN_POSSESSIVE);
        constantTagsCache.add(Tags.QUANTIFIER);
    }
}
