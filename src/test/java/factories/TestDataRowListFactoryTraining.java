package factories;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.factories.multilist.MultiListFactory;
import com.trainingdataprocessor.factories.multilist.MultiListFactoryImpl;
import com.trainingdataprocessor.factories.row.TrainingDataRowListFactory;
import com.trainingdataprocessor.factories.row.TrainingDataRowListFactoryImpl;
import com.trainingdataprocessor.tokens.Tokenizer;
import com.trainingdataprocessor.tokens.TokenizerImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Oliver on 8/5/2016.
 */
public class TestDataRowListFactoryTraining {

    private Tokenizer tokenizer = new TokenizerImpl();

    private MultiListFactory multiListFactory = new MultiListFactoryImpl();

    private TrainingDataRowListFactory trainingDataRowListFactory = new TrainingDataRowListFactoryImpl(tokenizer, multiListFactory);

    @Test
    public void testCreate() {
        List<String> testDataRowStringList = new ArrayList<>();
        String testDataRowString = "boys drink beer in pub#N V N PR N";
        testDataRowStringList.add(testDataRowString);
        List<TrainingDataRow> trainingDataRowList = trainingDataRowListFactory.create(testDataRowStringList);

        assertEquals(1, trainingDataRowList.size());

        TrainingDataRow trainingDataRow = trainingDataRowList.get(0);

        assertFalse(trainingDataRow.containsSubSentences());
        assertEquals(5, trainingDataRow.getTokensList().size());
        assertEquals(5, trainingDataRow.getTagsList().size());

        assertEquals("boys", trainingDataRow.getTokensList().get(0));
        assertEquals("drink", trainingDataRow.getTokensList().get(1));
        assertEquals("beer", trainingDataRow.getTokensList().get(2));
        assertEquals("in", trainingDataRow.getTokensList().get(3));
        assertEquals("pub", trainingDataRow.getTokensList().get(4));

        assertEquals("N", trainingDataRow.getTagsList().get(0));
        assertEquals("V", trainingDataRow.getTagsList().get(1));
        assertEquals("N", trainingDataRow.getTagsList().get(2));
        assertEquals("PR", trainingDataRow.getTagsList().get(3));
        assertEquals("N", trainingDataRow.getTagsList().get(4));

    }

    @Test
    public void testCreateWithSubSentences() {
        List<String> testDataRowStringList = new ArrayList<>();
        String testDataRowString = "john, harry, mike and bob drink beer in pub#N, N, N AO N V N PR N";
        testDataRowStringList.add(testDataRowString);
        List<TrainingDataRow> trainingDataRowList = trainingDataRowListFactory.create(testDataRowStringList);

        assertEquals(1, trainingDataRowList.size());
        TrainingDataRow trainingDataRow = trainingDataRowList.get(0);

        assertEquals(9, trainingDataRow.getTokensList().size());
        assertEquals(9, trainingDataRow.getTagsList().size());

        List<List<String>> tokensMultiList = trainingDataRow.getTokensMultiList();
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

        List<List<String>> tagsMultiList = trainingDataRow.getTagsMultiList();
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


    }

}
