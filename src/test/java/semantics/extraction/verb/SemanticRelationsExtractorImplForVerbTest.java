package semantics.extraction.verb;

import com.trainingdataprocessor.cache.ConstantWordsCache;
import com.trainingdataprocessor.data.RegexPatternData;
import com.trainingdataprocessor.data.semantics.SemanticRelationData;
import com.trainingdataprocessor.semantics.deprecated.*;
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

    private SemanticConstantTagAnalyser semanticConstantTagAnalyser = new SemanticConstantTagAnalyserImpl();

    private SubjectExtractor subjectExtractor = new SubjectExtractorImpl();

    private PredicateExtractor predicateExtractor = new PredicateExtractorImpl();

    private VerbExtractor verbExtractor = new VerbExtractorImpl();

    @Test
    public void testVERB_3_LEVEL() {
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

        RegexPatternData regexPatternData = new RegexPatternData("NNVNN", 0, "NNVNN".length());

        SemanticRelationData semanticRelationData = relationshipsExtractorImpl.extract(EncodedTags.VERB, regexPatternData, tokens, encodedTags,
                SemanticRelationConstantType.VERB_3_LEVEL);

        assertTrue(semanticRelationData.isPositiveVerb());
        assertTrue(semanticRelationData.isPresentTense());
        assertEquals("firemen", semanticRelationData.getAtomicSubject());
        assertEquals("brave firemen", semanticRelationData.getExtendedSubject());
        assertEquals("fire", semanticRelationData.getAtomicPredicate());
        assertEquals("forest fire", semanticRelationData.getExtendedPredicate());
        assertEquals("fight", semanticRelationData.getAtomicVerb());
    }

    @Test
    public void testVERB_3_LEVELWithBeforeAndAfterPrepositions() {
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

        RegexPatternData regexPatternData = new RegexPatternData("NPN$NNPNN", 0, "NPN$NNPNN".length());

        SemanticRelationData semanticRelationData = relationshipsExtractorImpl.extract(EncodedTags.VERB_ED, regexPatternData,
                tokens, encodedTags, SemanticRelationConstantType.VERB_3_LEVEL);

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
    public void tesVERB_DONT_3_LEVEL() {
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

        RegexPatternData regexPatternData = new RegexPatternData("JNLVNN", 0, "JNLVNN".length());

        SemanticRelationData semanticRelationData = relationshipsExtractorImpl.extract(EncodedTags.VERB, regexPatternData, tokens, encodedTags,
                SemanticRelationConstantType.VERB_DONT_3_LEVEL);

        assertFalse(semanticRelationData.isPositiveVerb());
        assertFalse(semanticRelationData.isPresentTense());
        assertEquals("guys", semanticRelationData.getAtomicSubject());
        assertEquals("drunken guys", semanticRelationData.getExtendedSubject());
        assertEquals("flight", semanticRelationData.getAtomicPredicate());
        assertEquals("Ryanair flight", semanticRelationData.getExtendedPredicate());
        assertEquals("didn't catch", semanticRelationData.getExtendedVerb());
        assertEquals("catch" , semanticRelationData.getAtomicVerb());
    }

    @Test
    public void testVERB_2_LEVEL() {
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser, subjectExtractor, predicateExtractor,
                verbExtractor);

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.VERB);

        String sentence = "brave soldiers fight";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        RegexPatternData regexPatternData = new RegexPatternData("JNV", 0, "JNV".length());

        SemanticRelationData semanticRelationData = relationshipsExtractorImpl.extract(EncodedTags.VERB, regexPatternData, tokens, encodedTags,
                SemanticRelationConstantType.VERB_2_LEVEL);

        assertTrue(semanticRelationData.isPositiveVerb());
        assertTrue(semanticRelationData.isPresentTense());
        assertEquals("soldiers", semanticRelationData.getAtomicSubject());
        assertEquals("brave soldiers", semanticRelationData.getExtendedSubject());
        assertEquals("fight", semanticRelationData.getAtomicVerb());
    }

    @Test
    public void testVERB_2_LEVELWithAfterPreposition() {
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser, subjectExtractor, predicateExtractor,
                verbExtractor);

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.VERB_ED);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);


        String sentence = "brave soldiers fought in Vietnam war";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        RegexPatternData regexPatternData = new RegexPatternData("JN$PNN", 0, "JN$PNN".length());

        SemanticRelationData semanticRelationData = relationshipsExtractorImpl.extract(EncodedTags.VERB_ED, regexPatternData, tokens, encodedTags,
                SemanticRelationConstantType.VERB_2_LEVEL);

        assertTrue(semanticRelationData.isPositiveVerb());
        assertFalse(semanticRelationData.isPresentTense());
        assertEquals("soldiers", semanticRelationData.getAtomicSubject());
        assertEquals("brave soldiers", semanticRelationData.getExtendedSubject());
        assertEquals("fought", semanticRelationData.getAtomicVerb());
        assertEquals("in Vietnam war", semanticRelationData.getPrepositionPredicate());

    }

    @Test
    public void testVERB_DONT_2_LEVEL() {
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser, subjectExtractor, predicateExtractor,
                verbExtractor);

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.DO);
        encodedTags.add(EncodedTags.VERB);

        String sentence = "brave soldiers don't fight";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        RegexPatternData regexPatternData = new RegexPatternData("JNLV", 0, "JNLV".length());

        SemanticRelationData semanticRelationData = relationshipsExtractorImpl.extract(EncodedTags.VERB, regexPatternData, tokens, encodedTags,
                SemanticRelationConstantType.VERB_DONT_2_LEVEL);

        assertFalse(semanticRelationData.isPositiveVerb());
        assertTrue(semanticRelationData.isPresentTense());
        assertEquals("soldiers", semanticRelationData.getAtomicSubject());
        assertEquals("brave soldiers", semanticRelationData.getExtendedSubject());
        assertEquals("fight", semanticRelationData.getAtomicVerb());
    }

    @Test
    public void testVERB_DO_NOT_2_LEVEL() {
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser, subjectExtractor, predicateExtractor,
                verbExtractor);

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.DO);
        encodedTags.add(EncodedTags.NOT);
        encodedTags.add(EncodedTags.VERB);

        String sentence = "brave soldiers do not fight";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        RegexPatternData regexPatternData = new RegexPatternData("JNLOV", 0, "JNLOV".length());

        SemanticRelationData semanticRelationData = relationshipsExtractorImpl.extract(EncodedTags.VERB, regexPatternData, tokens, encodedTags,
                SemanticRelationConstantType.VERB_DO_NOT_2_LEVEL);

        assertFalse(semanticRelationData.isPositiveVerb());
        assertTrue(semanticRelationData.isPresentTense());
        assertEquals("soldiers", semanticRelationData.getAtomicSubject());
        assertEquals("brave soldiers", semanticRelationData.getExtendedSubject());
        assertEquals("fight", semanticRelationData.getAtomicVerb());
    }

    @Test
    public void testMODAL_VERB_2_LEVEL() {
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser, subjectExtractor, predicateExtractor,
                verbExtractor);

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.MODAL_VERB);
        encodedTags.add(EncodedTags.VERB);

        String sentence = "brave soldiers can't fight";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        RegexPatternData regexPatternData = new RegexPatternData("JNMV", 0, "JNMV".length());

        SemanticRelationData semanticRelationData = relationshipsExtractorImpl.extract(EncodedTags.MODAL_VERB, regexPatternData, tokens, encodedTags,
                SemanticRelationConstantType.MODAL_VERB_2_LEVEL);

        assertFalse(semanticRelationData.isPositiveVerb());
        assertTrue(semanticRelationData.isPresentTense());
        assertEquals("soldiers", semanticRelationData.getAtomicSubject());
        assertEquals("brave soldiers", semanticRelationData.getExtendedSubject());
        assertEquals("can't", semanticRelationData.getAtomicModalVerb());
        assertEquals("fight", semanticRelationData.getAtomicVerb());
    }

}