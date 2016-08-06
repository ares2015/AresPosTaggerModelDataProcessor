package factories;

import com.trainingdataprocessor.data.factories.SubPathsListFactory;
import com.trainingdataprocessor.data.factories.SubPathsListFactoryImpl;
import com.trainingdataprocessor.tags.Tags;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oliver on 8/6/2016.
 */
public class SubPathsListFactoryTest {

    private SubPathsListFactory subPathsListFactory = new SubPathsListFactoryImpl();

    @Test
    public void testCreate(){
            List<String> tags = new ArrayList<>();
            tags.add(Tags.NOUN);
            tags.add(Tags.NOUN);
            tags.add(Tags.ADVERB);
            tags.add(Tags.VERB);
            tags.add(Tags.ADJECTIVE);
            tags.add(Tags.NOUN);
            List<Integer> commaIndexes = new ArrayList<>();

            commaIndexes.add(0);
            commaIndexes.add(2);
            commaIndexes.add(4);
            commaIndexes.add(tags.size() - 1);


            List<List<String>> tagSubPaths = subPathsListFactory.create(commaIndexes, tags);
            assertEquals(3, tagSubPaths.size());
            assertEquals(3, tagSubPaths.get(0).size());
            assertEquals(2, tagSubPaths.get(1).size());
            assertEquals(1, tagSubPaths.get(2).size());
            assertEquals(Tags.NOUN, tagSubPaths.get(0).get(0));
            assertEquals(Tags.NOUN, tagSubPaths.get(0).get(1));
            assertEquals(Tags.ADVERB, tagSubPaths.get(0).get(2));
            assertEquals(Tags.VERB, tagSubPaths.get(1).get(0));
            assertEquals(Tags.ADJECTIVE, tagSubPaths.get(1).get(1));
            assertEquals(Tags.NOUN, tagSubPaths.get(2).get(0));
    }

}
