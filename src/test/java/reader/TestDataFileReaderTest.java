package reader;

import com.trainingdataprocessor.cache.TagsCache;
import com.trainingdataprocessor.data.TestDataRow;
import com.trainingdataprocessor.reader.TestDataFileReaderImpl;
import com.trainingdataprocessor.reader.TestDataReader;
import com.trainingdataprocessor.validator.TestDataValidator;
import com.trainingdataprocessor.validator.TestDataValidatorImpl;
import org.junit.Test;

import java.util.List;

/**
 * Created by Oliver on 8/5/2016.
 */
public class TestDataFileReaderTest {

    private TagsCache tagsCache = new TagsCache();
    private TestDataValidator testDataValidator = new TestDataValidatorImpl(tagsCache);
    private TestDataReader testDataReader = new TestDataFileReaderImpl(testDataValidator);


    @Test
    public void testRead(){
        List<TestDataRow> testDataRowList = testDataReader.read();

    }
}
