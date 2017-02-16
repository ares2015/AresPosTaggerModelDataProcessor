package com.trainingdataprocessor.cache;

import com.trainingdataprocessor.tags.EncodedTags;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Oliver on 11/2/2016.
 */
public final class SemanticExtractionFilterCache {

    public static final Set<String> semanticExtractionAllowedTags = new HashSet<>();

    public static final Set<String> subjectExtractionAllowedTags = new HashSet<>();


    public static final Set<String> wordsToFilterCache = new HashSet<>();

    static {
        semanticExtractionAllowedTags.add(EncodedTags.ADJECTIVE);
        semanticExtractionAllowedTags.add(EncodedTags.ADVERB);
        semanticExtractionAllowedTags.add(EncodedTags.DETERMINER);
        semanticExtractionAllowedTags.add(EncodedTags.DO);
        semanticExtractionAllowedTags.add(EncodedTags.IS_ARE);
        semanticExtractionAllowedTags.add(EncodedTags.MODAL_VERB);
        semanticExtractionAllowedTags.add(EncodedTags.NOUN);
        semanticExtractionAllowedTags.add(EncodedTags.NOT);
        semanticExtractionAllowedTags.add(EncodedTags.NUMBER);
        semanticExtractionAllowedTags.add(EncodedTags.PREPOSITION);
        semanticExtractionAllowedTags.add(EncodedTags.QUANTIFIER);
        semanticExtractionAllowedTags.add(EncodedTags.TO);
        semanticExtractionAllowedTags.add(EncodedTags.VERB);
        semanticExtractionAllowedTags.add(EncodedTags.VERB_ING);
        semanticExtractionAllowedTags.add(EncodedTags.VERB_ED);

        subjectExtractionAllowedTags.add(EncodedTags.ADJECTIVE);
        subjectExtractionAllowedTags.add(EncodedTags.ADVERB);
        subjectExtractionAllowedTags.add(EncodedTags.NOUN);
        subjectExtractionAllowedTags.add(EncodedTags.NUMBER);
        subjectExtractionAllowedTags.add(EncodedTags.QUANTIFIER);
        subjectExtractionAllowedTags.add(EncodedTags.TO);
        subjectExtractionAllowedTags.add(EncodedTags.VERB_ING);
        subjectExtractionAllowedTags.add(EncodedTags.VERB_ED);


        wordsToFilterCache.add("the");
        wordsToFilterCache.add("a");
        wordsToFilterCache.add("an");
        wordsToFilterCache.add("The");
        wordsToFilterCache.add("A");
        wordsToFilterCache.add("An");
    }

}
