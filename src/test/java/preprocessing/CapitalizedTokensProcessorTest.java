package preprocessing;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.preprocessing.CapitalizedTokensProcessor;
import com.trainingdataprocessor.preprocessing.CapitalizedTokensProcessorImpl;
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
        tokensList.add("is");
        tokensList.add("city");
        tokensList.add("in");
        tokensList.add("the");
        tokensList.add("United");
        tokensList.add("States");

        List<String> tagsList = new ArrayList<>();
        tagsList.add("N");
        tagsList.add("N");
        tagsList.add("N");
        tagsList.add("IA");
        tagsList.add("N");
        tagsList.add("PR");
        tagsList.add("DET");
        tagsList.add("N");
        tagsList.add("N");


        TrainingDataRow trainingDataRow = new TrainingDataRow();
        trainingDataRow.setTokensList(tokensList);
        trainingDataRow.setTagsList(tagsList);
        capitalizedTokensProcessor.process(trainingDataRow);
        assertEquals(6, trainingDataRow.getTokensList().size());
        assertEquals(6, trainingDataRow.getTagsList().size());

    }
}
