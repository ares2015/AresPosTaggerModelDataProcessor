package semantics;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.semantics.extraction.NounPredicateExtractor;
import com.trainingdataprocessor.semantics.extraction.NounPredicateExtractorImpl;
import com.trainingdataprocessor.tags.EncodedTags;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oliver on 2/16/2017.
 */
public class NounPredicateExtractorTest {

    private NounPredicateExtractor nounPredicateExtractor = new NounPredicateExtractorImpl();

    @Test
    public void test() {

        List<String> encodedTags = new ArrayList<>();

        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);

        String sentence = "brave firemen fight furiously dangerous forest fire";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));
        SemanticExtractionData semanticExtractionData = new SemanticExtractionData();
        nounPredicateExtractor.extract(semanticExtractionData, tokens, encodedTags, 2, -1);
        assertEquals("furiously dangerous forest fire ", semanticExtractionData.getExtendedNounPredicate());
        assertEquals("fire", semanticExtractionData.getAtomicNounPredicate());
    }

    @Test
    public void testWithAfterVerbPreposition() {

        List<String> encodedTags = new ArrayList<>();

        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);

        String sentence = "brave firemen fight furiously forest fire in California";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));
        SemanticExtractionData semanticExtractionData = new SemanticExtractionData();
        nounPredicateExtractor.extract(semanticExtractionData, tokens, encodedTags, 2, 6);
        assertEquals("furiously forest fire in California ", semanticExtractionData.getExtendedNounPredicate());
        assertEquals("fire", semanticExtractionData.getAtomicNounPredicate());
    }
}
