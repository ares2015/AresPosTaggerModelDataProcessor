package semantics.is;

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
public class SemanticRelationsExtractorImplForISTest {

    private ConstantWordsCache constantWordsCache = new ConstantWordsCache();

    private SemanticConstantTagAnalyser semanticConstantTagAnalyser = new SemanticConstantTagAnalyserImpl(constantWordsCache);

    private SubjectExtractor subjectExtractor = new SubjectExtractorImpl();

    private PredicateExtractor predicateExtractor = new PredicateExtractorImpl();

    private VerbExtractor verbExtractor = new VerbExtractorImpl();


    @Test
    public void testIS_ISNT_3_LEVELNoPrepositions() {
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser, subjectExtractor,
                predicateExtractor, verbExtractor);

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.IS_ARE);
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);

        List<String> tokens = new ArrayList<>();
        tokens.add("Wikipedia");
        tokens.add("says");
        tokens.add("that");
        tokens.add("American");
        tokens.add("football");
        tokens.add("is");
        tokens.add("very");
        tokens.add("popular");
        tokens.add("collective");
        tokens.add("sport");

        RegexPatternIndexData regexPatternIndexData = new RegexPatternIndexData("NNIJJJN", 3, "NNIJJJN".length());

        SemanticRelationData semanticRelationData =
                relationshipsExtractorImpl.extract(EncodedTags.IS_ARE, regexPatternIndexData, tokens, encodedTags, SemanticRelationConstantType.IS_ISNT_3_LEVEL);

        assertTrue(semanticRelationData.isPresentTense());
        assertEquals("football", semanticRelationData.getAtomicSubject());
        assertEquals("sport", semanticRelationData.getAtomicPredicate());

        assertEquals("American football", semanticRelationData.getExtendedSubject());

        assertEquals("very popular collective sport", semanticRelationData.getExtendedPredicate());
        assertEquals("is", semanticRelationData.getAtomicVerb());
    }

    @Test
    public void testIS_ISNT_3_LEVELOnePreposition() {
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser, subjectExtractor, predicateExtractor,
                verbExtractor);

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.IS_ARE);
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);

        List<String> tokens = new ArrayList<>();
        tokens.add("Wikipedia");
        tokens.add("says");
        tokens.add("that");
        tokens.add("American");
        tokens.add("football");
        tokens.add("is");
        tokens.add("very");
        tokens.add("popular");
        tokens.add("collective");
        tokens.add("sport");
        tokens.add("in");
        tokens.add("Stanford");
        tokens.add("University");

        RegexPatternIndexData regexPatternIndexData = new RegexPatternIndexData("NNIJJJNPNN", 3, 12);

        SemanticRelationData semanticRelationData = relationshipsExtractorImpl.extract(EncodedTags.IS_ARE, regexPatternIndexData, tokens, encodedTags, SemanticRelationConstantType.IS_ISNT_3_LEVEL);

        assertTrue(semanticRelationData.isPresentTense());
        assertEquals("football", semanticRelationData.getAtomicSubject());
        assertEquals("sport", semanticRelationData.getAtomicPredicate());
        assertEquals("American football", semanticRelationData.getExtendedSubject());
        assertEquals("very popular collective sport in Stanford University", semanticRelationData.getPrepositionPredicate());
        assertEquals("is", semanticRelationData.getAtomicVerb());
    }

    @Test
    public void testIS_ISNT_3_LEVELTwoPrepositions() {
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser, subjectExtractor, predicateExtractor,
                verbExtractor);

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.IS_ARE);
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);

        List<String> tokens = new ArrayList<>();
        tokens.add("American");
        tokens.add("football");
        tokens.add("is");
        tokens.add("very");
        tokens.add("popular");
        tokens.add("collective");
        tokens.add("sport");
        tokens.add("in");
        tokens.add("Stanford");
        tokens.add("University");
        tokens.add("in");
        tokens.add("California");

        RegexPatternIndexData regexPatternIndexData = new RegexPatternIndexData("NNIJJJNPNNPN", 0, "NNIJJJNPNNPN".length());

        SemanticRelationData semanticRelationData = relationshipsExtractorImpl.extract(EncodedTags.IS_ARE, regexPatternIndexData,
                tokens, encodedTags, SemanticRelationConstantType.IS_ISNT_3_LEVEL);

        assertTrue(semanticRelationData.isPositiveVerb());
        assertTrue(semanticRelationData.isPresentTense());
        assertEquals("football", semanticRelationData.getAtomicSubject());
        assertEquals("sport", semanticRelationData.getAtomicPredicate());
        assertEquals("American football", semanticRelationData.getExtendedSubject());
        assertEquals("very popular collective sport ", semanticRelationData.getExtendedPredicate());
        assertEquals("very popular collective sport in Stanford University in California", semanticRelationData.getPrepositionPredicate());
        assertEquals("is", semanticRelationData.getAtomicVerb());
    }

    @Test
    public void testIS_ISNT_3_LEVELToVerbED() {
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser, subjectExtractor, predicateExtractor,
                verbExtractor);

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.IS_ARE);
        encodedTags.add(EncodedTags.VERB_ED);
        encodedTags.add(EncodedTags.TO);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.WH_PRONOUN_POSSESSIVE);
        encodedTags.add(EncodedTags.NOUN);

        List<String> tokens = new ArrayList<>();
        tokens.add("Vivaldi");
        tokens.add("was");
        tokens.add("taught");
        tokens.add("to");
        tokens.add("play");
        tokens.add("violin");
        tokens.add("by");
        tokens.add("his");
        tokens.add("father");

        RegexPatternIndexData regexPatternIndexData = new RegexPatternIndexData("NI$TVNPYN", 0, "NI$TVNPYN".length());

        SemanticRelationData semanticRelationData = relationshipsExtractorImpl.extract(EncodedTags.IS_ARE, regexPatternIndexData,
                tokens, encodedTags, SemanticRelationConstantType.IS_ISNT_3_LEVEL);

        assertTrue(semanticRelationData.isPositiveVerb());
        assertFalse(semanticRelationData.isPresentTense());
        assertEquals("Vivaldi", semanticRelationData.getAtomicSubject());
        assertEquals("taught", semanticRelationData.getAtomicPredicate());
        assertEquals("taught to play violin by his father", semanticRelationData.getPrepositionPredicate());
        assertEquals("was", semanticRelationData.getAtomicVerb());
    }

    @Test
    public void testIS_ISNT_3_LEVELExtendedPrepositionSubject() {
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser, subjectExtractor, predicateExtractor,
                verbExtractor);

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.IS_ARE);
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.ADJECTIVE);

        List<String> tokens = new ArrayList<>();
        tokens.add("Fans");
        tokens.add("of");
        tokens.add("Spartak");
        tokens.add("Trnava");
        tokens.add("are");
        tokens.add("very");
        tokens.add("aggresive");

        RegexPatternIndexData regexPatternIndexData = new RegexPatternIndexData("NPNNIJJ", 0, "NPNNIJJ".length());

        SemanticRelationData semanticRelationData = relationshipsExtractorImpl.extract(EncodedTags.IS_ARE, regexPatternIndexData,
                tokens, encodedTags, SemanticRelationConstantType.IS_ISNT_3_LEVEL);

        assertTrue(semanticRelationData.isPositiveVerb());
        assertTrue(semanticRelationData.isPresentTense());
        assertTrue(semanticRelationData.getAtomicSubject() == null);
        assertEquals("Fans of Spartak Trnava", semanticRelationData.getExtendedSubject());
        assertEquals("aggresive", semanticRelationData.getAtomicPredicate());
        assertEquals("very aggresive", semanticRelationData.getExtendedPredicate());
        assertEquals("are", semanticRelationData.getAtomicVerb());
    }

    @Test
    public void testISrelationshipExtractExtendedPrepositionSubjectWithWHdeterminer() {
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser, subjectExtractor, predicateExtractor, verbExtractor);

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.IS_ARE);
        encodedTags.add(EncodedTags.DETERMINER);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);


        String sentence = "Johannes Vermeer was a Dutch painter who specialized in domestic interior scenes of middle-class life";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        RegexPatternIndexData regexPatternIndexData = new RegexPatternIndexData("NNIDNN", 0, "NNIDNN".length());

        SemanticRelationData semanticRelationData = relationshipsExtractorImpl.extract(EncodedTags.IS_ARE, regexPatternIndexData, tokens,
                encodedTags, SemanticRelationConstantType.IS_ISNT_3_LEVEL);

        assertTrue(semanticRelationData.isPositiveVerb());
        assertFalse(semanticRelationData.isPresentTense());
        assertEquals("Vermeer", semanticRelationData.getAtomicSubject());
        assertEquals("Johannes Vermeer", semanticRelationData.getExtendedSubject());
        assertEquals("painter", semanticRelationData.getAtomicPredicate());
        assertEquals("a Dutch painter", semanticRelationData.getExtendedPredicate());
        assertEquals("was", semanticRelationData.getAtomicVerb());

    }
}
