package semantics.is;

import com.trainingdataprocessor.cache.ConstantWordsCache;
import com.trainingdataprocessor.data.RegexPatternIndexData;
import com.trainingdataprocessor.data.semantics.SemanticRelationData;
import com.trainingdataprocessor.semantics.SemanticConstantTagAnalyser;
import com.trainingdataprocessor.semantics.SemanticConstantTagAnalyserImpl;
import com.trainingdataprocessor.semantics.SemanticRelationConstantType;
import com.trainingdataprocessor.semantics.SemanticRelationsExtractorImpl;
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


    @Test
    public void testISrelationshipExtractNoPrepositions() {
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser);

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

        List<RegexPatternIndexData> isPatternIndexDataList = new ArrayList<>();
        RegexPatternIndexData regexPatternIndexData = new RegexPatternIndexData("NNIJJJN", 3, 9);
        isPatternIndexDataList.add(regexPatternIndexData);

        List<SemanticRelationData> semanticRelationDataList = relationshipsExtractorImpl.extract(EncodedTags.IS_ARE, isPatternIndexDataList, tokens, encodedTags, SemanticRelationConstantType.IS_ISNT);

        assertEquals(1, semanticRelationDataList.size());
        assertTrue(semanticRelationDataList.get(0).isPresentTense());
        assertEquals("football", semanticRelationDataList.get(0).getAtomicSubject());
        assertEquals("sport", semanticRelationDataList.get(0).getAtomicPredicate());

        assertEquals("American football", semanticRelationDataList.get(0).getExtendedSubject());

        assertEquals("very popular collective sport", semanticRelationDataList.get(0).getExtendedPredicate());
        assertEquals("is", semanticRelationDataList.get(0).getAtomicVerb());
    }

    @Test
    public void testISrelationshipExtractOnePreposition() {
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser);

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

        List<RegexPatternIndexData> isPatternIndexDataList = new ArrayList<>();
        RegexPatternIndexData regexPatternIndexData = new RegexPatternIndexData("NNIJJJNPNN", 3, 12);
        isPatternIndexDataList.add(regexPatternIndexData);

        List<SemanticRelationData> semanticRelationDataList = relationshipsExtractorImpl.extract(EncodedTags.IS_ARE, isPatternIndexDataList, tokens, encodedTags, SemanticRelationConstantType.IS_ISNT);

        assertEquals(1, semanticRelationDataList.size());
        assertTrue(semanticRelationDataList.get(0).isPresentTense());
        assertEquals("football", semanticRelationDataList.get(0).getAtomicSubject());
        assertEquals("sport", semanticRelationDataList.get(0).getAtomicPredicate());

        assertEquals("American football", semanticRelationDataList.get(0).getExtendedSubject());

        assertEquals("very popular collective sport in Stanford University", semanticRelationDataList.get(0).getPrepositionPredicate());

        assertEquals("is", semanticRelationDataList.get(0).getAtomicVerb());
    }

    @Test
    public void testISrelationshipExtractTwoPrepositions() {
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser);

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

        List<RegexPatternIndexData> isPatternIndexDataList = new ArrayList<>();
        RegexPatternIndexData regexPatternIndexData = new RegexPatternIndexData("NNIJJJNPNNPN", 0, 11);
        isPatternIndexDataList.add(regexPatternIndexData);

        List<SemanticRelationData> semanticRelationDataList = relationshipsExtractorImpl.extract(EncodedTags.IS_ARE, isPatternIndexDataList, tokens, encodedTags, SemanticRelationConstantType.IS_ISNT);

        assertEquals(1, semanticRelationDataList.size());
        assertTrue(semanticRelationDataList.get(0).isPresentTense());
        assertEquals("football", semanticRelationDataList.get(0).getAtomicSubject());
        assertEquals("sport", semanticRelationDataList.get(0).getAtomicPredicate());

        assertEquals("American football", semanticRelationDataList.get(0).getExtendedSubject());

        assertEquals("very popular collective sport ", semanticRelationDataList.get(0).getExtendedPredicate());

        assertEquals("very popular collective sport in Stanford University in California", semanticRelationDataList.get(0).getPrepositionPredicate());

        assertEquals("is", semanticRelationDataList.get(0).getAtomicVerb());
    }

    @Test
    public void testISrelationshipExtractToVerbED() {
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser);

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

        List<RegexPatternIndexData> isPatternIndexDataList = new ArrayList<>();
        RegexPatternIndexData regexPatternIndexData = new RegexPatternIndexData("NI$TVNPYN", 0, 8);
        isPatternIndexDataList.add(regexPatternIndexData);

        List<SemanticRelationData> semanticRelationDataList = relationshipsExtractorImpl.extract(EncodedTags.IS_ARE, isPatternIndexDataList, tokens, encodedTags, SemanticRelationConstantType.IS_ISNT);

        assertEquals(1, semanticRelationDataList.size());
        assertFalse(semanticRelationDataList.get(0).isPresentTense());
        assertEquals("Vivaldi", semanticRelationDataList.get(0).getAtomicSubject());
        assertEquals("taught", semanticRelationDataList.get(0).getAtomicPredicate());
        assertEquals("taught to play violin by his father", semanticRelationDataList.get(0).getPrepositionPredicate());
        assertEquals("was", semanticRelationDataList.get(0).getAtomicVerb());
    }

    @Test
    public void testISrelationshipExtractExtendedPrepositionSubject() {
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser);

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

        List<RegexPatternIndexData> isPatternIndexDataList = new ArrayList<>();
        RegexPatternIndexData regexPatternIndexData = new RegexPatternIndexData("NPNNIJJ", 0, 6);
        isPatternIndexDataList.add(regexPatternIndexData);

        List<SemanticRelationData> semanticRelationDataList = relationshipsExtractorImpl.extract(EncodedTags.IS_ARE, isPatternIndexDataList, tokens, encodedTags, SemanticRelationConstantType.IS_ISNT);

        assertEquals(1, semanticRelationDataList.size());
        assertTrue(semanticRelationDataList.get(0).isPresentTense());
        assertTrue(semanticRelationDataList.get(0).getAtomicSubject() == null);
        assertEquals("Fans of Spartak Trnava", semanticRelationDataList.get(0).getExtendedSubject());
        assertEquals("aggresive", semanticRelationDataList.get(0).getAtomicPredicate());
        assertEquals("very aggresive", semanticRelationDataList.get(0).getExtendedPredicate());
        assertEquals("are", semanticRelationDataList.get(0).getAtomicVerb());
    }

    @Test
    public void testISrelationshipExtractExtendedPrepositionSubjectWithWHdeterminer() {
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser);

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.IS_ARE);
        encodedTags.add(EncodedTags.DETERMINER);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);


        String sentence = "Johannes Vermeer was a Dutch painter who specialized in domestic interior scenes of middle-class life";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        List<RegexPatternIndexData> isPatternIndexDataList = new ArrayList<>();
        RegexPatternIndexData regexPatternIndexData = new RegexPatternIndexData("NNIDNN", 0, "NNIDNN".length() - 1);
        isPatternIndexDataList.add(regexPatternIndexData);

        List<SemanticRelationData> semanticRelationDataList = relationshipsExtractorImpl.extract(EncodedTags.IS_ARE, isPatternIndexDataList, tokens, encodedTags, SemanticRelationConstantType.IS_ISNT);
        assertEquals(1, semanticRelationDataList.size());
        assertFalse(semanticRelationDataList.get(0).isPresentTense());
        assertEquals("Vermeer", semanticRelationDataList.get(0).getAtomicSubject());
        assertEquals("Johannes Vermeer", semanticRelationDataList.get(0).getExtendedSubject());
        assertEquals("painter", semanticRelationDataList.get(0).getAtomicPredicate());
        assertEquals("a Dutch painter", semanticRelationDataList.get(0).getExtendedPredicate());
        assertEquals("was", semanticRelationDataList.get(0).getAtomicVerb());

    }
}
