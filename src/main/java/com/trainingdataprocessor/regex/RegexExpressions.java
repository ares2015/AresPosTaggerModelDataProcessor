package com.trainingdataprocessor.regex;

import com.trainingdataprocessor.semantics.SemanticRelationConstantType;
import com.trainingdataprocessor.tags.EncodedTags;

import java.util.*;

/**
 * Created by Oliver on 9/19/2016.
 */
public final class RegexExpressions {

    private static final String BEFORE_VERB_PREPOSITION_PHRASE = "[#NJD$@]?[PT]?";

    private static final String BEFORE_VERB_NOUN_PHRASE = "[NJD]*N[A]*";

    private static final String AFTER_VERB_NOUN_PHRASE = "[#JD@AYN]*[$NJ]";

    private static final String AFTER_VERB_PREPOSITION_PHRASE = "[@]?[PT]?[Y#NJD$V]*";

    private static final String ADVERB_SEQUENCE = "[A]*";

    public static final String NOUN_IS_NOUN_RELATION_PATTERN = BEFORE_VERB_PREPOSITION_PHRASE + BEFORE_VERB_NOUN_PHRASE + EncodedTags.IS_ARE + AFTER_VERB_NOUN_PHRASE +
            AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE;

    public static final String NOUN_IS_NOT_NOUN_RELATION_PATTERN = BEFORE_VERB_PREPOSITION_PHRASE + BEFORE_VERB_NOUN_PHRASE + EncodedTags.IS_ARE + EncodedTags.NOT + AFTER_VERB_NOUN_PHRASE +
            AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE;

    public static final String NOUN_VERB_NOUN_RELATION_PATTERN = BEFORE_VERB_PREPOSITION_PHRASE + BEFORE_VERB_NOUN_PHRASE + ADVERB_SEQUENCE + "[" + EncodedTags.VERB + EncodedTags.VERB_ED + "]" + AFTER_VERB_NOUN_PHRASE
            +
            AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE;

    public static final String NOUN_VERB_DONT_NOUN_RELATION_PATTERN = BEFORE_VERB_PREPOSITION_PHRASE + BEFORE_VERB_NOUN_PHRASE + EncodedTags.DO + ADVERB_SEQUENCE + "[" + EncodedTags.VERB + EncodedTags.VERB_ED + "]" +
            ADVERB_SEQUENCE + AFTER_VERB_NOUN_PHRASE + ADVERB_SEQUENCE +
            AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE;

    public static final String NOUN_VERB_DO_NOT_NOUN_RELATION_PATTERN = BEFORE_VERB_PREPOSITION_PHRASE + BEFORE_VERB_NOUN_PHRASE + EncodedTags.DO + EncodedTags.NOT + ADVERB_SEQUENCE +
            "[" + EncodedTags.VERB + EncodedTags.VERB_ED + "]" +  ADVERB_SEQUENCE  + AFTER_VERB_NOUN_PHRASE +  ADVERB_SEQUENCE +
    AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE;

    public static final String NOUN_MODAL_VERB_NOUN_RELATION_PATTERN = BEFORE_VERB_PREPOSITION_PHRASE + BEFORE_VERB_NOUN_PHRASE +
            EncodedTags.MODAL_VERB + ADVERB_SEQUENCE + EncodedTags.VERB + ADVERB_SEQUENCE +
            AFTER_VERB_NOUN_PHRASE + ADVERB_SEQUENCE +
            AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE;

    public static final String NOUN_MODAL_VERB_NOT_NOUN_RELATION_PATTERN = BEFORE_VERB_PREPOSITION_PHRASE + BEFORE_VERB_NOUN_PHRASE +
            EncodedTags.MODAL_VERB + EncodedTags.NOT + ADVERB_SEQUENCE + EncodedTags.VERB + ADVERB_SEQUENCE +
            AFTER_VERB_NOUN_PHRASE + ADVERB_SEQUENCE +
            AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE;

    public static final String NOUN_VERB_RELATION_PATTERN = BEFORE_VERB_PREPOSITION_PHRASE + BEFORE_VERB_NOUN_PHRASE + ADVERB_SEQUENCE + "[" + EncodedTags.VERB + EncodedTags.VERB_ED + "]"
            +  AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE;

    public static final String NOUN_VERB_DONT_RELATION_PATTERN = BEFORE_VERB_PREPOSITION_PHRASE + BEFORE_VERB_NOUN_PHRASE + EncodedTags.DO + ADVERB_SEQUENCE + "[" + EncodedTags.VERB + EncodedTags.VERB_ED + "]" +
            ADVERB_SEQUENCE  + ADVERB_SEQUENCE +
            AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE;

    public static final String NOUN_VERB_DO_NOT_RELATION_PATTERN = BEFORE_VERB_PREPOSITION_PHRASE + BEFORE_VERB_NOUN_PHRASE + EncodedTags.DO + EncodedTags.NOT + ADVERB_SEQUENCE +
            "[" + EncodedTags.VERB + EncodedTags.VERB_ED + "]" +  ADVERB_SEQUENCE +  ADVERB_SEQUENCE +
            AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE;

    public static final String NOUN_MODAL_VERB_RELATION_PATTERN = BEFORE_VERB_PREPOSITION_PHRASE + BEFORE_VERB_NOUN_PHRASE + EncodedTags.MODAL_VERB + ADVERB_SEQUENCE +  EncodedTags.VERB  +
            ADVERB_SEQUENCE  + ADVERB_SEQUENCE +
            AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE;

    public static final String NOUN_MODAL_VERB_NOT_RELATION_PATTERN = BEFORE_VERB_PREPOSITION_PHRASE + BEFORE_VERB_NOUN_PHRASE +
            EncodedTags.MODAL_VERB + EncodedTags.NOT + ADVERB_SEQUENCE + EncodedTags.VERB + ADVERB_SEQUENCE + ADVERB_SEQUENCE +
            AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE + AFTER_VERB_PREPOSITION_PHRASE;

