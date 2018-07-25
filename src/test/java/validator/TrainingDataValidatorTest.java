package validator;

import com.aresPosTaggerModelDataProcessor.cache.TagsCache;
import com.aresPosTaggerModelDataProcessor.tokens.Tokenizer;
import com.aresPosTaggerModelDataProcessor.tokens.TokenizerImpl;
import com.aresPosTaggerModelDataProcessor.validator.ListComparator;
import com.aresPosTaggerModelDataProcessor.validator.ListComparatorImpl;
import com.aresPosTaggerModelDataProcessor.validator.TrainingDataValidator;
import com.aresPosTaggerModelDataProcessor.validator.TrainingDataValidatorImpl;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 8/5/2016.
 */
public class TrainingDataValidatorTest {

    private TagsCache tagsCache = new TagsCache();
    private Tokenizer tokenizer = new TokenizerImpl();
    private ListComparator listComparator = new ListComparatorImpl();
    private TrainingDataValidator trainingDataValidator = new TrainingDataValidatorImpl(tokenizer, listComparator);

    @Test
    public void testValidateOK() {
        String testDataRow = "boys drink beer at pub#N V N PR N";
        int testDataRowLine = 2;
        trainingDataValidator.validate(testDataRow, testDataRowLine);
        testDataRow = "In fact, it 's very small#CJ N, PRP V AV AJ";
        assertTrue(trainingDataValidator.validate(testDataRow, testDataRowLine));
    }

    @Test
    public void testValidateNOKMissingComma() {
        String testDataRow = "boys drink, beer at pub@N V N PR N";
        int testDataRowLine = 2;
        assertFalse(trainingDataValidator.validate(testDataRow, testDataRowLine));
    }

    @Test
    public void testValidateNOKCommaIndexesNotEqual() {
        String testDataRow = "boys drink, beer at pub@N V N PR, N";
        int testDataRowLine = 2;
        assertFalse(trainingDataValidator.validate(testDataRow, testDataRowLine));
    }

    @Test
    public void testValidateNOKMissingHashTag() {
        String testDataRow = "boys drink beer at pub@N V N PR N";
        int testDataRowLine = 2;
        assertFalse(trainingDataValidator.validate(testDataRow, testDataRowLine));
    }

    @Test
    public void testValidateNOKLengthsNotEqual() {
        String testDataRow = "boys drink beer at pub#N V N";
        int testDataRowLine = 2;
        assertFalse(trainingDataValidator.validate(testDataRow, testDataRowLine));
    }

    @Test
    public void testValidateNOKInvalidTag() {
        String testDataRow = "boys drink beer at pub#N V N PR BAF";
        int testDataRowLine = 2;
        assertFalse(trainingDataValidator.validate(testDataRow, testDataRowLine));
    }

}