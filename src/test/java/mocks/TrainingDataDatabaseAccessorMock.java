package mocks;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.syntax.BigramData;
import com.trainingdataprocessor.data.syntax.StartTagEndTagPair;
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
    public void insertStartTagEndTagPair(StartTagEndTagPair startTagEndTagPair) {

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

    @Override
    public void insertEncodedSubPath(String encodedSubPath) {

    }

    @Override
    public void insertPath(String path) {

    }

    @Override
    public void insertSubPath(String subPath) {

    }

    @Override
    public void insertSentence(String sentence) {

    }

}
