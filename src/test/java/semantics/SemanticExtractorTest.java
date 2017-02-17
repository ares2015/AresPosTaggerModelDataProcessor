package semantics;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.semantics.extraction.*;
import com.trainingdataprocessor.tags.EncodedTags;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oliver on 2/17/2017.
 */
public class SemanticExtractorTest {

    private SubjectExtractor subjectExtractor = new SubjectExtractorImpl();

    private VerbPredicateExtractor verbPredicateExtractor = new VerbPredicateExtractorImpl();

    private NounPredicateExtractor nounPredicateExtractor = new NounPredicateExtractorImpl();

    private SemanticExtractor semanticExtractor = new SemanticExtractorImpl(subjectExtractor, verbPredicateExtractor, nounPredicateExtractor);

    @Test
    public void testWithAfterPreposition() {
        List<String> encodedTags = new ArrayList<>();
        encodedTags.add(EncodedTags.VERB_ED);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.IS_ARE);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.DETERMINER);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);

        String sentence = "Shot John Lennon is still very popular singer in the United States";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(tokens, encodedTags, 3, -1, -1, 8);
        assertEquals("Lennon", semanticExtractionData.getAtomicSubject());
        assertEquals("Shot John Lennon ", semanticExtractionData.getExtendedSubject());
        assertEquals("is", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("singer", semanticExtractionData.getAtomicNounPredicate());
        assertEquals("still very popular singer in the United States ", semanticExtractionData.getExtendedNounPredicate());
    }

    @Test
    public void testModalVerbWithBeforeAndAfterPreposition() {
        List<String> encodedTags = new ArrayList<>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.MODAL_VERB);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);

        String sentence = "Members of Fragile can sing absolutely perfectly before audience";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(tokens, encodedTags, 4, 3, 1, 8);
        assertEquals("Members of Fragile ", semanticExtractionData.getExtendedSubject());
        assertEquals("sing", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("can sing absolutely perfectly ", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("absolutely perfectly before audience ", semanticExtractionData.getExtendedNounPredicate());
        assertEquals("", semanticExtractionData.getAtomicNounPredicate());
    }
}
