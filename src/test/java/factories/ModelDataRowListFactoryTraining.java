package factories;

import com.aresPosTaggerModelDataProcessor.data.preprocessing.ModelDataRow;
import com.aresPosTaggerModelDataProcessor.factories.multilist.MultiListFactory;
import com.aresPosTaggerModelDataProcessor.factories.multilist.MultiListFactoryImpl;
import com.aresPosTaggerModelDataProcessor.factories.row.ModelDataRowListFactory;
import com.aresPosTaggerModelDataProcessor.factories.row.ModelDataRowListFactoryImpl;
import com.aresPosTaggerModelDataProcessor.tokens.Tokenizer;
import com.aresPosTaggerModelDataProcessor.tokens.TokenizerImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Oliver on 8/5/2016.
 */
public class ModelDataRowListFactoryTraining {

    private Tokenizer tokenizer = new TokenizerImpl();

    private MultiListFactory multiListFactory = new MultiListFactoryImpl();

    private ModelDataRowListFactory modelDataRowListFactory = new ModelDataRowListFactoryImpl(tokenizer, multiListFactory);

    @Test
    public void testCreate() {
        List<String> testDataRowStringList = new ArrayList<>();
        String testDataRowString = "boys drink beer in pub#N V N PR N";
        testDataRowStringList.add(testDataRowString);
        List<ModelDataRow> modelDataRowList = modelDataRowListFactory.create(testDataRowStringList);

        assertEquals(1, modelDataRowList.size());

        ModelDataRow modelDataRow = modelDataRowList.get(0);

        assertFalse(modelDataRow.containsSubSentences());
        assertEquals(5, modelDataRow.getTokensList().size());
        assertEquals(5, modelDataRow.getTagsList().size());

        assertEquals("boys", modelDataRow.getTokensList().get(0));
        assertEquals("drink", modelDataRow.getTokensList().get(1));
        assertEquals("beer", modelDataRow.getTokensList().get(2));
        assertEquals("in", modelDataRow.getTokensList().get(3));
        assertEquals("pub", modelDataRow.getTokensList().get(4));

        assertEquals("N", modelDataRow.getTagsList().get(0));
        assertEquals("V", modelDataRow.getTagsList().get(1));
        assertEquals("N", modelDataRow.getTagsList().get(2));
        assertEquals("PR", modelDataRow.getTagsList().get(3));
        assertEquals("N", modelDataRow.getTagsList().get(4));

    }

    @Test
    public void testCreateWithSubSentences() {
        List<String> testDataRowStringList = new ArrayList<>();
        String testDataRowString = "john, harry, mike and bob drink beer in pub#N, N, N AO N V N PR N";
        testDataRowStringList.add(testDataRowString);
        List<ModelDataRow> modelDataRowList = modelDataRowListFactory.create(testDataRowStringList);

        assertEquals(1, modelDataRowList.size());
        ModelDataRow modelDataRow = modelDataRowList.get(0);

        assertEquals(9, modelDataRow.getTokensList().size());
        assertEquals(9, modelDataRow.getTagsList().size());

        List<List<String>> tokensMultiList = modelDataRow.getTokensMultiList();
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

        List<List<String>> tagsMultiList = modelDataRow.getTagsMultiList();
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
