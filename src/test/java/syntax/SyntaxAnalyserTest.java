package syntax;

import com.trainingdataprocessor.data.TestDataRow;
import com.trainingdataprocessor.database.TrainingDataAccessor;
import com.trainingdataprocessor.factories.BigramDataListFactory;
import com.trainingdataprocessor.factories.BigramDataListFactoryImpl;
import com.trainingdataprocessor.factories.StartTagEndTagPairsListFactory;
import com.trainingdataprocessor.factories.StartTagEndTagPairsListFactoryImpl;
import com.trainingdataprocessor.syntax.SyntaxAnalyserImpl;
import com.trainingdataprocessor.tags.Tags;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Oliver on 11/12/2016.
 */
public class SyntaxAnalyserTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
    TrainingDataAccessor trainingDataAccessor = (TrainingDataAccessor) context.getBean("trainingDataAccessor");

    BigramDataListFactory bigramDataListFactory = new BigramDataListFactoryImpl(trainingDataAccessor);

    StartTagEndTagPairsListFactory startTagEndTagPairsListFactory = new StartTagEndTagPairsListFactoryImpl();


    @Test
    public void test() throws InterruptedException {
        String sentence = "King George visited Hanover again from May to November 1719";
        List<String> tagsList = new ArrayList<String>();
        tagsList.add(Tags.NOUN);
        tagsList.add(Tags.NOUN);
        tagsList.add(Tags.VERB);
        tagsList.add(Tags.NOUN);
        tagsList.add(Tags.ADVERB);
        tagsList.add(Tags.PREPOSITION);
        tagsList.add(Tags.NOUN);
        tagsList.add(Tags.PREPOSITION);
        tagsList.add(Tags.NOUN);
        tagsList.add(Tags.NUMBER);

        List<TestDataRow> testDataRowList = new ArrayList<>();
        TestDataRow testDataRow = new TestDataRow();
        testDataRow.setTagsList(tagsList);

        testDataRowList.add(testDataRow);

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Runnable syntaxAnalyser = new SyntaxAnalyserImpl(trainingDataAccessor, bigramDataListFactory, startTagEndTagPairsListFactory, testDataRowList);

        executor.execute(syntaxAnalyser);

//        String sql = "select max(id) from jos_nlp_semantic_data";
//        int id = jdbcTemplate.queryForInt(sql);
//
//        sql = "delete from jos_nlp_semantic_data where id = ?";
//        jdbcTemplate.update(sql,  new Object[]{id});

        Thread.sleep(5000);
    }



}
