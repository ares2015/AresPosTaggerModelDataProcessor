package factories;

import com.trainingdataprocessor.cache.TagsCodingCache;
import com.trainingdataprocessor.data.factories.TestDataRowListFactory;
import com.trainingdataprocessor.data.factories.TestDataRowListFactoryImpl;
import com.trainingdataprocessor.encoding.TagsEncoder;
import com.trainingdataprocessor.encoding.TagsEncoderImpl;
import com.trainingdataprocessor.tokenizing.Tokenizer;
import com.trainingdataprocessor.tokenizing.TokenizerImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 8/5/2016.
 */
public class TestDataRowListFactoryTest {

    private TagsCodingCache tagsCodingCache = new TagsCodingCache();

    private Tokenizer tokenizer = new TokenizerImpl();

    private TagsEncoder tagsEncoder = new TagsEncoderImpl(tagsCodingCache);

    private TestDataRowListFactory testDataRowListFactory = new TestDataRowListFactoryImpl(tokenizer, tagsEncoder);

    @Test
    public void testCreate() {
        List<String> testDataRowStringList = new ArrayList<>();
        String testDataRowString = "boys drink beer in pub#N V N PR N";
        testDataRowStringList.add(testDataRowString);
        testDataRowListFactory.create(testDataRowStringList);
    }
}
