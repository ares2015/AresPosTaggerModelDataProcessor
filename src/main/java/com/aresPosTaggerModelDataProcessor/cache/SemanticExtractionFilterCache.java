package com.aresPosTaggerModelDataProcessor.cache;

import com.aresPosTaggerModelDataProcessor.tags.Tags;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Oliver on 11/2/2016.
 */
public final class SemanticExtractionFilterCache {

    public static final Set<String> semanticExtractionAllowedTags = new HashSet<>();

    public static final Set<String> subjectNounPredicateExtractionAllowedTags = new HashSet<>();

    public static final Set<String> wordsToFilterCache = new HashSet<>();

    static {
        semanticExtractionAllowedTags.add(Tags.ADJECTIVE);
        semanticExtractionAllowedTags.add(Tags.ADVERB);
        semanticExtractionAllowedTags.add(Tags.DETERMINER);
        semanticExtractionAllowedTags.add(Tags.CONJUNCTION);
        semanticExtractionAllowedTags.add(Tags.IS_ARE);
        semanticExtractionAllowedTags.add(Tags.MODAL_VERB);
        semanticExtractionAllowedTags.add(Tags.NOUN);
        semanticExtractionAllowedTags.add(Tags.NUMBER);
        semanticExtractionAllowedTags.add(Tags.PREPOSITION);
        semanticExtractionAllowedTags.add(Tags.PRONOUN_POSSESIVE);
        semanticExtractionAllowedTags.add(Tags.QUANTIFIER);
        semanticExtractionAllowedTags.add(Tags.TO);
        semanticExtractionAllowedTags.add(Tags.VERB);
        semanticExtractionAllowedTags.add(Tags.VERB_ING);
        semanticExtractionAllowedTags.add(Tags.VERB_ED);

        subjectNounPredicateExtractionAllowedTags.add(Tags.ADJECTIVE);
        subjectNounPredicateExtractionAllowedTags.add(Tags.ADVERB);
        subjectNounPredicateExtractionAllowedTags.add(Tags.CONJUNCTION);
        subjectNounPredicateExtractionAllowedTags.add(Tags.NOUN);
        subjectNounPredicateExtractionAllowedTags.add(Tags.NUMBER);
        subjectNounPredicateExtractionAllowedTags.add(Tags.PREPOSITION);
        subjectNounPredicateExtractionAllowedTags.add(Tags.QUANTIFIER);
        subjectNounPredicateExtractionAllowedTags.add(Tags.TO);
        subjectNounPredicateExtractionAllowedTags.add(Tags.VERB_ING);
        subjectNounPredicateExtractionAllowedTags.add(Tags.VERB_ED);


        wordsToFilterCache.add("the");
        wordsToFilterCache.add("a");
        wordsToFilterCache.add("an");
        wordsToFilterCache.add("The");
        wordsToFilterCache.add("A");
        wordsToFilterCache.add("An");
    }

}