    public static final List<String> regexPatterns = new ArrayList<>();

    public static final Map<String, String> patternsConstantTagsMap = new HashMap<String, String>();

    public static final Map<String, SemanticRelationConstantType> patternsSemanticRelationConstantTypeMap = new HashMap<String, SemanticRelationConstantType>();


    static{
        regexPatterns.add(NOUN_IS_NOUN_RELATION_PATTERN);
        regexPatterns.add(NOUN_IS_NOT_NOUN_RELATION_PATTERN);
        regexPatterns.add(NOUN_VERB_NOUN_RELATION_PATTERN);
        regexPatterns.add(NOUN_VERB_DONT_NOUN_RELATION_PATTERN);
        regexPatterns.add(NOUN_VERB_DO_NOT_NOUN_RELATION_PATTERN);
        regexPatterns.add(NOUN_MODAL_VERB_NOUN_RELATION_PATTERN);
        regexPatterns.add(NOUN_MODAL_VERB_NOT_NOUN_RELATION_PATTERN);
        regexPatterns.add(NOUN_VERB_RELATION_PATTERN);
        regexPatterns.add(NOUN_VERB_DONT_RELATION_PATTERN);
        regexPatterns.add(NOUN_VERB_DO_NOT_RELATION_PATTERN);
        regexPatterns.add(NOUN_MODAL_VERB_RELATION_PATTERN);
        regexPatterns.add(NOUN_MODAL_VERB_NOT_RELATION_PATTERN);

        patternsConstantTagsMap.put(NOUN_IS_NOUN_RELATION_PATTERN, EncodedTags.IS_ARE);
        patternsConstantTagsMap.put(NOUN_IS_NOT_NOUN_RELATION_PATTERN, EncodedTags.IS_ARE);
        patternsConstantTagsMap.put(NOUN_VERB_NOUN_RELATION_PATTERN, EncodedTags.VERB);
        patternsConstantTagsMap.put(NOUN_VERB_DONT_NOUN_RELATION_PATTERN, EncodedTags.VERB);
        patternsConstantTagsMap.put(NOUN_VERB_DO_NOT_NOUN_RELATION_PATTERN, EncodedTags.VERB);
        patternsConstantTagsMap.put(NOUN_MODAL_VERB_NOUN_RELATION_PATTERN, EncodedTags.MODAL_VERB);
        patternsConstantTagsMap.put(NOUN_MODAL_VERB_NOT_NOUN_RELATION_PATTERN, EncodedTags.MODAL_VERB);
        patternsConstantTagsMap.put(NOUN_VERB_RELATION_PATTERN, EncodedTags.VERB);
        patternsConstantTagsMap.put(NOUN_VERB_DONT_RELATION_PATTERN, EncodedTags.VERB);
        patternsConstantTagsMap.put(NOUN_VERB_DO_NOT_RELATION_PATTERN, EncodedTags.VERB);
        patternsConstantTagsMap.put(NOUN_MODAL_VERB_RELATION_PATTERN, EncodedTags.MODAL_VERB);
        patternsConstantTagsMap.put(NOUN_MODAL_VERB_NOT_RELATION_PATTERN, EncodedTags.MODAL_VERB);

        patternsSemanticRelationConstantTypeMap.put(NOUN_IS_NOUN_RELATION_PATTERN, SemanticRelationConstantType.IS_ISNT_3_LEVEL);
        patternsSemanticRelationConstantTypeMap.put(NOUN_IS_NOT_NOUN_RELATION_PATTERN, SemanticRelationConstantType.IS_NOT_3_LEVEL);
        patternsSemanticRelationConstantTypeMap.put(NOUN_VERB_NOUN_RELATION_PATTERN, SemanticRelationConstantType.VERB_3_LEVEL);
        patternsSemanticRelationConstantTypeMap.put(NOUN_VERB_DONT_NOUN_RELATION_PATTERN, SemanticRelationConstantType.VERB_DONT_3_LEVEL);
        patternsSemanticRelationConstantTypeMap.put(NOUN_VERB_DO_NOT_NOUN_RELATION_PATTERN, SemanticRelationConstantType.VERB_DO_NOT_3_LEVEL);
        patternsSemanticRelationConstantTypeMap.put(NOUN_MODAL_VERB_NOUN_RELATION_PATTERN, SemanticRelationConstantType.MODAL_VERB_3_LEVEL);
        patternsSemanticRelationConstantTypeMap.put(NOUN_MODAL_VERB_NOT_NOUN_RELATION_PATTERN, SemanticRelationConstantType.MODAL_VERB_NOT_3_LEVEL);
        patternsSemanticRelationConstantTypeMap.put(NOUN_VERB_RELATION_PATTERN, SemanticRelationConstantType.VERB_2_LEVEL);
        patternsSemanticRelationConstantTypeMap.put(NOUN_VERB_DONT_RELATION_PATTERN, SemanticRelationConstantType.VERB_DONT_2_LEVEL);
        patternsSemanticRelationConstantTypeMap.put(NOUN_VERB_DO_NOT_RELATION_PATTERN, SemanticRelationConstantType.VERB_DO_NOT_2_LEVEL);
        patternsSemanticRelationConstantTypeMap.put(NOUN_MODAL_VERB_RELATION_PATTERN, SemanticRelationConstantType.MODAL_VERB_2_LEVEL);
        patternsSemanticRelationConstantTypeMap.put(NOUN_MODAL_VERB_NOT_RELATION_PATTERN, SemanticRelationConstantType.MODAL_VERB_3_LEVEL);

    }
}
