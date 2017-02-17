package semantics.preprocessing;

import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.semantics.preprocessing.SemanticPreprocessor;
import com.trainingdataprocessor.semantics.preprocessing.SemanticPreprocessorImpl;
import com.trainingdataprocessor.tags.Tags;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Oliver on 2/17/2017.
 */
public class SemanticPreprocessorTest {

    private SemanticPreprocessor semanticPreprocessor = new SemanticPreprocessorImpl();

    @Test
    public void test() {
        List<String> tags = new ArrayList<>();
        tags.add(Tags.VERB_ED);
        tags.add(Tags.NOUN);
        tags.add(Tags.NOUN);
        tags.add(Tags.IS_ARE);
        tags.add(Tags.ADVERB);
        tags.add(Tags.ADVERB);
        tags.add(Tags.ADJECTIVE);
        tags.add(Tags.NOUN);
        tags.add(Tags.PREPOSITION);
        tags.add(Tags.DETERMINER);
        tags.add(Tags.NOUN);
        tags.add(Tags.NOUN);

        String sentence = "Shot John Lennon is still very popular singer in the United States";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokens, tags);
        assertTrue(semanticPreprocessingData.canGoToExtraction());
        assertEquals(3, semanticPreprocessingData.getVerbIndex());

        tags.clear();
        tags.add(Tags.NOUN);
        tags.add(Tags.PREPOSITION);
        tags.add(Tags.NOUN);
        tags.add(Tags.MODAL_VERB);
        tags.add(Tags.VERB);
        tags.add(Tags.ADVERB);
        tags.add(Tags.ADVERB);
        tags.add(Tags.PREPOSITION);
        tags.add(Tags.NOUN);
        tags.add(Tags.PREPOSITION);
        tags.add(Tags.NOUN);


        sentence = "Members of Fragile can sing absolutely perfectly before audience in theatre";
        tokens = Arrays.asList(sentence.split("\\ "));
        semanticPreprocessingData = semanticPreprocessor.preprocess(tokens, tags);
        assertTrue(semanticPreprocessingData.canGoToExtraction());
        assertTrue(semanticPreprocessingData.containsBeforeVerbPreposition());
        assertEquals(3, semanticPreprocessingData.getModalVerbIndex());
        assertEquals(4, semanticPreprocessingData.getVerbIndex());
        assertEquals(7, semanticPreprocessingData.getAfterVerbFirstPrepositionIndex());

        tags.clear();
        tags.add(Tags.DETERMINER);
        tags.add(Tags.NOUN);
        tags.add(Tags.IS_ARE);
        tags.add(Tags.DETERMINER);
        tags.add(Tags.ADJECTIVE);
        tags.add(Tags.NOUN);
        tags.add(Tags.PREPOSITION);
        tags.add(Tags.DETERMINER);
        tags.add(Tags.NOUN);
        tags.add(Tags.NOUN);
        tags.add(Tags.WH_PRONOUN);
        tags.add(Tags.VERB);
        tags.add(Tags.ADVERB);
        tags.add(Tags.PREPOSITION);
        tags.add(Tags.ADJECTIVE);
        tags.add(Tags.AND_OR);
        tags.add(Tags.ADJECTIVE);
        tags.add(Tags.NOUN);
        tags.add(Tags.AND_OR);
        tags.add(Tags.DETERMINER);
        tags.add(Tags.QUANTIFIER);
        tags.add(Tags.NOUN);
        tags.add(Tags.PREPOSITION);
        tags.add(Tags.NOUN);

        sentence = "The cheetah is a large felid of the subfamily Felinae that occurs mainly in eastern and southern Africa and a few parts of Iran";
        tokens = Arrays.asList(sentence.split("\\ "));
        semanticPreprocessingData = semanticPreprocessor.preprocess(tokens, tags);
        assertTrue(semanticPreprocessingData.canGoToExtraction());
        assertFalse(semanticPreprocessingData.containsBeforeVerbPreposition());
        assertEquals(7, semanticPreprocessingData.getTagsList().size());
        assertEquals(7, semanticPreprocessingData.getTokensList().size());
        assertEquals(1, semanticPreprocessingData.getVerbIndex());
        assertEquals(4, semanticPreprocessingData.getAfterVerbFirstPrepositionIndex());

        tags.clear();
        tags.add(Tags.NOUN);
        tags.add(Tags.IS_ARE);
        tags.add(Tags.VERB_ING);
        tags.add(Tags.DETERMINER);
        tags.add(Tags.NOUN);

        sentence = "Drones are globalising the battlefield";
        tokens = Arrays.asList(sentence.split("\\ "));
        semanticPreprocessingData = semanticPreprocessor.preprocess(tokens, tags);
        assertTrue(semanticPreprocessingData.canGoToExtraction());
        assertFalse(semanticPreprocessingData.containsBeforeVerbPreposition());
        assertEquals(4, semanticPreprocessingData.getTagsList().size());
        assertEquals(4, semanticPreprocessingData.getTokensList().size());
        assertEquals(1, semanticPreprocessingData.getVerbIndex());
        assertEquals(-1, semanticPreprocessingData.getAfterVerbFirstPrepositionIndex());

    }
}
