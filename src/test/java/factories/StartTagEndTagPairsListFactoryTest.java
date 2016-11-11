package factories;


import com.trainingdataprocessor.cache.ConstantTagsCache;
import com.trainingdataprocessor.data.StartTagEndTagPair;
import com.trainingdataprocessor.factories.StartTagEndTagPairsListFactory;
import com.trainingdataprocessor.factories.StartTagEndTagPairsListFactoryImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StartTagEndTagPairsListFactoryTest {

    ConstantTagsCache constantTagsCache = new ConstantTagsCache();
    StartTagEndTagPairsListFactory startTagEndTagPairsListFactory = new StartTagEndTagPairsListFactoryImpl();

    @Test
    public void testCreate(){

        List<String> tags = new ArrayList<String>();
        tags.add("N");
        tags.add("V");
        tags.add("DET");
        tags.add("N");

        List<StartTagEndTagPair> startTagEndTagPairList = startTagEndTagPairsListFactory.create(tags);
        assertEquals(6, startTagEndTagPairList.size());

    }
}
