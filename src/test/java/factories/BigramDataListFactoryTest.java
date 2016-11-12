package factories;

import com.trainingdataprocessor.cache.ConstantTagsCache;
import com.trainingdataprocessor.calculator.BigramProbabilityCalculator;
import com.trainingdataprocessor.data.syntax.BigramData;
import com.trainingdataprocessor.database.TrainingDataDatabaseAccessor;
import com.trainingdataprocessor.factories.BigramDataListFactory;
import com.trainingdataprocessor.factories.BigramDataListFactoryImpl;
import com.trainingdataprocessor.tags.EncodedTags;
import mocks.TrainingDataDatabaseAccessorMock;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class BigramDataListFactoryTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

    ConstantTagsCache constantTagsCache = new ConstantTagsCache();
    TrainingDataDatabaseAccessor trainingDataDatabaseAccessor = new TrainingDataDatabaseAccessorMock();
    BigramProbabilityCalculator bigramProbabilityCalculator = new BigramProbabilityCalculator();
    BigramDataListFactory bigramDataListFactory = new BigramDataListFactoryImpl(trainingDataDatabaseAccessor);

    @Test
    public void testCreate(){
        List<String> tags = new ArrayList<String>();
        tags.add(EncodedTags.DETERMINER);
        tags.add(EncodedTags.ADJECTIVE);
        tags.add(EncodedTags.NOUN);
        tags.add(EncodedTags.VERB);
        List<BigramData> bigramDataList = bigramDataListFactory.create(tags);

        assertEquals(3, bigramDataList.size());

        assertEquals(EncodedTags.DETERMINER, bigramDataList.get(0).getTag1());
        assertEquals(EncodedTags.ADJECTIVE, bigramDataList.get(0).getTag2());
        assertEquals(275, bigramDataList.get(0).getBigramFrequency());
        assertEquals(Double.valueOf(61.111111111111114), Double.valueOf(bigramDataList.get(0).getBigramProbability()));
        assertEquals(450, bigramDataList.get(0).getTag1Frequency());
        assertTrue(bigramDataList.get(0).isExistsInDatabase());


        assertEquals(EncodedTags.ADJECTIVE, bigramDataList.get(1).getTag1());
        assertEquals(EncodedTags.NOUN, bigramDataList.get(1).getTag2());
        assertEquals(275, bigramDataList.get(1).getBigramFrequency());
        assertEquals(Double.valueOf(61.111111111111114), Double.valueOf(bigramDataList.get(1).getBigramProbability()));
        assertEquals(450, bigramDataList.get(1).getTag1Frequency());
        assertTrue(bigramDataList.get(1).isExistsInDatabase());

        assertEquals(EncodedTags.NOUN, bigramDataList.get(2).getTag1());
        assertEquals(EncodedTags.VERB, bigramDataList.get(2).getTag2());
        assertEquals(275, bigramDataList.get(2).getBigramFrequency());
        assertEquals(Double.valueOf(61.111111111111114), Double.valueOf(bigramDataList.get(2).getBigramProbability()));
        assertEquals(450, bigramDataList.get(2).getTag1Frequency());
        assertTrue(bigramDataList.get(2).isExistsInDatabase());
    }

}
