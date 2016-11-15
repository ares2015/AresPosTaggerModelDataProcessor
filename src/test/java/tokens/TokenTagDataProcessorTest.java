package tokens;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.database.TrainingDataDatabaseAccessor;
import com.trainingdataprocessor.tags.Tags;
import com.trainingdataprocessor.tokens.TokenTagDataProcessorImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Oliver on 11/15/2016.
 */
public class TokenTagDataProcessorTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    TrainingDataDatabaseAccessor trainingDataDatabaseAccessor = (TrainingDataDatabaseAccessor) context.getBean("trainingDataDatabaseAccessor");

    @Test
    public void testUpdate() throws InterruptedException {
        String sentence = "King George visited Hanover again from May to November 1719";
        String encodedPath = "NNVNAPNPN#";

        List<String> tagsList = new ArrayList<String>();
        tagsList.add(Tags.NOUN);


        List<String> tokens = new ArrayList<String>();
        tokens.add("dog");

        List<TrainingDataRow> trainingDataRowList = new ArrayList<>();
        TrainingDataRow trainingDataRow = new TrainingDataRow();
        trainingDataRow.setTagsList(tagsList);
        trainingDataRow.setTokensList(tokens);

        trainingDataRowList.add(trainingDataRow);

        Runnable tokenTagDataProcessor = new TokenTagDataProcessorImpl(trainingDataDatabaseAccessor, trainingDataRowList);
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(tokenTagDataProcessor);
        Thread.sleep(7000);

    }
}
