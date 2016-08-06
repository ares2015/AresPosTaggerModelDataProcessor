package tokenizer;

import com.trainingdataprocessor.tags.Tags;
import com.trainingdataprocessor.tokenizing.Tokenizer;
import com.trainingdataprocessor.tokenizing.TokenizerImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 8/6/2016.
 */
public class TokenizerTest {

    private Tokenizer tokenizer = new TokenizerImpl();

    @Test
    public void testGetCommaIndexes() {
        List<String> tokens = new ArrayList<>();
        tokens.add("bob,");
        tokens.add("alice,");
        tokens.add("joe");
        tokens.add("and");
        tokens.add("mike");
        tokens.add("are");
        tokens.add("at");
        tokens.add("school");
        List<Integer> commaIndexes = new ArrayList<>();
        commaIndexes = tokenizer.getCommaIndexes(commaIndexes, tokens);
        assertEquals(2, commaIndexes.size());
        assertTrue(commaIndexes.contains(0));
        assertTrue(commaIndexes.contains(1));
    }
}
