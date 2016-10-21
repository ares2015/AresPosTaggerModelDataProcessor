package com.trainingdataprocessor.regex;

import com.trainingdataprocessor.semantics.preprocessing.phrases.verb.VerbPhraseTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Oliver on 9/19/2016.
 */
public final class RegexExpressions {

    public static final String PREPOSITION_PHRASE = "[PT]?[#NJD$@]*[PT]?[#NJD$@]*[PT]?[#NJD$@]*";

    public static final String NOUN_PHRASE = "[#$@NJD]*N";

    public static final String IS_PHRASE = "[A]*[I][A]*";

    public static final String IS_NOT_PHRASE = "[A]*[I][O][A]*";

    public static final String VERB_PHRASE = "[A]*[V$][A]*";

    public static final String VERB_PHRASE_DONT = "[L][A]*[V$][A]*";

    public static final String VERB_PHRASE_DO_NOT = "[L][O][A]*[V$][A]*";

    public static final String MODAL_VERB_PHRASE = "[A]*[M][A]*[V][A]*";

    public static final String MODAL_VERB_NOT_PHRASE = "[A]*[M][O][A]*[V][A]*";

    public static final String AND_OR_PHRASE = "[AJN]*[JNV]<[AJN]*[JNV]";

    public static List<String> verbPhrases = new ArrayList<>();

    public static Map<String, VerbPhraseTypes> verbRegexAndTypeMap = new HashMap<String, VerbPhraseTypes>();

