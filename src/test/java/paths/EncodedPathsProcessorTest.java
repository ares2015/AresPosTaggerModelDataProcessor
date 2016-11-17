package paths;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.database.TrainingDataDatabaseAccessor;
import com.trainingdataprocessor.paths.EncodedPathsProcessorImpl;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Oliver on 11/17/2016.
 */
public class EncodedPathsProcessorTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    TrainingDataDatabaseAccessor trainingDataDatabaseAccessor = (TrainingDataDatabaseAccessor) context.getBean("trainingDataDatabaseAccessor");

    @Test
    @Ignore
    public void testInsert() throws InterruptedException {
        List<TrainingDataRow> trainingDataRowList = new ArrayList<>();
        TrainingDataRow trainingDataRow = new TrainingDataRow();
        trainingDataRow.setEncodedPathAsString("JNVN");
        trainingDataRowList.add(trainingDataRow);

        Runnable encodedPathsProcessor = new EncodedPathsProcessorImpl(trainingDataDatabaseAccessor, trainingDataRowList);
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(encodedPathsProcessor);
        Thread.sleep(7000);
    }


}
