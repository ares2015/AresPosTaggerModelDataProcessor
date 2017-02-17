package semantics.extraction;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.semantics.extraction.*;
import com.trainingdataprocessor.tags.Tags;
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
        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();
        semanticPreprocessingData.setTokensList(tokens);
        semanticPreprocessingData.setTagsList(tags);
        semanticPreprocessingData.setVerbIndex(3);
        semanticPreprocessingData.setContainsBeforeVerbPreposition(false);
        semanticPreprocessingData.setAfterVerbFirstPrepositionIndex(8);
        semanticPreprocessingData.setModalVerbIndex(-1);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("Lennon", semanticExtractionData.getAtomicSubject());
        assertEquals("Shot John Lennon ", semanticExtractionData.getExtendedSubject());
        assertEquals("is", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("singer", semanticExtractionData.getAtomicNounPredicate());
        assertEquals("still very popular singer in the United States ", semanticExtractionData.getExtendedNounPredicate());
    }

    @Test
    public void testModalVerbWithBeforeAndAfterPreposition() {
        List<String> tags = new ArrayList<>();
        tags.add(Tags.NOUN);
        tags.add(Tags.PREPOSITION);
        tags.add(Tags.NOUN);
        tags.add(Tags.MODAL_VERB);
        tags.add(Tags.VERB);
        tags.add(Tags.ADVERB);
        tags.add(Tags.ADVERB);
        tags.add(Tags.PREPOSITION);
        tags.add(Tags.NOUN);

        String sentence = "Members of Fragile can sing absolutely perfectly before audience";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();
        semanticPreprocessingData.setTokensList(tokens);
        semanticPreprocessingData.setTagsList(tags);
        semanticPreprocessingData.setVerbIndex(4);
        semanticPreprocessingData.setContainsBeforeVerbPreposition(true);
        semanticPreprocessingData.setAfterVerbFirstPrepositionIndex(7);
        semanticPreprocessingData.setModalVerbIndex(3);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("Members of Fragile ", semanticExtractionData.getExtendedSubject());
        assertEquals("sing", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("can sing absolutely perfectly ", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("absolutely perfectly before audience ", semanticExtractionData.getExtendedNounPredicate());
        assertEquals("", semanticExtractionData.getAtomicNounPredicate());
    }

    @Test
    public void testWithAfterVerbVerbIng() {
        List<String> tags = new ArrayList<>();
        tags.add(Tags.NOUN);
        tags.add(Tags.IS_ARE);
        tags.add(Tags.VERB_ING);
        tags.add(Tags.DETERMINER);
        tags.add(Tags.NOUN);

        String sentence = "Drones are globalising battlefield";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();
        semanticPreprocessingData.setVerbIndex(1);
        semanticPreprocessingData.setContainsAfterVerbVerbIng(true);
        semanticPreprocessingData.setContainsBeforeVerbPreposition(false);
        semanticPreprocessingData.setTokensList(tokens);
        semanticPreprocessingData.setTagsList(tags);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("Drones", semanticExtractionData.getAtomicSubject());
        assertEquals("", semanticExtractionData.getExtendedSubject());
        assertEquals("are", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("globalising battlefield ", semanticExtractionData.getExtendedNounPredicate());
        assertEquals("", semanticExtractionData.getAtomicNounPredicate());
    }
}
