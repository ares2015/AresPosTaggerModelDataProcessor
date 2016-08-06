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

    @Test
    public void testGetTagSubPaths() {
        List<String> tags = new ArrayList<>();
        tags.add(Tags.NOUN);
        tags.add(Tags.NOUN);
        tags.add(Tags.ADVERB);
        tags.add(Tags.VERB);
        tags.add(Tags.ADJECTIVE);
        tags.add(Tags.NOUN);
        List<Integer> commaIndexes = new ArrayList<>();

        commaIndexes.add(0);
        commaIndexes.add(2);
        commaIndexes.add(4);
        commaIndexes.add(tags.size() - 1);


        List<List<String>> tagSubPaths = tokenizer.getTagSubPaths(commaIndexes, tags);
        assertEquals(3, tagSubPaths.size());
        assertEquals(3, tagSubPaths.get(0).size());
        assertEquals(2, tagSubPaths.get(1).size());
        assertEquals(1, tagSubPaths.get(2).size());
        assertEquals(Tags.NOUN, tagSubPaths.get(0).get(0));
        assertEquals(Tags.NOUN, tagSubPaths.get(0).get(1));
        assertEquals(Tags.ADVERB, tagSubPaths.get(0).get(2));
        assertEquals(Tags.VERB, tagSubPaths.get(1).get(0));
        assertEquals(Tags.ADJECTIVE, tagSubPaths.get(1).get(1));
        assertEquals(Tags.NOUN, tagSubPaths.get(2).get(0));

    }


}
