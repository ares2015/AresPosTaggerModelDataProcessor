package com.trainingdataprocessor.regex;

/**
 * Created by Oliver on 9/19/2016.
 */
public final class RegexExpressions {

    public static final String BEFORE_IS_PREPOSITION_PHRASE = "[#NJD$@]?[PT]?";

    public static final String BEFORE_IS_NOUN_PHRASE = "[NJD]*N[A]?";

    public static final String AFTER_IS_NOUN_PHRASE = "[#NJD$@]*";

    public static final String AFTER_IS_PREPOSITION_WH_PHRASE = "[PTW]?[Y#NJD$V]*";

}