    static {
        verbPhrases.add(IS_NOT_PHRASE);
        verbPhrases.add(IS_PHRASE);
        verbPhrases.add(VERB_PHRASE_DO_NOT);
        verbPhrases.add(VERB_PHRASE_DONT);
        verbPhrases.add(VERB_PHRASE);
        verbPhrases.add(MODAL_VERB_NOT_PHRASE);
        verbPhrases.add(MODAL_VERB_PHRASE);

        verbRegexAndTypeMap.put(IS_NOT_PHRASE, VerbPhraseTypes.IS_NOT_PHRASE);
        verbRegexAndTypeMap.put(IS_PHRASE, VerbPhraseTypes.IS_PHRASE);
        verbRegexAndTypeMap.put(VERB_PHRASE_DO_NOT, VerbPhraseTypes.VERB_PHRASE_DO_NOT);
        verbRegexAndTypeMap.put(VERB_PHRASE_DONT, VerbPhraseTypes.VERB_PHRASE_DONT);
        verbRegexAndTypeMap.put(VERB_PHRASE, VerbPhraseTypes.VERB_PHRASE);
        verbRegexAndTypeMap.put(MODAL_VERB_NOT_PHRASE, VerbPhraseTypes.MODAL_VERB_NOT_PHRASE);
        verbRegexAndTypeMap.put(MODAL_VERB_NOT_PHRASE, VerbPhraseTypes.MODAL_VERB_NOT_PHRASE);

    }


//    public static final String AFTER_VERB_NOUN_PHRASE = "[#JD@AYN]*[$NJ]";

//    public static final String ADVERB_SEQUENCE = "[A]*";

//    public static final String NOUN_IS_NOUN_RELATION_PATTERN = PREPOSITION_PHRASE + NOUN_PHRASE + EncodedTags.IS_ARE + AFTER_VERB_NOUN_PHRASE +
//            PREPOSITION_PHRASE;
//
//    public static final String NOUN_IS_NOT_NOUN_RELATION_PATTERN = PREPOSITION_PHRASE + NOUN_PHRASE + EncodedTags.IS_ARE + EncodedTags.NOT + AFTER_VERB_NOUN_PHRASE +
//            PREPOSITION_PHRASE;
//
//    public static final String NOUN_VERB_NOUN_RELATION_PATTERN = PREPOSITION_PHRASE + NOUN_PHRASE + ADVERB_SEQUENCE + "[" + EncodedTags.VERB + EncodedTags.VERB_ED + "]" + AFTER_VERB_NOUN_PHRASE
//            +
//            PREPOSITION_PHRASE;
//
//    public static final String NOUN_VERB_DONT_NOUN_RELATION_PATTERN = PREPOSITION_PHRASE + NOUN_PHRASE + EncodedTags.DO + ADVERB_SEQUENCE + "[" + EncodedTags.VERB + EncodedTags.VERB_ED + "]" +
//            ADVERB_SEQUENCE + AFTER_VERB_NOUN_PHRASE + ADVERB_SEQUENCE;
//
//    public static final String NOUN_VERB_DO_NOT_NOUN_RELATION_PATTERN = PREPOSITION_PHRASE + NOUN_PHRASE + EncodedTags.DO + EncodedTags.NOT + ADVERB_SEQUENCE +
//            "[" + EncodedTags.VERB + EncodedTags.VERB_ED + "]" +  ADVERB_SEQUENCE  + AFTER_VERB_NOUN_PHRASE +  ADVERB_SEQUENCE + PREPOSITION_PHRASE;
//
//    public static final String NOUN_MODAL_VERB_NOUN_RELATION_PATTERN = PREPOSITION_PHRASE + NOUN_PHRASE +
//            EncodedTags.MODAL_VERB + ADVERB_SEQUENCE + EncodedTags.VERB + ADVERB_SEQUENCE +
//            AFTER_VERB_NOUN_PHRASE + ADVERB_SEQUENCE + PREPOSITION_PHRASE;
//
//    public static final String NOUN_MODAL_VERB_NOT_NOUN_RELATION_PATTERN = PREPOSITION_PHRASE + NOUN_PHRASE +
//            EncodedTags.MODAL_VERB + EncodedTags.NOT + ADVERB_SEQUENCE + EncodedTags.VERB + ADVERB_SEQUENCE +
//            AFTER_VERB_NOUN_PHRASE + ADVERB_SEQUENCE + PREPOSITION_PHRASE;
//
//    public static final String NOUN_VERB_RELATION_PATTERN = PREPOSITION_PHRASE + NOUN_PHRASE + ADVERB_SEQUENCE + "[" + EncodedTags.VERB + EncodedTags.VERB_ED + "]"
//            +  PREPOSITION_PHRASE;
//
//    public static final String NOUN_VERB_DONT_RELATION_PATTERN = PREPOSITION_PHRASE + NOUN_PHRASE + EncodedTags.DO + ADVERB_SEQUENCE + "[" + EncodedTags.VERB + EncodedTags.VERB_ED + "]" +
//            ADVERB_SEQUENCE  + ADVERB_SEQUENCE +
//            PREPOSITION_PHRASE;
//
//    public static final String NOUN_VERB_DO_NOT_RELATION_PATTERN = PREPOSITION_PHRASE + NOUN_PHRASE + EncodedTags.DO + EncodedTags.NOT + ADVERB_SEQUENCE +
//            "[" + EncodedTags.VERB + EncodedTags.VERB_ED + "]" +  ADVERB_SEQUENCE +  ADVERB_SEQUENCE +
//            PREPOSITION_PHRASE;
//
//    public static final String NOUN_MODAL_VERB_RELATION_PATTERN = PREPOSITION_PHRASE + NOUN_PHRASE + EncodedTags.MODAL_VERB + ADVERB_SEQUENCE +  EncodedTags.VERB  +
//            ADVERB_SEQUENCE  + ADVERB_SEQUENCE +
//            PREPOSITION_PHRASE;
//
//    public static final String NOUN_MODAL_VERB_NOT_RELATION_PATTERN = PREPOSITION_PHRASE + NOUN_PHRASE +
//            EncodedTags.MODAL_VERB + EncodedTags.NOT + ADVERB_SEQUENCE + EncodedTags.VERB + ADVERB_SEQUENCE + ADVERB_SEQUENCE +
//            PREPOSITION_PHRASE;
//
//    public static final List<String> regexPatterns = new ArrayList<>();
//
//    public static final Map<String, String> patternsConstantTagsMap = new HashMap<String, String>();
//
//    public static final Map<String, SemanticRelationConstantType> patternsSemanticRelationConstantTypeMap = new HashMap<String, SemanticRelationConstantType>();
//
//
//    static{
//        regexPatterns.add(NOUN_IS_NOUN_RELATION_PATTERN);
//        regexPatterns.add(NOUN_IS_NOT_NOUN_RELATION_PATTERN);
//        regexPatterns.add(NOUN_VERB_NOUN_RELATION_PATTERN);
//        regexPatterns.add(NOUN_VERB_DONT_NOUN_RELATION_PATTERN);
//        regexPatterns.add(NOUN_VERB_DO_NOT_NOUN_RELATION_PATTERN);
//        regexPatterns.add(NOUN_MODAL_VERB_NOUN_RELATION_PATTERN);
//        regexPatterns.add(NOUN_MODAL_VERB_NOT_NOUN_RELATION_PATTERN);
//        regexPatterns.add(NOUN_VERB_RELATION_PATTERN);
//        regexPatterns.add(NOUN_VERB_DONT_RELATION_PATTERN);
//        regexPatterns.add(NOUN_VERB_DO_NOT_RELATION_PATTERN);
//        regexPatterns.add(NOUN_MODAL_VERB_RELATION_PATTERN);
//        regexPatterns.add(NOUN_MODAL_VERB_NOT_RELATION_PATTERN);
//
//        patternsConstantTagsMap.put(NOUN_IS_NOUN_RELATION_PATTERN, EncodedTags.IS_ARE);
//        patternsConstantTagsMap.put(NOUN_IS_NOT_NOUN_RELATION_PATTERN, EncodedTags.IS_ARE);
//        patternsConstantTagsMap.put(NOUN_VERB_NOUN_RELATION_PATTERN, EncodedTags.VERB);
//        patternsConstantTagsMap.put(NOUN_VERB_DONT_NOUN_RELATION_PATTERN, EncodedTags.VERB);
//        patternsConstantTagsMap.put(NOUN_VERB_DO_NOT_NOUN_RELATION_PATTERN, EncodedTags.VERB);
//        patternsConstantTagsMap.put(NOUN_MODAL_VERB_NOUN_RELATION_PATTERN, EncodedTags.MODAL_VERB);
//        patternsConstantTagsMap.put(NOUN_MODAL_VERB_NOT_NOUN_RELATION_PATTERN, EncodedTags.MODAL_VERB);
//        patternsConstantTagsMap.put(NOUN_VERB_RELATION_PATTERN, EncodedTags.VERB);
//        patternsConstantTagsMap.put(NOUN_VERB_DONT_RELATION_PATTERN, EncodedTags.VERB);
//        patternsConstantTagsMap.put(NOUN_VERB_DO_NOT_RELATION_PATTERN, EncodedTags.VERB);
//        patternsConstantTagsMap.put(NOUN_MODAL_VERB_RELATION_PATTERN, EncodedTags.MODAL_VERB);
//        patternsConstantTagsMap.put(NOUN_MODAL_VERB_NOT_RELATION_PATTERN, EncodedTags.MODAL_VERB);
//
//        patternsSemanticRelationConstantTypeMap.put(NOUN_IS_NOUN_RELATION_PATTERN, SemanticRelationConstantType.IS_ISNT_3_LEVEL);
//        patternsSemanticRelationConstantTypeMap.put(NOUN_IS_NOT_NOUN_RELATION_PATTERN, SemanticRelationConstantType.IS_NOT_3_LEVEL);
//        patternsSemanticRelationConstantTypeMap.put(NOUN_VERB_NOUN_RELATION_PATTERN, SemanticRelationConstantType.VERB_3_LEVEL);
//        patternsSemanticRelationConstantTypeMap.put(NOUN_VERB_DONT_NOUN_RELATION_PATTERN, SemanticRelationConstantType.VERB_DONT_3_LEVEL);
//        patternsSemanticRelationConstantTypeMap.put(NOUN_VERB_DO_NOT_NOUN_RELATION_PATTERN, SemanticRelationConstantType.VERB_DO_NOT_3_LEVEL);
//        patternsSemanticRelationConstantTypeMap.put(NOUN_MODAL_VERB_NOUN_RELATION_PATTERN, SemanticRelationConstantType.MODAL_VERB_3_LEVEL);
//        patternsSemanticRelationConstantTypeMap.put(NOUN_MODAL_VERB_NOT_NOUN_RELATION_PATTERN, SemanticRelationConstantType.MODAL_VERB_NOT_3_LEVEL);
//        patternsSemanticRelationConstantTypeMap.put(NOUN_VERB_RELATION_PATTERN, SemanticRelationConstantType.VERB_2_LEVEL);
//        patternsSemanticRelationConstantTypeMap.put(NOUN_VERB_DONT_RELATION_PATTERN, SemanticRelationConstantType.VERB_DONT_2_LEVEL);
//        patternsSemanticRelationConstantTypeMap.put(NOUN_VERB_DO_NOT_RELATION_PATTERN, SemanticRelationConstantType.VERB_DO_NOT_2_LEVEL);
//        patternsSemanticRelationConstantTypeMap.put(NOUN_MODAL_VERB_RELATION_PATTERN, SemanticRelationConstantType.MODAL_VERB_2_LEVEL);
//        patternsSemanticRelationConstantTypeMap.put(NOUN_MODAL_VERB_NOT_RELATION_PATTERN, SemanticRelationConstantType.MODAL_VERB_NOT_2_LEVEL);
//
//    }
}
