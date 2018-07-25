package factories;

import com.aresPosTaggerModelDataProcessor.data.syntax.BigramData;
import com.aresPosTaggerModelDataProcessor.factories.bigram.BigramDataListFactory;
import com.aresPosTaggerModelDataProcessor.factories.bigram.BigramDataListFactoryImpl;
import com.aresPosTaggerModelDataProcessor.tags.Tags;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BigramDataListFactoryTest {

    BigramDataListFactory bigramDataListFactory = new BigramDataListFactoryImpl();

    @Test
    public void testCreate(){
        List<String> tags = new ArrayList<String>();
        tags.add(Tags.DETERMINER);
        tags.add(Tags.ADJECTIVE);
        tags.add(Tags.NOUN);
        tags.add(Tags.VERB);
        List<BigramData> bigramDataList = bigramDataListFactory.create(tags);

        assertEquals(3, bigramDataList.size());

        assertEquals(Tags.DETERMINER, bigramDataList.get(0).getTag1());
        assertEquals(Tags.ADJECTIVE, bigramDataList.get(0).getTag2());


        assertEquals(Tags.ADJECTIVE, bigramDataList.get(1).getTag1());
        assertEquals(Tags.NOUN, bigramDataList.get(1).getTag2());

        assertEquals(Tags.NOUN, bigramDataList.get(2).getTag1());
        assertEquals(Tags.VERB, bigramDataList.get(2).getTag2());

    }

}
