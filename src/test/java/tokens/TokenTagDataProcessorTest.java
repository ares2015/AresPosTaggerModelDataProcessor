package tokens;

import com.aresPosTaggerModelDataProcessor.data.preprocessing.ModelDataRow;
import com.aresPosTaggerModelDataProcessor.tags.Tags;
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

        List<ModelDataRow> modelDataRowList = new ArrayList<>();
        ModelDataRow modelDataRow = new ModelDataRow();
        modelDataRow.setTagsList(tagsList);
        modelDataRow.setTokensList(tokens);

        modelDataRowList.add(modelDataRow);


    }

    @Test
    @Ignore
    public void testUpdate() throws InterruptedException {
        List<String> tagsList = new ArrayList<String>();
        tagsList.add(Tags.NOUN);

        List<String> tokens = new ArrayList<String>();
        tokens.add("kick");

        List<ModelDataRow> modelDataRowList = new ArrayList<>();
        ModelDataRow modelDataRow = new ModelDataRow();
        modelDataRow.setTagsList(tagsList);
        modelDataRow.setTokensList(tokens);

        modelDataRowList.add(modelDataRow);

    }
}