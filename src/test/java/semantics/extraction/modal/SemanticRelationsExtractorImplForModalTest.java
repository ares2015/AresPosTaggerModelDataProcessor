package semantics.extraction.modal;

import com.trainingdataprocessor.cache.ConstantWordsCache;
import com.trainingdataprocessor.data.RegexPatternData;
import com.trainingdataprocessor.data.semantics.deprecated.SemanticRelationData;
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
public class SemanticRelationsExtractorImplForModalTest {

    private ConstantWordsCache constantWordsCache = new ConstantWordsCache();

    private SemanticConstantTagAnalyser semanticConstantTagAnalyser = new SemanticConstantTagAnalyserImpl();

    private SubjectExtractor subjectExtractor = new SubjectExtractorImpl();

    private PredicateExtractor predicateExtractor = new PredicateExtractorImpl();

    private VerbExtractor verbExtractor = new VerbExtractorImpl();


    @Test
    public void testMODAL_VERB_NOT_3_LEVEL(){
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser, subjectExtractor, predicateExtractor,
                verbExtractor);

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.MODAL_VERB);
        encodedTags.add(EncodedTags.NOT);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);


        String sentence = "brave firemen can not fight forest fire";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        RegexPatternData regexPatternData = new RegexPatternData("JNMLVNN", 0, "JNMLVNN".length());

        SemanticRelationData semanticRelationData = relationshipsExtractorImpl.extract(EncodedTags.MODAL_VERB,
                regexPatternData, tokens, encodedTags, SemanticRelationConstantType.MODAL_VERB_NOT_3_LEVEL);

        assertFalse(semanticRelationData.isPositiveVerb());
        assertTrue(semanticRelationData.isPresentTense());
        assertEquals("firemen", semanticRelationData.getAtomicSubject());
        assertEquals("brave firemen", semanticRelationData.getExtendedSubject());
        assertEquals("fire", semanticRelationData.getAtomicPredicate());
        assertEquals("forest fire", semanticRelationData.getExtendedPredicate());
        assertEquals("can not fight", semanticRelationData.getExtendedVerb());
        assertEquals("fight" , semanticRelationData.getAtomicVerb());
        assertEquals("can" , semanticRelationData.getAtomicModalVerb());
    }

    @Test
    public void testMODAL_VERB_NOT_3_LEVELWithPreposition(){
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser, subjectExtractor, predicateExtractor, verbExtractor);

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.MODAL_VERB);
        encodedTags.add(EncodedTags.NOT);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);

        String sentence = "brave firemen can not fight forest fire in California mountains";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        RegexPatternData regexPatternData = new RegexPatternData("JNMLVNNPNN", 0, "JNMLVNNPNN".length());

        SemanticRelationData semanticRelationData = relationshipsExtractorImpl.extract(EncodedTags.MODAL_VERB, regexPatternData, tokens, encodedTags,
                SemanticRelationConstantType.MODAL_VERB_NOT_3_LEVEL);

        assertFalse(semanticRelationData.isPositiveVerb());
        assertTrue(semanticRelationData.isPresentTense());
        assertEquals("firemen", semanticRelationData.getAtomicSubject());
        assertEquals("brave firemen", semanticRelationData.getExtendedSubject());
        assertEquals("fire", semanticRelationData.getAtomicPredicate());
        assertEquals("forest fire ", semanticRelationData.getExtendedPredicate());
        assertEquals("forest fire in California mountains", semanticRelationData.getPrepositionPredicate());
        assertEquals("can not fight", semanticRelationData.getExtendedVerb());
        assertEquals("fight" , semanticRelationData.getAtomicVerb());
        assertEquals("can" , semanticRelationData.getAtomicModalVerb());
    }

}
