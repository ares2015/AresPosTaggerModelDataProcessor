package com.trainingdataprocessor.regex;

import com.trainingdataprocessor.tags.EncodedTags;

/**
 * Created by Oliver on 9/19/2016.
 */
public final class RegexExpressions {

    private static final String BEFORE_IS_PREPOSITION_PHRASE = "[#NJD$@]?[PT]?";

    private static final String BEFORE_IS_NOUN_PHRASE = "[NJD]*N[A]?";

    private static final String AFTER_IS_NOUN_PHRASE = "[#NJD$@A]*";

    private static final String AFTER_IS_PREPOSITION_PHRASE = "[PT]?[Y#NJD$V]*";

    public static final String IS_RELATIONSHIP_PATTERN = BEFORE_IS_PREPOSITION_PHRASE +  BEFORE_IS_NOUN_PHRASE + EncodedTags.IS_ARE + AFTER_IS_NOUN_PHRASE +
            AFTER_IS_PREPOSITION_PHRASE + AFTER_IS_PREPOSITION_PHRASE + AFTER_IS_PREPOSITION_PHRASE + AFTER_IS_PREPOSITION_PHRASE;

    public static final String VERB_RELATIONSHIP_PATTERN = BEFORE_IS_PREPOSITION_PHRASE +  BEFORE_IS_NOUN_PHRASE + "[" + EncodedTags.VERB + EncodedTags.VERB_ED + "]" + AFTER_IS_NOUN_PHRASE +
            AFTER_IS_PREPOSITION_PHRASE + AFTER_IS_PREPOSITION_PHRASE + AFTER_IS_PREPOSITION_PHRASE + AFTER_IS_PREPOSITION_PHRASE;

}
