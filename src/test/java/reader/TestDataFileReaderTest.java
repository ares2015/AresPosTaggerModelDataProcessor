package reader;

import com.aresPosTaggerModelDataProcessor.reader.TrainingDataFileReaderImpl;
import com.aresPosTaggerModelDataProcessor.reader.TrainingDataReader;
import com.aresPosTaggerModelDataProcessor.tokens.Tokenizer;
import com.aresPosTaggerModelDataProcessor.tokens.TokenizerImpl;
import com.aresPosTaggerModelDataProcessor.validator.ListComparator;
import com.aresPosTaggerModelDataProcessor.validator.ListComparatorImpl;
import com.aresPosTaggerModelDataProcessor.validator.TrainingDataValidator;
import com.aresPosTaggerModelDataProcessor.validator.TrainingDataValidatorImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 8/5/2016.
 */
public class TestDataFileReaderTest {

    private Tokenizer tokenizer = new TokenizerImpl();
    private ListComparator listComparator = new ListComparatorImpl();
    private TrainingDataValidator trainingDataValidator = new TrainingDataValidatorImpl(tokenizer, listComparator);
    private TrainingDataReader trainingDataReader = new TrainingDataFileReaderImpl(trainingDataValidator);


    @Test
    public void testRead(){
        List<String> testDataRowList = trainingDataReader.read();
        assertTrue(testDataRowList.size() > 0);
    }
}
