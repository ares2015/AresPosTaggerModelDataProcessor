package com.trainingdataprocessor.semantics;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Oliver on 10/5/2016.
 */
public enum SemanticRelationConstantType {

    IS_ISNT_3_LEVEL, IS_NOT_3_LEVEL, VERB_3_LEVEL, VERB_DONT_3_LEVEL, VERB_DO_NOT_3_LEVEL, MODAL_VERB_3_LEVEL, MODAL_VERB_NOT_3_LEVEL,
    VERB_2_LEVEL, VERB_DONT_2_LEVEL, VERB_DO_NOT_2_LEVEL, MODAL_VERB_2_LEVEL, MODAL_VERB_NOT_2_LEVEL;

    public static Map<SemanticRelationConstantType, Integer> constantTagAnalyserExtSubjectIndexMap = new HashMap<SemanticRelationConstantType, Integer>();

    public static Map<SemanticRelationConstantType, Integer> constantTagAnalyserExtPredicateIndexMap = new HashMap<SemanticRelationConstantType, Integer>();

    public static Map<SemanticRelationConstantType, Integer> relationsExtractorAtomicSubjectIndexMap = new HashMap<SemanticRelationConstantType, Integer>();

    public static Map<SemanticRelationConstantType, Integer> relationsExtractorExtSubjectIndexMap = new HashMap<SemanticRelationConstantType, Integer>();

    public static Set<SemanticRelationConstantType> auxiliaryTypes = new HashSet<SemanticRelationConstantType>();

    public static Set<SemanticRelationConstantType> negativeTypes = new HashSet<SemanticRelationConstantType>();

    public static Set<SemanticRelationConstantType> negativeTypesForPresentTenseTest = new HashSet<SemanticRelationConstantType>();

    public static Set<SemanticRelationConstantType> nounVerbRelationTypes = new HashSet<SemanticRelationConstantType>();

    public static Set<SemanticRelationConstantType> modalVerbs = new HashSet<SemanticRelationConstantType>();



