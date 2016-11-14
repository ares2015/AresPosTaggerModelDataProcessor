package mocks;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.syntax.BigramData;
import com.trainingdataprocessor.data.syntax.SubPathData;
import com.trainingdataprocessor.data.token.TokenTagData;
import com.trainingdataprocessor.database.TrainingDataDatabaseAccessor;

/**
 * Created by Oliver on 8/1/2016.
 */
public class TrainingDataDatabaseAccessorMock implements TrainingDataDatabaseAccessor {

    @Override
    public void insertTag(String tag) {

    }

    @Override
    public void insertBigramData(BigramData bigramData) {

    }

    @Override
    public void insertSubPathData(SubPathData subPathData) {

    }

    @Override
    public void insertSemanticData(SemanticExtractionData semanticExtractionData) {

    }

    @Override
    public void insertTokenTagData(TokenTagData tokenTagData) {

    }

    @Override
    public void insertEncodedPath(String encodedPath) {

    }

}