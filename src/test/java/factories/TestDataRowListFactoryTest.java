package factories;

import com.trainingdataprocessor.cache.TagsCodingCache;
import com.trainingdataprocessor.data.TestDataRow;
import com.trainingdataprocessor.factories.SubPathsListFactory;
import com.trainingdataprocessor.factories.SubPathsListFactoryImpl;
import com.trainingdataprocessor.factories.TestDataRowListFactory;
import com.trainingdataprocessor.factories.TestDataRowListFactoryImpl;
import com.trainingdataprocessor.encoding.TagsEncoder;
import com.trainingdataprocessor.encoding.TagsEncoderImpl;
import com.trainingdataprocessor.tokenizing.Tokenizer;
import com.trainingdataprocessor.tokenizing.TokenizerImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oliver on 8/5/2016.
 */
public class TestDataRowListFactoryTest {

    private TagsCodingCache tagsCodingCache = new TagsCodingCache();

    private Tokenizer tokenizer = new TokenizerImpl();

    private TagsEncoder tagsEncoder = new TagsEncoderImpl(tagsCodingCache);

    private SubPathsListFactory subPathsListFactory = new SubPathsListFactoryImpl();

    private TestDataRowListFactory testDataRowListFactory = new TestDataRowListFactoryImpl(tokenizer, tagsEncoder, subPathsListFactory);

    @Test
    public void testCreate() {
        List<String> testDataRowStringList = new ArrayList<>();
        String testDataRowString = "boys drink beer in pub#N V N PR N";
        testDataRowStringList.add(testDataRowString);
        List<TestDataRow> testDataRowList = testDataRowListFactory.create(testDataRowStringList);
        assertEquals(1, testDataRowList.size());
        assertEquals("boys drink beer in pub", testDataRowList.get(0).getSentence());
        assertEquals("N V N PR N", testDataRowList.get(0).getTagsAsString());
        assertEquals("NVNPN", testDataRowList.get(0).getEncodedTagsSubPathAsString());
        assertEquals("N", testDataRowList.get(0).getEncodedTagsAsSingleList().get(0));
        assertEquals("V", testDataRowList.get(0).getEncodedTagsAsSingleList().get(1));
        assertEquals("N", testDataRowList.get(0).getEncodedTagsAsSingleList().get(2));
        assertEquals("P", testDataRowList.get(0).getEncodedTagsAsSingleList().get(3));
        assertEquals("N", testDataRowList.get(0).getEncodedTagsAsSingleList().get(4));

        assertEquals(5, testDataRowList.get(0).getTokensList().size());
        assertEquals(5, testDataRowList.get(0).getTagsList().size());
    }

    @Test
    public void testCreateWithSubSentences() {
        List<String> testDataRowStringList = new ArrayList<>();
        String testDataRowString = "john, harry, mike and bob drink beer in pub#N, N, N AO N V N PR N";
        testDataRowStringList.add(testDataRowString);
        List<TestDataRow> testDataRowList = testDataRowListFactory.create(testDataRowStringList);
        assertEquals(1, testDataRowList.size());
        assertEquals("john, harry, mike and bob drink beer in pub", testDataRowList.get(0).getSentence());
        assertEquals("N N N AO N V N PR N", testDataRowList.get(0).getTagsAsString());
        assertEquals("NNN>NVNPN", testDataRowList.get(0).getEncodedTagsSubPathAsString());
        assertEquals(9, testDataRowList.get(0).getTokensList().size());
        assertEquals(9, testDataRowList.get(0).getTagsList().size());
        assertEquals(3, testDataRowList.get(0).getSubSentences().size());

        assertEquals(3, testDataRowList.get(0).getTagsListOfLists().size());
        assertEquals("N", testDataRowList.get(0).getTagsListOfLists().get(2).get(0));
        assertEquals("AO", testDataRowList.get(0).getTagsListOfLists().get(2).get(1));
        assertEquals("N", testDataRowList.get(0).getTagsListOfLists().get(2).get(2));
        assertEquals("V", testDataRowList.get(0).getTagsListOfLists().get(2).get(3));
        assertEquals("N", testDataRowList.get(0).getTagsListOfLists().get(2).get(4));
        assertEquals("PR", testDataRowList.get(0).getTagsListOfLists().get(2).get(5));
        assertEquals("N", testDataRowList.get(0).getTagsListOfLists().get(2).get(6));


        assertEquals(3, testDataRowList.get(0).getEncodedTagSubPathsList().size());
        assertEquals("N>NVNPN", testDataRowList.get(0).getEncodedTagSubPathsList().get(2));


        assertEquals(3, testDataRowList.get(0).getEncodedTagsListOfLists().size());
        assertEquals("N", testDataRowList.get(0).getEncodedTagsListOfLists().get(2).get(0));
        assertEquals(">", testDataRowList.get(0).getEncodedTagsListOfLists().get(2).get(1));
        assertEquals("N", testDataRowList.get(0).getEncodedTagsListOfLists().get(2).get(2));
        assertEquals("V", testDataRowList.get(0).getEncodedTagsListOfLists().get(2).get(3));
        assertEquals("N", testDataRowList.get(0).getEncodedTagsListOfLists().get(2).get(4));
        assertEquals("P", testDataRowList.get(0).getEncodedTagsListOfLists().get(2).get(5));
        assertEquals("N", testDataRowList.get(0).getEncodedTagsListOfLists().get(2).get(6));



    }

}
