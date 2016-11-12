package reader;

import com.trainingdataprocessor.reader.TestDataFileReaderImpl;
import com.trainingdataprocessor.reader.TestDataReader;
import com.trainingdataprocessor.tokens.Tokenizer;
import com.trainingdataprocessor.tokens.TokenizerImpl;
import com.trainingdataprocessor.validator.ListComparator;
import com.trainingdataprocessor.validator.ListComparatorImpl;
import com.trainingdataprocessor.validator.TestDataValidator;
import com.trainingdataprocessor.validator.TestDataValidatorImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 8/5/2016.
 */
public class TestDataFileReaderTest {

    private Tokenizer tokenizer = new TokenizerImpl();
    private ListComparator listComparator = new ListComparatorImpl();
    private TestDataValidator testDataValidator = new TestDataValidatorImpl(tokenizer, listComparator);
    private TestDataReader testDataReader = new TestDataFileReaderImpl(testDataValidator);


    @Test
    public void testRead(){
        List<String> testDataRowList = testDataReader.read();
        assertTrue(testDataRowList.size() > 0);
    }
}
