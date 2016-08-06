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
    public void testRemoveComma(){
        String word = "drink,";
        assertEquals("drink", tokenizer.removeCommaAndDot(word));
    }

}
