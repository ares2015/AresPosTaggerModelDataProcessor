package mocks;

import com.trainingdataprocessor.data.BigramData;
import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.database.TrainingDataAccessor;

/**
 * Created by Oliver on 8/1/2016.
 */
public class TrainingDataAccessorMock implements TrainingDataAccessor {

    @Override
    public void insertSemanticData(SemanticExtractionData semanticExtractionData) {

    }

    public void populateBigramFrequencyData(BigramData bigramData) {
        bigramData.setBigramFrequency(275);
        bigramData.setExistsInDatabase(true);
    }

    public void populateBigramTag1FrequencyData(BigramData bigramData) {
        bigramData.setTag1Frequency(450);
    }

}
