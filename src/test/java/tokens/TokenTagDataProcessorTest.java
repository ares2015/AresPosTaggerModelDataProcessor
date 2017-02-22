package tokens;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.tags.Tags;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 11/15/2016.
 */
public class TokenTagDataProcessorTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

    @Test
    @Ignore
    public void testInsert() throws InterruptedException {
        List<String> tagsList = new ArrayList<String>();
        tagsList.add(Tags.VERB);

        List<String> tokens = new ArrayList<String>();
        tokens.add("kick");

        List<TrainingDataRow> trainingDataRowList = new ArrayList<>();
        TrainingDataRow trainingDataRow = new TrainingDataRow();
        trainingDataRow.setTagsList(tagsList);
        trainingDataRow.setTokensList(tokens);

        trainingDataRowList.add(trainingDataRow);


    }

    @Test
    @Ignore
    public void testUpdate() throws InterruptedException {
        List<String> tagsList = new ArrayList<String>();
        tagsList.add(Tags.NOUN);

        List<String> tokens = new ArrayList<String>();
        tokens.add("kick");

        List<TrainingDataRow> trainingDataRowList = new ArrayList<>();
        TrainingDataRow trainingDataRow = new TrainingDataRow();
        trainingDataRow.setTagsList(tagsList);
        trainingDataRow.setTokensList(tokens);

        trainingDataRowList.add(trainingDataRow);

    }
}