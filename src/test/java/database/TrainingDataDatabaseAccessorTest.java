package database;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.syntax.BigramData;
import com.trainingdataprocessor.data.syntax.StartTagEndTagPair;
import com.trainingdataprocessor.database.TrainingDataDatabaseAccessor;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Oliver on 11/9/2016.
 */
public class TrainingDataDatabaseAccessorTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
    TrainingDataDatabaseAccessor trainingDataDatabaseAccessor = (TrainingDataDatabaseAccessor) context.getBean("trainingDataDatabaseAccessor");

    @Test
    public void testInsertSemanticData(){
        SemanticExtractionData semanticExtractionData = new SemanticExtractionData();
        semanticExtractionData.setAtomicSubject("Gretzky");
        semanticExtractionData.setExtendedSubject("Wayne Gretzky");
        semanticExtractionData.setAtomicVerbPredicate("was");
        semanticExtractionData.setAtomicNounPredicate("player");
        semanticExtractionData.setExtendedNounPredicate("ice hockey player");
        trainingDataDatabaseAccessor.insertSemanticData(semanticExtractionData);

        String sql = "select max(id) from jos_nlp_semantic_data";
        int id = jdbcTemplate.queryForInt(sql);

        sql = "delete from jos_nlp_semantic_data where id = ?";
        jdbcTemplate.update(sql,  new Object[]{id});
    }

    @Test
    @Ignore
    public void testInsertBigramData(){
        BigramData bigramData = new BigramData("N", "V", false, false);
        trainingDataDatabaseAccessor.insertBigramData(bigramData);
    }

    @Test
    @Ignore
    public void testUpdateBigramData(){
        BigramData bigramData = new BigramData("V", "N", false, false);
        trainingDataDatabaseAccessor.insertBigramData(bigramData);
    }

    @Test
    @Ignore
    public void testInsertStartTagEndTagPair(){
        StartTagEndTagPair startTagEndTagPair = new StartTagEndTagPair("N", "AJ", "N AJ AJ N", 4, 3, 7, false);
        trainingDataDatabaseAccessor.insertStartTagEndTagPair(startTagEndTagPair);
    }

    @Test
    @Ignore
    public void testUpdateStartTagEndTagPair(){
        StartTagEndTagPair startTagEndTagPair = new StartTagEndTagPair("V", "N", "V AJ AJ N", 4, 3, 7, false);
        trainingDataDatabaseAccessor.insertStartTagEndTagPair(startTagEndTagPair);
    }


}
