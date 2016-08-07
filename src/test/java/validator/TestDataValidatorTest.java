package validator;

import com.trainingdataprocessor.cache.TagsCache;
import com.trainingdataprocessor.tokenizing.Tokenizer;
import com.trainingdataprocessor.tokenizing.TokenizerImpl;
import com.trainingdataprocessor.validator.ListComparator;
import com.trainingdataprocessor.validator.ListComparatorImpl;
import com.trainingdataprocessor.validator.TestDataValidator;
import com.trainingdataprocessor.validator.TestDataValidatorImpl;
import org.junit.Test;

import java.util.List;

/**
 * Created by Oliver on 8/5/2016.
 */
public class TestDataValidatorTest {

    private TagsCache tagsCache = new TagsCache();
    private Tokenizer tokenizer = new TokenizerImpl();
    private ListComparator listComparator = new ListComparatorImpl();
    private TestDataValidator testDataValidator = new TestDataValidatorImpl(tagsCache, tokenizer, listComparator);

    @Test
    public void testValidateOK(){
        String testDataRow = "boys drink beer at pub#N V N PR N";
        int testDataRowLine = 2;
        testDataValidator.validate(testDataRow, testDataRowLine);
    }

    @Test(expected=IllegalStateException.class)
    public void testValidateNOKMissingComma(){
        String testDataRow = "boys drink, beer at pub@N V N PR N";
        int testDataRowLine = 2;
        testDataValidator.validate(testDataRow, testDataRowLine);
    }

    @Test(expected=IllegalStateException.class)
    public void testValidateNOKCommaIndexesNotEqual(){
        String testDataRow = "boys drink, beer at pub@N V N PR, N";
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
