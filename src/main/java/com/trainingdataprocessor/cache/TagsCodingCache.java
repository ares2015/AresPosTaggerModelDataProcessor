package com.trainingdataprocessor.cache;

import com.trainingdataprocessor.tags.EncodedTags;
import com.trainingdataprocessor.tags.Tags;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Oliver on 8/6/2016.
 */
public class TagsCodingCache {

    public static Map<String,String> encodingMap = new HashMap<String,String>();

    public static Map<String,String> decodingMap = new HashMap<String,String>();

    static {
        encodingMap.put(Tags.ADJECTIVE, EncodedTags.ADJECTIVE);
        encodingMap.put(Tags.ADVERB, EncodedTags.ADVERB);
        encodingMap.put(Tags.AND_OR, EncodedTags.AND_OR);
        encodingMap.put(Tags.CONJUNCTION, EncodedTags.CONJUNCTION);
        encodingMap.put(Tags.DETERMINER, EncodedTags.DETERMINER);
        encodingMap.put(Tags.DO, EncodedTags.DO);
        encodingMap.put(Tags.FOREIGN_WORD, EncodedTags.FOREIGN_WORD);
        encodingMap.put(Tags.HAVE, EncodedTags.HAVE);
        encodingMap.put(Tags.HERE, EncodedTags.HERE);
        encodingMap.put(Tags.IS_ARE, EncodedTags.IS_ARE);
        encodingMap.put(Tags.INTERJECTION, EncodedTags.INTERJECTION);
        encodingMap.put(Tags.LIST_ITEM_MARKER, EncodedTags.LIST_ITEM_MARKER);
        encodingMap.put(Tags.MODAL_VERB, EncodedTags.MODAL_VERB);
        encodingMap.put(Tags.NOT, EncodedTags.NOT);
        encodingMap.put(Tags.NOUN, EncodedTags.NOUN);
        encodingMap.put(Tags.NUMBER, EncodedTags.NUMBER);
        encodingMap.put(Tags.PARTICLE, EncodedTags.PARTICLE);
        encodingMap.put(Tags.PREDETERMINER, EncodedTags.PREDETERMINER);
        encodingMap.put(Tags.PREPOSITION, EncodedTags.PREPOSITION);
        encodingMap.put(Tags.PRONOUN_PERSONAL, EncodedTags.PRONOUN_PERSONAL);
        encodingMap.put(Tags.PRONOUN_POSSESIVE, EncodedTags.PRONOUN_POSSESIVE);
        encodingMap.put(Tags.SYMBOL, EncodedTags.SYMBOL);
        encodingMap.put(Tags.QUANTIFIER, EncodedTags.QUANTIFIER);
        encodingMap.put(Tags.THERE, EncodedTags.THERE);
        encodingMap.put(Tags.TO, EncodedTags.TO);
        encodingMap.put(Tags.VERB, EncodedTags.VERB);
        encodingMap.put(Tags.VERB_ED, EncodedTags.VERB_ED);
        encodingMap.put(Tags.VERB_ING, EncodedTags.VERB_ING);
        encodingMap.put(Tags.WH_ADVERB, EncodedTags.WH_ADVERB);
        encodingMap.put(Tags.WH_DETERMINER, EncodedTags.WH_DETERMINER);
        encodingMap.put(Tags.WH_PRONOUN, EncodedTags.WH_PRONOUN);
        encodingMap.put(Tags.WH_PRONOUN_POSSESSIVE, EncodedTags.WH_PRONOUN_POSSESSIVE);

        decodingMap.put(EncodedTags.ADJECTIVE, Tags.ADJECTIVE);
        decodingMap.put(EncodedTags.ADVERB, Tags.ADVERB);
        decodingMap.put(EncodedTags.AND_OR, Tags.AND_OR);
        decodingMap.put(EncodedTags.CONJUNCTION, Tags.CONJUNCTION);
        decodingMap.put(EncodedTags.DETERMINER, Tags.DETERMINER);
        decodingMap.put(EncodedTags.DO, Tags.DO);
        decodingMap.put(EncodedTags.FOREIGN_WORD, Tags.FOREIGN_WORD);
        decodingMap.put(EncodedTags.HAVE, Tags.HAVE);
        decodingMap.put(EncodedTags.HERE, Tags.HERE);
        decodingMap.put(EncodedTags.IS_ARE, Tags.IS_ARE);
        decodingMap.put(EncodedTags.INTERJECTION, Tags.INTERJECTION);
        decodingMap.put(EncodedTags.LIST_ITEM_MARKER, Tags.LIST_ITEM_MARKER);
        decodingMap.put(EncodedTags.MODAL_VERB, Tags.MODAL_VERB);
        decodingMap.put(EncodedTags.NOT, Tags.NOT);
        decodingMap.put(EncodedTags.NOUN, Tags.NOUN);
        decodingMap.put(EncodedTags.NUMBER, Tags.NUMBER);
        decodingMap.put(EncodedTags.PARTICLE, Tags.PARTICLE);
        decodingMap.put(EncodedTags.PREDETERMINER, Tags.PREDETERMINER);
        decodingMap.put(EncodedTags.PREPOSITION, Tags.PREPOSITION);
        decodingMap.put(EncodedTags.PRONOUN_PERSONAL, Tags.PRONOUN_PERSONAL);
        decodingMap.put(EncodedTags.PRONOUN_POSSESIVE, Tags.PRONOUN_POSSESIVE);
        decodingMap.put(EncodedTags.SYMBOL, Tags.SYMBOL);
        decodingMap.put(EncodedTags.QUANTIFIER, Tags.QUANTIFIER);
        decodingMap.put(EncodedTags.THERE, Tags.THERE);
        decodingMap.put(EncodedTags.TO, Tags.TO);
        decodingMap.put(EncodedTags.VERB, Tags.VERB);
        decodingMap.put(EncodedTags.VERB_ED, Tags.VERB_ED);
        decodingMap.put(EncodedTags.VERB_ING, Tags.VERB_ING);
        decodingMap.put(EncodedTags.WH_ADVERB, Tags.WH_ADVERB);
        decodingMap.put(EncodedTags.WH_DETERMINER, Tags.WH_DETERMINER);
        decodingMap.put(EncodedTags.WH_PRONOUN, Tags.WH_PRONOUN);
        decodingMap.put(EncodedTags.WH_PRONOUN_POSSESSIVE, Tags.WH_PRONOUN_POSSESSIVE);
    }

}
