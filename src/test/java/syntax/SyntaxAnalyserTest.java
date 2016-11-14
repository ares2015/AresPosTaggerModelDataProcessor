package syntax;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.database.TrainingDataDatabaseAccessor;
import com.trainingdataprocessor.factories.BigramDataListFactory;
import com.trainingdataprocessor.factories.BigramDataListFactoryImpl;
import com.trainingdataprocessor.factories.SubPathDataListFactory;
import com.trainingdataprocessor.factories.SubPathDataListFactoryImpl;
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
    TrainingDataDatabaseAccessor trainingDataDatabaseAccessor = (TrainingDataDatabaseAccessor) context.getBean("trainingDataDatabaseAccessor");

    BigramDataListFactory bigramDataListFactory = new BigramDataListFactoryImpl();

    SubPathDataListFactory subPathDataListFactory = new SubPathDataListFactoryImpl();


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

        List<TrainingDataRow> trainingDataRowList = new ArrayList<>();
        TrainingDataRow trainingDataRow = new TrainingDataRow();
        trainingDataRow.setTagsList(tagsList);

        trainingDataRowList.add(trainingDataRow);

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Runnable syntaxAnalyser = new SyntaxAnalyserImpl(trainingDataDatabaseAccessor, bigramDataListFactory, subPathDataListFactory, trainingDataRowList);

        executor.execute(syntaxAnalyser);

//        String sql = "select max(id) from jos_nlp_semantic_data";
//        int id = jdbcTemplate.queryForInt(sql);
//
//        sql = "delete from jos_nlp_semantic_data where id = ?";
//        jdbcTemplate.update(sql,  new Object[]{id});

        Thread.sleep(5000);
    }



}
