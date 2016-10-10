package com.trainingdataprocessor.semantics;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Oliver on 10/5/2016.
 */
public enum SemanticRelationConstantType {

    IS_ISNT, IS_NOT, VERB, VERB_DONT, VERB_DO_NOT, MODAL_VERB, MODAL_VERB_NOT;

    public static Map<SemanticRelationConstantType, Integer> constantTagAnalyserExtSubjectIndexMap = new HashMap<SemanticRelationConstantType, Integer>();

    public static Map<SemanticRelationConstantType, Integer> constantTagAnalyserExtPredicateIndexMap = new HashMap<SemanticRelationConstantType, Integer>();

    public static Map<SemanticRelationConstantType, Integer> relationsExtractorAtomicSubjectIndexMap = new HashMap<SemanticRelationConstantType, Integer>();

    public static Map<SemanticRelationConstantType, Integer> relationsExtractorExtSubjectIndexMap = new HashMap<SemanticRelationConstantType, Integer>();

    public static Set<SemanticRelationConstantType> auxiliaryTypes = new HashSet<SemanticRelationConstantType>();

    static {
        constantTagAnalyserExtSubjectIndexMap.put(SemanticRelationConstantType.IS_ISNT, 1);
        constantTagAnalyserExtSubjectIndexMap.put(SemanticRelationConstantType.IS_NOT, 1);
        constantTagAnalyserExtSubjectIndexMap.put(SemanticRelationConstantType.VERB, 1);
        constantTagAnalyserExtSubjectIndexMap.put(SemanticRelationConstantType.VERB_DONT, 2);
        constantTagAnalyserExtSubjectIndexMap.put(SemanticRelationConstantType.VERB_DO_NOT, 3);
        constantTagAnalyserExtSubjectIndexMap.put(SemanticRelationConstantType.MODAL_VERB, 1);
        constantTagAnalyserExtSubjectIndexMap.put(SemanticRelationConstantType.MODAL_VERB_NOT, 1);

        constantTagAnalyserExtPredicateIndexMap.put(SemanticRelationConstantType.IS_ISNT, 2);
        constantTagAnalyserExtPredicateIndexMap.put(SemanticRelationConstantType.IS_NOT, 3);
        constantTagAnalyserExtPredicateIndexMap.put(SemanticRelationConstantType.VERB, 2);
        constantTagAnalyserExtPredicateIndexMap.put(SemanticRelationConstantType.VERB_DONT, 2);
        constantTagAnalyserExtPredicateIndexMap.put(SemanticRelationConstantType.VERB_DO_NOT, 2);
        constantTagAnalyserExtPredicateIndexMap.put(SemanticRelationConstantType.MODAL_VERB, 2);
        constantTagAnalyserExtPredicateIndexMap.put(SemanticRelationConstantType.MODAL_VERB_NOT, 3);

        relationsExtractorAtomicSubjectIndexMap.put(SemanticRelationConstantType.IS_ISNT, 1);
        relationsExtractorAtomicSubjectIndexMap.put(SemanticRelationConstantType.IS_NOT, 1);
        relationsExtractorAtomicSubjectIndexMap.put(SemanticRelationConstantType.VERB, 1);
        relationsExtractorAtomicSubjectIndexMap.put(SemanticRelationConstantType.VERB_DONT, 2);
        relationsExtractorAtomicSubjectIndexMap.put(SemanticRelationConstantType.VERB_DO_NOT, 3);
        relationsExtractorAtomicSubjectIndexMap.put(SemanticRelationConstantType.MODAL_VERB, 1);
        relationsExtractorAtomicSubjectIndexMap.put(SemanticRelationConstantType.MODAL_VERB_NOT, 1);

        relationsExtractorExtSubjectIndexMap.put(SemanticRelationConstantType.IS_ISNT, 0);
        relationsExtractorExtSubjectIndexMap.put(SemanticRelationConstantType.IS_NOT, 0);
        relationsExtractorExtSubjectIndexMap.put(SemanticRelationConstantType.VERB, 0);
        relationsExtractorExtSubjectIndexMap.put(SemanticRelationConstantType.VERB_DONT, 1);
        relationsExtractorExtSubjectIndexMap.put(SemanticRelationConstantType.VERB_DO_NOT, 2);
        relationsExtractorExtSubjectIndexMap.put(SemanticRelationConstantType.MODAL_VERB, 0);
        relationsExtractorExtSubjectIndexMap.put(SemanticRelationConstantType.MODAL_VERB_NOT, 0);

        auxiliaryTypes.add(SemanticRelationConstantType.IS_NOT);
        auxiliaryTypes.add(SemanticRelationConstantType.VERB_DONT);
        auxiliaryTypes.add(SemanticRelationConstantType.VERB_DO_NOT);
        auxiliaryTypes.add(SemanticRelationConstantType.MODAL_VERB_NOT);
    }

}
