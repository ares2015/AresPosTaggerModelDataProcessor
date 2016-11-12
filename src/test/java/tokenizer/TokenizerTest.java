package tokenizer;

import com.trainingdataprocessor.tokens.Tokenizer;
import com.trainingdataprocessor.tokens.TokenizerImpl;
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
    public void testSplitStringIntoList(){
        String sentence = "john drink beer in bar";
        List<String> list = tokenizer.splitStringIntoList(sentence);
        assertEquals(5, list.size());
    }

    @Test
    public void testSplitStringWithoutEmptySpaceToList(){
        String s = "JNVN";
        List<String> list = tokenizer.splitStringWithoutEmptySpaceToList(s);
        assertEquals(4, list.size());
    }


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
        List<Integer> commaIndexes = tokenizer.getCommaIndexes(tokens);
        assertEquals(2, commaIndexes.size());
        assertTrue(commaIndexes.contains(0));
        assertTrue(commaIndexes.contains(1));
    }



    @Test
    public void testRemoveComma(){
        String word = "drink,";
        assertEquals("drink", tokenizer.removeCommaAndDot(word));
    }

    @Test
    public void testConvertListToString() {
        List<String> tokens = new ArrayList<>();
        tokens.add("bob");
        tokens.add("alice");
        tokens.add("joe");
        tokens.add("and");
        tokens.add("mike");
        tokens.add("are");
        tokens.add("at");
        tokens.add("school");
        assertEquals("bob alice joe and mike are at school", tokenizer.convertListToString(tokens));

    }

}
