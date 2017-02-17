package semantics;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.semantics.VerbPredicateExtractor;
import com.trainingdataprocessor.semantics.VerbPredicateExtractorImpl;
import com.trainingdataprocessor.tags.EncodedTags;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oliver on 2/17/2017.
 */
public class VerbPredicateExtractorTest {

    private VerbPredicateExtractor verbPredicateExtractor = new VerbPredicateExtractorImpl();

    @Test
    public void test() {
        List<String> encodedTags = new ArrayList<>();

        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.MODAL_VERB);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.DETERMINER);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);

        String sentence = "cheetahs can run very quickly on the savannahs of Africa";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));
        SemanticExtractionData semanticExtractionData = new SemanticExtractionData();
        verbPredicateExtractor.extract(semanticExtractionData, tokens, encodedTags, 2, 1);
        assertEquals("can run very quickly ", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("run", semanticExtractionData.getAtomicVerbPredicate());
    }
}
