package com.trainingdataprocessor.database;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.syntax.BigramData;
import com.trainingdataprocessor.data.syntax.StartTagEndTagPair;
import com.trainingdataprocessor.data.token.TokenTagData;

/**
 * Created by Oliver on 8/1/2016.
 */
public interface TrainingDataDatabaseAccessor {

    void insertTag(String tag);

    void insertBigramData(BigramData bigramData);

    void insertStartTagEndTagPair(StartTagEndTagPair startTagEndTagPair);

    void populateBigramFrequencyData(BigramData bigramData);

    void populateBigramTag1FrequencyData(BigramData bigramData);

    void insertSemanticData(SemanticExtractionData semanticExtractionData);

    void insertTokenTagData(TokenTagData tokenTagData);

}
