package com.trainingdataprocessor.database;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.syntax.BigramData;
import com.trainingdataprocessor.data.syntax.SubPathData;
import com.trainingdataprocessor.data.token.TokenDatabaseData;
import com.trainingdataprocessor.data.token.TokenTagData;

import java.util.Optional;

/**
 * Created by Oliver on 8/1/2016.
 */
public interface TrainingDataDatabaseAccessor {

    void insertTag(String tag);

    void insertBigramData(BigramData bigramData);

    void insertSubPathData(SubPathData subPathData);

    void insertSemanticData(SemanticExtractionData semanticExtractionData);

    void insertTokenTagData(TokenTagData tokenTagData);

    void insertEncodedPath(String encodedPath);

    Optional<TokenDatabaseData> getTokenDatabaseData(String token);

}
