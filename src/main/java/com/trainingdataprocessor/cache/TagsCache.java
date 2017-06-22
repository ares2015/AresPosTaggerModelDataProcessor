package com.trainingdataprocessor.cache;

import com.trainingdataprocessor.tags.Tags;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Oliver on 8/5/2016.
 */
public final class TagsCache {

    public static Set<String> tagsCache = new HashSet<String>();

    static  {
        tagsCache.add(Tags.ADJECTIVE);
        tagsCache.add(Tags.ADVERB);
        tagsCache.add(Tags.AND_OR);
        tagsCache.add(Tags.CONJUNCTION);
        tagsCache.add(Tags.DETERMINER);
        tagsCache.add(Tags.DO);
        tagsCache.add(Tags.DO_NOT);
        tagsCache.add(Tags.FOREIGN_WORD);
        tagsCache.add(Tags.HAVE);
        tagsCache.add(Tags.HAVE_NOT);
        tagsCache.add(Tags.HERE);
        tagsCache.add(Tags.IS_ARE);
        tagsCache.add(Tags.IS_ARE_NOT);
        tagsCache.add(Tags.INTERJECTION);
        tagsCache.add(Tags.LIST_ITEM_MARKER);
        tagsCache.add(Tags.MODAL_VERB);
        tagsCache.add(Tags.MODAL_VERB_NOT);
        tagsCache.add(Tags.NOT);
        tagsCache.add(Tags.NOUN);
        tagsCache.add(Tags.NUMBER);
        tagsCache.add(Tags.PARTICLE);
        tagsCache.add(Tags.PREDETERMINER);
        tagsCache.add(Tags.PREPOSITION);
        tagsCache.add(Tags.PRONOUN_PERSONAL);
        tagsCache.add(Tags.PRONOUN_POSSESIVE);
        tagsCache.add(Tags.SYMBOL);
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
