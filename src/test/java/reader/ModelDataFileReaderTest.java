package reader;

import com.aresPosTaggerModelDataProcessor.reader.AresPosTaggerModelDataReader;
import com.aresPosTaggerModelDataProcessor.reader.AresPosTaggerModelDataReaderImpl;
import com.aresPosTaggerModelDataProcessor.tokens.Tokenizer;
import com.aresPosTaggerModelDataProcessor.tokens.TokenizerImpl;
import com.aresPosTaggerModelDataProcessor.validator.ListComparator;
import com.aresPosTaggerModelDataProcessor.validator.ListComparatorImpl;
import com.aresPosTaggerModelDataProcessor.validator.ModelDataValidator;
import com.aresPosTaggerModelDataProcessor.validator.ModelDataValidatorImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 8/5/2016.
 */
public class ModelDataFileReaderTest {

    private Tokenizer tokenizer = new TokenizerImpl();
    private ListComparator listComparator = new ListComparatorImpl();
    private ModelDataValidator modelDataValidator = new ModelDataValidatorImpl(tokenizer, listComparator);
    private AresPosTaggerModelDataReader aresPosTaggerModelDataReader = new AresPosTaggerModelDataReaderImpl(modelDataValidator);


    @Test
    public void testRead(){
        List<String> testDataRowList = aresPosTaggerModelDataReader.read();
        assertTrue(testDataRowList.size() > 0);
    }
}
