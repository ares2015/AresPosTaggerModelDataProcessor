package factories;

import com.trainingdataprocessor.data.syntax.BigramData;
import com.trainingdataprocessor.factories.bigram.BigramDataListFactory;
import com.trainingdataprocessor.factories.bigram.BigramDataListFactoryImpl;
import com.trainingdataprocessor.tags.EncodedTags;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BigramDataListFactoryTest {

    BigramDataListFactory bigramDataListFactory = new BigramDataListFactoryImpl();

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


        assertEquals(EncodedTags.ADJECTIVE, bigramDataList.get(1).getTag1());
        assertEquals(EncodedTags.NOUN, bigramDataList.get(1).getTag2());

        assertEquals(EncodedTags.NOUN, bigramDataList.get(2).getTag1());
        assertEquals(EncodedTags.VERB, bigramDataList.get(2).getTag2());

    }

}
