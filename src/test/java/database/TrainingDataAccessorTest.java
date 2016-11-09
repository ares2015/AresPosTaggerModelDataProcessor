package database;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.database.TrainingDataAccessor;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Oliver on 11/9/2016.
 */
public class TrainingDataAccessorTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
    TrainingDataAccessor trainingDataAccessor = (TrainingDataAccessor) context.getBean("trainingDataAccessor");

    @Test
    public void testInsertSemanticData(){
        SemanticExtractionData semanticExtractionData = new SemanticExtractionData();
        semanticExtractionData.setAtomicSubject("Gretzky");
        semanticExtractionData.setExtendedSubject("Wayne Gretzky");
        semanticExtractionData.setAtomicVerbPredicate("was");
        semanticExtractionData.setAtomicNounPredicate("player");
        semanticExtractionData.setExtendedNounPredicate("ice hockey player");
        trainingDataAccessor.insertSemanticData(semanticExtractionData);

        String sql = "select max(id) from jos_nlp_semantic_data";
        int id = jdbcTemplate.queryForInt(sql);

        sql = "delete from jos_nlp_semantic_data where id = ?";
        jdbcTemplate.update(sql,  new Object[]{id});
    }
}
