package semantics.preprocessing;

import com.trainingdataprocessor.cache.SemanticAnalysisFilterCache;
import com.trainingdataprocessor.semantics.preprocessing.SemanticPreprocessingFilter;
import com.trainingdataprocessor.semantics.preprocessing.SemanticPreprocessingFilterImpl;
import com.trainingdataprocessor.tokens.Tokenizer;
import com.trainingdataprocessor.tokens.TokenizerImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oliver on 11/3/2016.
 */
public class SemanticPreprocessingFilterTest {

    private Tokenizer tokenizer = new TokenizerImpl();

    private SemanticAnalysisFilterCache semanticAnalysisFilterCache = new SemanticAnalysisFilterCache();

    private SemanticPreprocessingFilter semanticPreprocessingFilter = new SemanticPreprocessingFilterImpl();

    @Test
    public void testFilter(){
        List<String> tokens = new ArrayList<String>();
        tokens.add("the");
        tokens.add("dog");

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add("D");
        encodedTags.add("N");

        List<String> filteredTokens = semanticPreprocessingFilter.filterToList(tokens);
        assertEquals(1, filteredTokens.size());
        assertEquals("dog", filteredTokens.get(0));


        List<String> filteredTags = semanticPreprocessingFilter.filterToList(encodedTags);
        assertEquals(1, filteredTags.size());
        assertEquals("N", filteredTags.get(0));


        String filteredPath = semanticPreprocessingFilter.filterToString(encodedTags);
        assertEquals("N", filteredPath);

    }

}
