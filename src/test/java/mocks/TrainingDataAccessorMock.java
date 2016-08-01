package mocks;

import com.trainingdataprocessor.data.BigramData;
import com.trainingdataprocessor.database.TrainingDataAccessor;

/**
 * Created by Oliver on 8/1/2016.
 */
public class TrainingDataAccessorMock implements TrainingDataAccessor {

    public void populateBigramFrequencyData(BigramData bigramData) {
        bigramData.setBigramFrequency(275);
    }

    public void populateBigramTag1FrequencyData(BigramData bigramData) {
        bigramData.setTag1Frequency(450);
    }

}
