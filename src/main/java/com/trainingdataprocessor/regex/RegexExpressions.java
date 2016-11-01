package com.trainingdataprocessor.regex;

import com.trainingdataprocessor.semantics.preprocessing.phrases.VerbPhraseTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Oliver on 9/19/2016.
 */
public final class RegexExpressions {

    public static final String PREPOSITION_PHRASE = "[PT]?[#NJD$@]*[PT]?[#NJD$@]*[PT]?[#NJD$@]*";

    public static final String NOUN_PHRASE = "[#$@NJD]*[JN]";

    public static final String IS_PHRASE = "[A]*[I][A]*";

    public static final String IS_NOT_PHRASE = "[A]*[I][O][A]*";

    public static final String VERB_PHRASE = "[A]*[V][A]*";

    public static final String VERB_PHRASE_DONT = "[L][A]*[V][A]*";

    public static final String VERB_PHRASE_DO_NOT = "[L][O][A]*[V][A]*";

    public static final String MODAL_VERB_PHRASE = "[A]*[M][A]*[V][A]*";

    public static final String MODAL_VERB_NOT_PHRASE = "[A]*[M][O][A]*[V][A]*";

    public static final String AND_OR_PHRASE = "[AJN]*[JNV]<[AJN]*[JNV]";

    public static List<String> verbPhrases = new ArrayList<>();

    public static Map<String, VerbPhraseTypes> verbRegexAndTypeMap = new HashMap<String, VerbPhraseTypes>();

    static {
        verbPhrases.add(IS_PHRASE);
        verbPhrases.add(IS_NOT_PHRASE);
        verbPhrases.add(VERB_PHRASE);
        verbPhrases.add(VERB_PHRASE_DONT);
        verbPhrases.add(VERB_PHRASE_DO_NOT);
        verbPhrases.add(MODAL_VERB_PHRASE);
        verbPhrases.add(MODAL_VERB_NOT_PHRASE);


        verbRegexAndTypeMap.put(IS_NOT_PHRASE, VerbPhraseTypes.IS_NOT_PHRASE);
        verbRegexAndTypeMap.put(IS_PHRASE, VerbPhraseTypes.IS_PHRASE);
        verbRegexAndTypeMap.put(VERB_PHRASE_DO_NOT, VerbPhraseTypes.VERB_PHRASE_DO_NOT);
        verbRegexAndTypeMap.put(VERB_PHRASE_DONT, VerbPhraseTypes.VERB_PHRASE_DONT);
        verbRegexAndTypeMap.put(VERB_PHRASE, VerbPhraseTypes.VERB_PHRASE);
        verbRegexAndTypeMap.put(MODAL_VERB_NOT_PHRASE, VerbPhraseTypes.MODAL_VERB_NOT_PHRASE);
        verbRegexAndTypeMap.put(MODAL_VERB_NOT_PHRASE, VerbPhraseTypes.MODAL_VERB_NOT_PHRASE);

    }
}
