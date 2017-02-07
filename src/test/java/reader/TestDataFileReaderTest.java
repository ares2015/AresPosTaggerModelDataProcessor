package reader;

import com.trainingdataprocessor.reader.TrainingDataFileReaderImpl;
import com.trainingdataprocessor.reader.TrainingDataReader;
import com.trainingdataprocessor.tokens.Tokenizer;
import com.trainingdataprocessor.tokens.TokenizerImpl;
import com.trainingdataprocessor.validator.ListComparator;
import com.trainingdataprocessor.validator.ListComparatorImpl;
import com.trainingdataprocessor.validator.TrainingDataValidator;
import com.trainingdataprocessor.validator.TrainingDataValidatorImpl;
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
