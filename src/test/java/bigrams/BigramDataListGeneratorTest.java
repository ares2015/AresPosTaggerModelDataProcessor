package bigrams;

import com.trainingdataprocessor.bigrams.BigramDataListGenerator;
import com.trainingdataprocessor.bigrams.BigramDataListGeneratorImpl;
import com.trainingdataprocessor.bigrams.BigramProbabilityCalculator;
import com.trainingdataprocessor.bigrams.BigramProbabilityCalculatorImpl;
import com.trainingdataprocessor.cache.ConstantTagsCache;
import com.trainingdataprocessor.data.BigramData;
import com.trainingdataprocessor.database.TrainingDataAccessor;
import com.trainingdataprocessor.tags.Tags;
import mocks.TrainingDataAccessorMock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BigramDataListGeneratorTest {

    ConstantTagsCache constantTagsCache = new ConstantTagsCache();
    TrainingDataAccessor trainingDataAccessor = new TrainingDataAccessorMock();
    BigramProbabilityCalculator bigramProbabilityCalculator = new BigramProbabilityCalculatorImpl();
    BigramDataListGenerator bigramDataListGenerator = new BigramDataListGeneratorImpl(constantTagsCache, trainingDataAccessor, bigramProbabilityCalculator);

    @Test
    public void testGenerate(){
        List<String> tags = new ArrayList<String>();
        tags.add(Tags.DETERMINER);
        tags.add(Tags.ADJECTIVE);
        tags.add(Tags.NOUN);
        tags.add(Tags.VERB);
        List<BigramData> bigramDataList = bigramDataListGenerator.generate(tags);

        assertEquals(3, bigramDataList.size());

        assertEquals(Tags.DETERMINER, bigramDataList.get(0).getTag1());
        assertEquals(Tags.ADJECTIVE, bigramDataList.get(0).getTag2());
        assertEquals(275, bigramDataList.get(0).getBigramFrequency());
        assertEquals(Double.valueOf(61.111111111111114), Double.valueOf(bigramDataList.get(0).getBigramProbability()));
        assertEquals(450, bigramDataList.get(0).getTag1Frequency());

        assertEquals(Tags.ADJECTIVE, bigramDataList.get(1).getTag1());
        assertEquals(Tags.NOUN, bigramDataList.get(1).getTag2());
        assertEquals(275, bigramDataList.get(1).getBigramFrequency());
        assertEquals(Double.valueOf(61.111111111111114), Double.valueOf(bigramDataList.get(1).getBigramProbability()));
        assertEquals(450, bigramDataList.get(1).getTag1Frequency());

        assertEquals(Tags.NOUN, bigramDataList.get(2).getTag1());
        assertEquals(Tags.VERB, bigramDataList.get(2).getTag2());
        assertEquals(275, bigramDataList.get(2).getBigramFrequency());
        assertEquals(Double.valueOf(61.111111111111114), Double.valueOf(bigramDataList.get(2).getBigramProbability()));
        assertEquals(450, bigramDataList.get(2).getTag1Frequency());
    }

}
