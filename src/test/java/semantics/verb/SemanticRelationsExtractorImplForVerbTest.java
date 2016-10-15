package semantics.verb;

import com.trainingdataprocessor.cache.ConstantWordsCache;
import com.trainingdataprocessor.data.RegexPatternIndexData;
import com.trainingdataprocessor.data.semantics.SemanticRelationData;
import com.trainingdataprocessor.semantics.*;
import com.trainingdataprocessor.tags.EncodedTags;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 10/11/2016.
 */
public class SemanticRelationsExtractorImplForVerbTest {

    private ConstantWordsCache constantWordsCache = new ConstantWordsCache();

    private SemanticConstantTagAnalyser semanticConstantTagAnalyser = new SemanticConstantTagAnalyserImpl(constantWordsCache);

    private SubjectExtractor subjectExtractor = new SubjectExtractorImpl();

    private PredicateExtractor predicateExtractor = new PredicateExtractorImpl();

    private VerbExtractor verbExtractor = new VerbExtractorImpl();

    @Test
    public void testVerbRelationshipBasic() {
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser, subjectExtractor, predicateExtractor,
                verbExtractor);

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);


        String sentence = "brave firemen fight forest fire";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        RegexPatternIndexData regexPatternIndexData = new RegexPatternIndexData("NNVNN", 0, "NNVNN".length() - 1);

        SemanticRelationData semanticRelationData = relationshipsExtractorImpl.extract(EncodedTags.VERB, regexPatternIndexData, tokens, encodedTags,
                SemanticRelationConstantType.VERB);

        assertTrue(semanticRelationData.isPositiveVerb());
        assertTrue(semanticRelationData.isPresentTense());
        assertEquals("firemen", semanticRelationData.getAtomicSubject());
        assertEquals("brave firemen", semanticRelationData.getExtendedSubject());
        assertEquals("fire", semanticRelationData.getAtomicPredicate());
        assertEquals("forest fire", semanticRelationData.getExtendedPredicate());
        assertEquals("fight", semanticRelationData.getAtomicVerb());
    }

    @Test
    public void testVerbRelationshipWithBeforeAndAfterPrepositions() {
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser, subjectExtractor, predicateExtractor,
                verbExtractor);

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.VERB_ED);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);


        String sentence = "Fans of Russia attacked English supporters at European Championships";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        RegexPatternIndexData regexPatternIndexData = new RegexPatternIndexData("NPN$NNPNN", 0, "NPN$NNPNN".length() - 1);

        SemanticRelationData semanticRelationData = relationshipsExtractorImpl.extract(EncodedTags.VERB_ED, regexPatternIndexData,
                tokens, encodedTags, SemanticRelationConstantType.VERB);

        assertTrue(semanticRelationData.isPositiveVerb());
        assertFalse(semanticRelationData.isPresentTense());
        assertEquals(null, semanticRelationData.getAtomicSubject());
        assertEquals("Fans of Russia", semanticRelationData.getExtendedSubject());
        assertEquals("supporters", semanticRelationData.getAtomicPredicate());
        assertEquals("English supporters ", semanticRelationData.getExtendedPredicate());
        assertEquals("English supporters at European Championships", semanticRelationData.getPrepositionPredicate());
        assertEquals("attacked", semanticRelationData.getAtomicVerb());
    }

    @Test
    public void testDontVerbRelationship() {
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser, subjectExtractor, predicateExtractor, verbExtractor);

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.DO);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);


        String sentence = "drunken guys didn't catch Ryanair flight";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        RegexPatternIndexData regexPatternIndexData = new RegexPatternIndexData("JNLVNN", 0, "JNLVNN".length() - 1);

        SemanticRelationData semanticRelationData = relationshipsExtractorImpl.extract(EncodedTags.VERB, regexPatternIndexData, tokens, encodedTags,
                SemanticRelationConstantType.VERB_DONT);

        assertFalse(semanticRelationData.isPositiveVerb());
        assertTrue(semanticRelationData.isPresentTense());
        assertEquals("guys", semanticRelationData.getAtomicSubject());
        assertEquals("drunken guys", semanticRelationData.getExtendedSubject());
        assertEquals("flight", semanticRelationData.getAtomicPredicate());
        assertEquals("Ryanair flight", semanticRelationData.getExtendedPredicate());
        assertEquals("didn't catch", semanticRelationData.getExtendedVerb());
        assertEquals("catch" , semanticRelationData.getAtomicVerb());
    }

}