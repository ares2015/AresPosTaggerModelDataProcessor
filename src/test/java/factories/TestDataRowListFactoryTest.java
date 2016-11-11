package factories;

import com.trainingdataprocessor.cache.TagsCodingCache;
import com.trainingdataprocessor.data.TestDataRow;
import com.trainingdataprocessor.encoding.TagsEncoder;
import com.trainingdataprocessor.encoding.TagsEncoderImpl;
import com.trainingdataprocessor.factories.MultiListFactory;
import com.trainingdataprocessor.factories.MultiListFactoryImpl;
import com.trainingdataprocessor.factories.TestDataRowListFactory;
import com.trainingdataprocessor.factories.TestDataRowListFactoryImpl;
import com.trainingdataprocessor.tokenizing.Tokenizer;
import com.trainingdataprocessor.tokenizing.TokenizerImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Oliver on 8/5/2016.
 */
public class TestDataRowListFactoryTest {

    private TagsCodingCache tagsCodingCache = new TagsCodingCache();

    private Tokenizer tokenizer = new TokenizerImpl();

    private TagsEncoder tagsEncoder = new TagsEncoderImpl();

    private MultiListFactory multiListFactory = new MultiListFactoryImpl();

    private TestDataRowListFactory testDataRowListFactory = new TestDataRowListFactoryImpl(tokenizer, tagsEncoder, multiListFactory);

    @Test
    public void testCreate() {
        List<String> testDataRowStringList = new ArrayList<>();
        String testDataRowString = "boys drink beer in pub#N V N PR N";
        testDataRowStringList.add(testDataRowString);
        List<TestDataRow> testDataRowList = testDataRowListFactory.create(testDataRowStringList);
        assertEquals(1, testDataRowList.size());
        TestDataRow testDataRow = testDataRowList.get(0);
        assertFalse(testDataRow.containsSubSentences());
        assertEquals(5, testDataRow.getTokensList().size());
        assertEquals(5, testDataRow.getTagsList().size());

        assertEquals("boys drink beer in pub", testDataRow.getSentence());
        assertEquals("N V N PR N", testDataRow.getPath());

        assertEquals("NVNPN", testDataRow.getEncodedPath());

        assertEquals("boys", testDataRow.getTokensList().get(0));
        assertEquals("drink", testDataRow.getTokensList().get(1));
        assertEquals("beer", testDataRow.getTokensList().get(2));
        assertEquals("in", testDataRow.getTokensList().get(3));
        assertEquals("pub", testDataRow.getTokensList().get(4));

        assertEquals("N", testDataRow.getTagsList().get(0));
        assertEquals("V", testDataRow.getTagsList().get(1));
        assertEquals("N", testDataRow.getTagsList().get(2));
        assertEquals("PR", testDataRow.getTagsList().get(3));
        assertEquals("N", testDataRow.getTagsList().get(4));

        assertEquals("N", testDataRow.getEncodedTagsList().get(0));
        assertEquals("V", testDataRow.getEncodedTagsList().get(1));
        assertEquals("N", testDataRow.getEncodedTagsList().get(2));
        assertEquals("P", testDataRow.getEncodedTagsList().get(3));
        assertEquals("N", testDataRow.getEncodedTagsList().get(4));

    }

    @Test
    public void testCreateWithSubSentences() {
        List<String> testDataRowStringList = new ArrayList<>();
        String testDataRowString = "john, harry, mike and bob drink beer in pub#N, N, N AO N V N PR N";
        testDataRowStringList.add(testDataRowString);
        List<TestDataRow> testDataRowList = testDataRowListFactory.create(testDataRowStringList);

        assertEquals(1, testDataRowList.size());
        TestDataRow testDataRow = testDataRowList.get(0);

        assertTrue(testDataRow.containsSubSentences());
        assertEquals("john, harry, mike and bob drink beer in pub", testDataRow.getSentence());
        assertEquals("N, N, N AO N V N PR N", testDataRow.getPath());
        assertEquals("NNN>NVNPN", testDataRow.getEncodedPath());

        assertEquals(9, testDataRow.getTokensList().size());
        assertEquals(9, testDataRow.getTagsList().size());

        List<List<String>> tokensMultiList = testDataRow.getTokensMultiList();
        assertEquals(3, tokensMultiList.size());
        assertEquals("john", tokensMultiList.get(0).get(0));
        assertEquals("harry", tokensMultiList.get(1).get(0));
        assertEquals("mike", tokensMultiList.get(2).get(0));
        assertEquals("and", tokensMultiList.get(2).get(1));
        assertEquals("bob", tokensMultiList.get(2).get(2));
        assertEquals("drink", tokensMultiList.get(2).get(3));
        assertEquals("beer", tokensMultiList.get(2).get(4));
        assertEquals("in", tokensMultiList.get(2).get(5));
        assertEquals("pub", tokensMultiList.get(2).get(6));

        List<List<String>> tagsMultiList = testDataRow.getTagsMultiList();
        assertEquals(3, tagsMultiList.size());
        assertEquals("N", tagsMultiList.get(0).get(0));
        assertEquals("N", tagsMultiList.get(1).get(0));
        assertEquals("N", tagsMultiList.get(2).get(0));
        assertEquals("AO", tagsMultiList.get(2).get(1));
        assertEquals("N", tagsMultiList.get(2).get(2));
        assertEquals("V", tagsMultiList.get(2).get(3));
        assertEquals("N", tagsMultiList.get(2).get(4));
        assertEquals("PR", tagsMultiList.get(2).get(5));
        assertEquals("N", tagsMultiList.get(2).get(6));

        List<List<String>> encodedTagsMultiList = testDataRow.getEncodedTagsMultiList();
        assertEquals(3, encodedTagsMultiList.size());
        assertEquals("N", encodedTagsMultiList.get(0).get(0));
        assertEquals("N", encodedTagsMultiList.get(1).get(0));
        assertEquals("N", encodedTagsMultiList.get(2).get(0));
        assertEquals(">", encodedTagsMultiList.get(2).get(1));
        assertEquals("N", encodedTagsMultiList.get(2).get(2));
        assertEquals("V", encodedTagsMultiList.get(2).get(3));
        assertEquals("N", encodedTagsMultiList.get(2).get(4));
        assertEquals("P", encodedTagsMultiList.get(2).get(5));
        assertEquals("N", encodedTagsMultiList.get(2).get(6));

        List<String> encodedSubPathsList = testDataRow.getEncodedSubPathsList();
        assertEquals(3, encodedSubPathsList.size());
        assertEquals("N", encodedSubPathsList.get(0));
        assertEquals("N", encodedSubPathsList.get(1));
        assertEquals("N>NVNPN", encodedSubPathsList.get(2));


    }

}