    static {
        constantTagAnalyserExtSubjectIndexMap.put(SemanticRelationConstantType.IS_ISNT_3_LEVEL, 1);
        constantTagAnalyserExtSubjectIndexMap.put(SemanticRelationConstantType.IS_NOT_3_LEVEL, 1);
        constantTagAnalyserExtSubjectIndexMap.put(SemanticRelationConstantType.VERB_3_LEVEL, 1);
        constantTagAnalyserExtSubjectIndexMap.put(SemanticRelationConstantType.VERB_DONT_3_LEVEL, 2);
        constantTagAnalyserExtSubjectIndexMap.put(SemanticRelationConstantType.VERB_DO_NOT_3_LEVEL, 3);
        constantTagAnalyserExtSubjectIndexMap.put(SemanticRelationConstantType.MODAL_VERB_3_LEVEL, 1);
        constantTagAnalyserExtSubjectIndexMap.put(SemanticRelationConstantType.MODAL_VERB_NOT_3_LEVEL, 1);
        constantTagAnalyserExtSubjectIndexMap.put(SemanticRelationConstantType.VERB_2_LEVEL, 1);
        constantTagAnalyserExtSubjectIndexMap.put(SemanticRelationConstantType.VERB_DONT_2_LEVEL, 2);
        constantTagAnalyserExtSubjectIndexMap.put(SemanticRelationConstantType.VERB_DO_NOT_2_LEVEL, 3);
        constantTagAnalyserExtSubjectIndexMap.put(SemanticRelationConstantType.MODAL_VERB_2_LEVEL, 1);
        constantTagAnalyserExtSubjectIndexMap.put(SemanticRelationConstantType.MODAL_VERB_NOT_2_LEVEL, 1);

        constantTagAnalyserExtPredicateIndexMap.put(SemanticRelationConstantType.IS_ISNT_3_LEVEL, 2);
        constantTagAnalyserExtPredicateIndexMap.put(SemanticRelationConstantType.IS_NOT_3_LEVEL, 3);
        constantTagAnalyserExtPredicateIndexMap.put(SemanticRelationConstantType.VERB_3_LEVEL, 2);
        constantTagAnalyserExtPredicateIndexMap.put(SemanticRelationConstantType.VERB_DONT_3_LEVEL, 2);
        constantTagAnalyserExtPredicateIndexMap.put(SemanticRelationConstantType.VERB_DO_NOT_3_LEVEL, 2);
        constantTagAnalyserExtPredicateIndexMap.put(SemanticRelationConstantType.MODAL_VERB_3_LEVEL, 2);
        constantTagAnalyserExtPredicateIndexMap.put(SemanticRelationConstantType.MODAL_VERB_NOT_3_LEVEL, 3);

        relationsExtractorAtomicSubjectIndexMap.put(SemanticRelationConstantType.IS_ISNT_3_LEVEL, 1);
        relationsExtractorAtomicSubjectIndexMap.put(SemanticRelationConstantType.IS_NOT_3_LEVEL, 1);
        relationsExtractorAtomicSubjectIndexMap.put(SemanticRelationConstantType.VERB_3_LEVEL, 1);
        relationsExtractorAtomicSubjectIndexMap.put(SemanticRelationConstantType.VERB_DONT_3_LEVEL, 2);
        relationsExtractorAtomicSubjectIndexMap.put(SemanticRelationConstantType.VERB_DO_NOT_3_LEVEL, 3);
        relationsExtractorAtomicSubjectIndexMap.put(SemanticRelationConstantType.MODAL_VERB_3_LEVEL, 1);
        relationsExtractorAtomicSubjectIndexMap.put(SemanticRelationConstantType.MODAL_VERB_NOT_3_LEVEL, 1);
        relationsExtractorAtomicSubjectIndexMap.put(SemanticRelationConstantType.VERB_2_LEVEL, 1);
        relationsExtractorAtomicSubjectIndexMap.put(SemanticRelationConstantType.VERB_DONT_2_LEVEL, 2);
        relationsExtractorAtomicSubjectIndexMap.put(SemanticRelationConstantType.VERB_DO_NOT_2_LEVEL, 3);
        relationsExtractorAtomicSubjectIndexMap.put(SemanticRelationConstantType.MODAL_VERB_2_LEVEL, 1);
        relationsExtractorAtomicSubjectIndexMap.put(SemanticRelationConstantType.MODAL_VERB_NOT_2_LEVEL, 1);

        relationsExtractorExtSubjectIndexMap.put(SemanticRelationConstantType.IS_ISNT_3_LEVEL, 0);
        relationsExtractorExtSubjectIndexMap.put(SemanticRelationConstantType.IS_NOT_3_LEVEL, 0);
        relationsExtractorExtSubjectIndexMap.put(SemanticRelationConstantType.VERB_3_LEVEL, 0);
        relationsExtractorExtSubjectIndexMap.put(SemanticRelationConstantType.VERB_DONT_3_LEVEL, 1);
        relationsExtractorExtSubjectIndexMap.put(SemanticRelationConstantType.VERB_DO_NOT_3_LEVEL, 2);
        relationsExtractorExtSubjectIndexMap.put(SemanticRelationConstantType.MODAL_VERB_3_LEVEL, 0);
        relationsExtractorExtSubjectIndexMap.put(SemanticRelationConstantType.MODAL_VERB_NOT_3_LEVEL, 0);
        relationsExtractorExtSubjectIndexMap.put(SemanticRelationConstantType.VERB_2_LEVEL, 0);
        relationsExtractorExtSubjectIndexMap.put(SemanticRelationConstantType.VERB_DONT_2_LEVEL, 1);
        relationsExtractorExtSubjectIndexMap.put(SemanticRelationConstantType.VERB_DO_NOT_2_LEVEL, 2);
        relationsExtractorExtSubjectIndexMap.put(SemanticRelationConstantType.MODAL_VERB_2_LEVEL, 0);
        relationsExtractorExtSubjectIndexMap.put(SemanticRelationConstantType.MODAL_VERB_NOT_2_LEVEL, 0);

        auxiliaryTypes.add(SemanticRelationConstantType.IS_NOT_3_LEVEL);
        auxiliaryTypes.add(SemanticRelationConstantType.VERB_DONT_3_LEVEL);
        auxiliaryTypes.add(SemanticRelationConstantType.VERB_DO_NOT_3_LEVEL);
        auxiliaryTypes.add(SemanticRelationConstantType.MODAL_VERB_NOT_3_LEVEL);

        nounVerbRelationTypes.add(SemanticRelationConstantType.VERB_2_LEVEL);
        nounVerbRelationTypes.add(SemanticRelationConstantType.VERB_DONT_2_LEVEL);
        nounVerbRelationTypes.add(SemanticRelationConstantType.VERB_DO_NOT_2_LEVEL);
        nounVerbRelationTypes.add(SemanticRelationConstantType.MODAL_VERB_2_LEVEL);
        nounVerbRelationTypes.add(SemanticRelationConstantType.MODAL_VERB_NOT_2_LEVEL);

        negativeTypes.add(SemanticRelationConstantType.IS_NOT_3_LEVEL);
        negativeTypes.add(SemanticRelationConstantType.VERB_DO_NOT_3_LEVEL);
        negativeTypes.add(SemanticRelationConstantType.VERB_DONT_3_LEVEL);
        negativeTypes.add(SemanticRelationConstantType.MODAL_VERB_NOT_3_LEVEL);
        negativeTypes.add(SemanticRelationConstantType.VERB_DO_NOT_2_LEVEL);
        negativeTypes.add(SemanticRelationConstantType.VERB_DONT_2_LEVEL);
        negativeTypes.add(SemanticRelationConstantType.MODAL_VERB_NOT_2_LEVEL);

        negativeTypesForPresentTenseTest.add(SemanticRelationConstantType.VERB_DO_NOT_3_LEVEL);
        negativeTypesForPresentTenseTest.add(SemanticRelationConstantType.VERB_DONT_3_LEVEL);
        negativeTypesForPresentTenseTest.add(SemanticRelationConstantType.VERB_DO_NOT_2_LEVEL);
        negativeTypesForPresentTenseTest.add(SemanticRelationConstantType.VERB_DONT_2_LEVEL);

        modalVerbs.add(SemanticRelationConstantType.MODAL_VERB_2_LEVEL);
        modalVerbs.add(SemanticRelationConstantType.MODAL_VERB_3_LEVEL);
        modalVerbs.add(SemanticRelationConstantType.MODAL_VERB_NOT_2_LEVEL);
        modalVerbs.add(SemanticRelationConstantType.MODAL_VERB_NOT_3_LEVEL);

    }

}
