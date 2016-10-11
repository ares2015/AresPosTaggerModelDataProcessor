package semantics.verb;

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
public class SemanticRelationsExtractorImplForVerbTest {

    private ConstantWordsCache constantWordsCache = new ConstantWordsCache();

    private SemanticConstantTagAnalyser semanticConstantTagAnalyser = new SemanticConstantTagAnalyserImpl(constantWordsCache);

    @Test
    public void testVerbRelationshipBasic(){
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser);

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);


        String sentence = "brave firemen fight forest fire";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        List<RegexPatternIndexData> isPatternIndexDataList = new ArrayList<>();
        RegexPatternIndexData regexPatternIndexData = new RegexPatternIndexData("NNVNN", 0, "NNVNN".length() - 1);
        isPatternIndexDataList.add(regexPatternIndexData);

        List<SemanticRelationData> semanticRelationDataList = relationshipsExtractorImpl.extract(EncodedTags.VERB, isPatternIndexDataList, tokens, encodedTags, SemanticRelationConstantType.IS_ISNT);
        assertEquals(1, semanticRelationDataList.size());
        assertTrue(semanticRelationDataList.get(0).isPresentTense());
        assertEquals("firemen", semanticRelationDataList.get(0).getAtomicSubject());
        assertEquals("brave firemen", semanticRelationDataList.get(0).getExtendedSubject());
        assertEquals("fire", semanticRelationDataList.get(0).getAtomicPredicate());
        assertEquals("forest fire", semanticRelationDataList.get(0).getExtendedPredicate());
        assertEquals("fight", semanticRelationDataList.get(0).getAtomicVerb());
    }

    @Test
    public void testVerbRelationshipWithBeforeAndAfterPrepositions(){
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser);

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

        List<RegexPatternIndexData> isPatternIndexDataList = new ArrayList<>();
        RegexPatternIndexData regexPatternIndexData = new RegexPatternIndexData("NPN$NNPNN", 0, "NPN$NNPNN".length() - 1);
        isPatternIndexDataList.add(regexPatternIndexData);

        List<SemanticRelationData> semanticRelationDataList = relationshipsExtractorImpl.extract(EncodedTags.VERB_ED, isPatternIndexDataList,
                tokens, encodedTags, SemanticRelationConstantType.VERB);
        assertEquals(1, semanticRelationDataList.size());
        assertFalse(semanticRelationDataList.get(0).isPresentTense());
        assertEquals(null, semanticRelationDataList.get(0).getAtomicSubject());
        assertEquals("Fans of Russia", semanticRelationDataList.get(0).getExtendedSubject());
        assertEquals("supporters", semanticRelationDataList.get(0).getAtomicPredicate());
        assertEquals("English supporters ", semanticRelationDataList.get(0).getExtendedPredicate());
        assertEquals("English supporters at European Championships", semanticRelationDataList.get(0).getPrepositionPredicate());
        assertEquals("attacked", semanticRelationDataList.get(0).getAtomicVerb());
    }

    @Test
    public void testDontVerbRelationship(){
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser);

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.DO);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);


        String sentence = "drunken guys didn't catch Ryanair flight";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        List<RegexPatternIndexData> isPatternIndexDataList = new ArrayList<>();
        RegexPatternIndexData regexPatternIndexData = new RegexPatternIndexData("JNLVNN", 0, "JNLVNN".length() - 1);
        isPatternIndexDataList.add(regexPatternIndexData);

        List<SemanticRelationData> semanticRelationDataList = relationshipsExtractorImpl.extract(EncodedTags.VERB, isPatternIndexDataList, tokens, encodedTags,
                SemanticRelationConstantType.VERB_DONT);
        assertEquals(1, semanticRelationDataList.size());
        assertTrue(semanticRelationDataList.get(0).isPresentTense());
        assertEquals("guys", semanticRelationDataList.get(0).getAtomicSubject());
        assertEquals("drunken guys", semanticRelationDataList.get(0).getExtendedSubject());
        assertEquals("flight", semanticRelationDataList.get(0).getAtomicPredicate());
        assertEquals("Ryanair flight", semanticRelationDataList.get(0).getExtendedPredicate());
        assertEquals("didn't catch", semanticRelationDataList.get(0).getVerbAuxiliaryVerbPhrase());
        assertEquals(null , semanticRelationDataList.get(0).getAtomicVerb());
    }



}
