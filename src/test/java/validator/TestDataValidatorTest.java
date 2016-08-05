package validator;

import com.trainingdataprocessor.cache.TagsCache;
import com.trainingdataprocessor.validator.TestDataValidator;
import com.trainingdataprocessor.validator.TestDataValidatorImpl;
import org.junit.Test;

/**
 * Created by Oliver on 8/5/2016.
 */
public class TestDataValidatorTest {

    private TagsCache tagsCache = new TagsCache();
    private TestDataValidator testDataValidator = new TestDataValidatorImpl(tagsCache);

    @Test
    public void testValidateOK(){
        String testDataRow = "boys drink beer at pub#N V N PR N";
        int testDataRowLine = 2;
        testDataValidator.validate(testDataRow, testDataRowLine);
    }

    @Test(expected=IllegalStateException.class)
    public void testValidateNOKMissingHashTag(){
        String testDataRow = "boys drink beer at pub@N V N PR N";
        int testDataRowLine = 2;
        testDataValidator.validate(testDataRow, testDataRowLine);
    }

    @Test(expected=IllegalStateException.class)
    public void testValidateNOKLengthsNotEqual(){
        String testDataRow = "boys drink beer at pub#N V N";
        int testDataRowLine = 2;
        testDataValidator.validate(testDataRow, testDataRowLine);
    }

    @Test(expected=IllegalStateException.class)
    public void testValidateNOKInvalidTag(){
        String testDataRow = "boys drink beer at pub#N V N PR BAF";
        int testDataRowLine = 2;
        testDataValidator.validate(testDataRow, testDataRowLine);
    }

}
