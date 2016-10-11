package semantics.modal;

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

/**
 * Created by Oliver on 10/11/2016.
 */
public class SemanticRelationsExtractorImplForModalTest {

    private ConstantWordsCache constantWordsCache = new ConstantWordsCache();

    private SemanticConstantTagAnalyser semanticConstantTagAnalyser = new SemanticConstantTagAnalyserImpl(constantWordsCache);

    @Test
    public void testModalVerbNotRelationship(){
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser);

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

        List<RegexPatternIndexData> isPatternIndexDataList = new ArrayList<>();
        RegexPatternIndexData regexPatternIndexData = new RegexPatternIndexData("JNMLVNN", 0, "JNMLVNN".length() - 1);
        isPatternIndexDataList.add(regexPatternIndexData);

        List<SemanticRelationData> semanticRelationDataList = relationshipsExtractorImpl.extract(EncodedTags.MODAL_VERB, isPatternIndexDataList, tokens, encodedTags,
                SemanticRelationConstantType.MODAL_VERB_NOT);
        assertEquals(1, semanticRelationDataList.size());
        assertFalse(semanticRelationDataList.get(0).isPresentTense());
        assertEquals("firemen", semanticRelationDataList.get(0).getAtomicSubject());
        assertEquals("brave firemen", semanticRelationDataList.get(0).getExtendedSubject());
        assertEquals("fire", semanticRelationDataList.get(0).getAtomicPredicate());
        assertEquals("forest fire", semanticRelationDataList.get(0).getExtendedPredicate());
        assertEquals("can not fight", semanticRelationDataList.get(0).getVerbAuxiliaryVerbPhrase());
        assertEquals(null , semanticRelationDataList.get(0).getAtomicVerb());
    }

    @Test
    public void testModalVerbNotRelationshipWithPreposition(){
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser);

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.MODAL_VERB);
        encodedTags.add(EncodedTags.NOT);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);



        String sentence = "brave firemen can not fight forest fire in California mountains";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        List<RegexPatternIndexData> isPatternIndexDataList = new ArrayList<>();
        RegexPatternIndexData regexPatternIndexData = new RegexPatternIndexData("JNMLVNNPNN", 0, "JNMLVNNPNN".length() - 1);
        isPatternIndexDataList.add(regexPatternIndexData);

        List<SemanticRelationData> semanticRelationDataList = relationshipsExtractorImpl.extract(EncodedTags.MODAL_VERB, isPatternIndexDataList, tokens, encodedTags,
                SemanticRelationConstantType.MODAL_VERB_NOT);
        assertEquals(1, semanticRelationDataList.size());
        assertFalse(semanticRelationDataList.get(0).isPresentTense());
        assertEquals("firemen", semanticRelationDataList.get(0).getAtomicSubject());
        assertEquals("brave firemen", semanticRelationDataList.get(0).getExtendedSubject());
        assertEquals("fire", semanticRelationDataList.get(0).getAtomicPredicate());
        assertEquals("forest fire ", semanticRelationDataList.get(0).getExtendedPredicate());
        assertEquals("forest fire in California mountains", semanticRelationDataList.get(0).getPrepositionPredicate());
        assertEquals("can not fight", semanticRelationDataList.get(0).getVerbAuxiliaryVerbPhrase());
        assertEquals(null , semanticRelationDataList.get(0).getAtomicVerb());
    }

}
