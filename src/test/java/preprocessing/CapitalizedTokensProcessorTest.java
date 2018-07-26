package preprocessing;

import com.aresPosTaggerModelDataProcessor.data.preprocessing.ModelDataRow;
import com.aresPosTaggerModelDataProcessor.preprocessing.CapitalizedTokensProcessor;
import com.aresPosTaggerModelDataProcessor.preprocessing.CapitalizedTokensProcessorImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oliver on 11/30/2016.
 */
public class CapitalizedTokensProcessorTest {

    private CapitalizedTokensProcessor capitalizedTokensProcessor = new CapitalizedTokensProcessorImpl();

    @Test
    public void test() {
        List<String> tokensList = new ArrayList<>();
        tokensList.add("New");
        tokensList.add("York");
        tokensList.add("City");
        tokensList.add("Police");
        tokensList.add("Department");
        tokensList.add("is");
        tokensList.add("the");
        tokensList.add("best");
        tokensList.add("one");
        tokensList.add("in");
        tokensList.add("the");
        tokensList.add("United");
        tokensList.add("States");
        tokensList.add("of");
        tokensList.add("America");

        List<String> tagsList = new ArrayList<>();
        tagsList.add("N");
        tagsList.add("N");
        tagsList.add("N");
        tagsList.add("N");
        tagsList.add("N");
        tagsList.add("IA");
        tagsList.add("DET");
        tagsList.add("AJ");
        tagsList.add("NR");
        tagsList.add("PR");
        tagsList.add("DET");
        tagsList.add("N");
        tagsList.add("N");
        tagsList.add("PR");
        tagsList.add("N");

        List<String> encodedTagsList = new ArrayList<>();
        encodedTagsList.add("N");
        encodedTagsList.add("N");
        encodedTagsList.add("N");
        encodedTagsList.add("N");
        encodedTagsList.add("N");
        encodedTagsList.add("I");
        encodedTagsList.add("D");
        encodedTagsList.add("J");
        encodedTagsList.add("#");
        encodedTagsList.add("P");
        encodedTagsList.add("D");
        encodedTagsList.add("N");
        encodedTagsList.add("N");
        encodedTagsList.add("P");
        encodedTagsList.add("N");

        ModelDataRow modelDataRow = new ModelDataRow();
        modelDataRow.setTokensList(tokensList);
        modelDataRow.setTagsList(tagsList);

        capitalizedTokensProcessor.process(modelDataRow);

        assertEquals(10, modelDataRow.getTokensList().size());
        assertEquals(10, modelDataRow.getTagsList().size());
        assertEquals("New York City Police Department", modelDataRow.getTokensList().get(0));
        assertEquals("United States", modelDataRow.getTokensList().get(7));
    }

    @Test
    public void test2() {
        List<String> tokensList = new ArrayList<>();
        tokensList.add("George");
        tokensList.add("Bush");
        tokensList.add("met");
        tokensList.add("Vladimir");
        tokensList.add("Putin");
        tokensList.add("in");
        tokensList.add("Bratislava");
        tokensList.add("in");
        tokensList.add("2005");

        List<String> tagsList = new ArrayList<>();
        tagsList.add("N");
        tagsList.add("N");
        tagsList.add("V");
        tagsList.add("N");
        tagsList.add("N");
        tagsList.add("PR");
        tagsList.add("N");
        tagsList.add("PR");
        tagsList.add("NR");

        List<String> encodedTagsList = new ArrayList<>();
        encodedTagsList.add("N");
        encodedTagsList.add("N");
        encodedTagsList.add("V");
        encodedTagsList.add("N");
        encodedTagsList.add("N");
        encodedTagsList.add("P");
        encodedTagsList.add("N");
        encodedTagsList.add("P");
        encodedTagsList.add("#");

        ModelDataRow modelDataRow = new ModelDataRow();
        modelDataRow.setTokensList(tokensList);
        modelDataRow.setTagsList(tagsList);

        capitalizedTokensProcessor.process(modelDataRow);

        assertEquals(7, modelDataRow.getTokensList().size());
        assertEquals(7, modelDataRow.getTagsList().size());
        assertEquals("George Bush", modelDataRow.getTokensList().get(0));
        assertEquals("Vladimir Putin", modelDataRow.getTokensList().get(2));
        assertEquals("Bratislava", modelDataRow.getTokensList().get(4));
    }
}
