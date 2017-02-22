package paths;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.tokens.Tokenizer;
import com.trainingdataprocessor.tokens.TokenizerImpl;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 11/17/2016.
 */
public class EncodedPathsProcessorTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    Tokenizer tokenizer = new TokenizerImpl();

    @Test
    @Ignore
    public void testInsert() throws InterruptedException {
        List<TrainingDataRow> trainingDataRowList = new ArrayList<>();
        TrainingDataRow trainingDataRow = new TrainingDataRow();
        trainingDataRow.setEncodedPathAsString("JNVN");
        trainingDataRowList.add(trainingDataRow);
    }
}
