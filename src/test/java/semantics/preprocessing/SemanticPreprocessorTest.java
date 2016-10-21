package semantics.preprocessing;

import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.regex.RegexPatternSearcherImpl;
import com.trainingdataprocessor.semantics.SemanticRelationConstantType;
import com.trainingdataprocessor.semantics.preprocessing.SemanticPreprocessor;
import com.trainingdataprocessor.semantics.preprocessing.SemanticPreprocessorImpl;
import com.trainingdataprocessor.tags.EncodedTags;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 10/17/2016.
 */
public class SemanticPreprocessorTest {

//    private RegexPatternSearcher regexPatternSearcher = new RegexPatternSearcherImpl();
//
//    private SemanticPreprocessor semanticPreprocessor = new SemanticPreprocessorImpl(regexPatternSearcher);
//
//    @Test
//    public void testVERB_3_LEVEL() {
//        String foundPattern = "JNVNN";
//
//        List<String> encodedTags = new ArrayList<String>();
//        encodedTags.add(EncodedTags.ADJECTIVE);
//        encodedTags.add(EncodedTags.NOUN);
//        encodedTags.add(EncodedTags.VERB);
//        encodedTags.add(EncodedTags.NOUN);
//        encodedTags.add(EncodedTags.NOUN);
//
//
//        String sentence = "brave firemen fight forest fire";
//        List<String> tokens = Arrays.asList(sentence.split("\\ "));
//        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(foundPattern, EncodedTags.VERB,
//                tokens, encodedTags, SemanticRelationConstantType.VERB_3_LEVEL);
////        assertEquals("JN", semanticPreprocessingData.getBeforeVerbNounPhrase().getPattern());
////        assertTrue(semanticPreprocessingData.isContainsBeforeVerbNounPhrase());
////        assertFalse(semanticPreprocessingData.isContainsBeforeVerbPrepositionPhrase());
//    }
//
//    @Test
//    public void testVERB_3_LEVELWithBeforeAndAfterPrepositions() {
//        String foundPattern = "NPNPNVNNPNN";
//
//        List<String> encodedTags = new ArrayList<String>();
//        encodedTags.add(EncodedTags.NOUN);
//        encodedTags.add(EncodedTags.PREPOSITION);
//        encodedTags.add(EncodedTags.NOUN);
//        encodedTags.add(EncodedTags.PREPOSITION);
//        encodedTags.add(EncodedTags.NOUN);
//        encodedTags.add(EncodedTags.VERB);
//        encodedTags.add(EncodedTags.NOUN);
//        encodedTags.add(EncodedTags.NOUN);
//        encodedTags.add(EncodedTags.PREPOSITION);
//        encodedTags.add(EncodedTags.NOUN);
//        encodedTags.add(EncodedTags.NOUN);
//
//
//        String sentence = "Fans of Russia in Paris attack English supporters at European Championships";
//        List<String> tokens = Arrays.asList(sentence.split("\\ "));
//        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(foundPattern, EncodedTags.VERB,
//                tokens, encodedTags, SemanticRelationConstantType.VERB_3_LEVEL);
////        assertEquals("NPNPN", semanticPreprocessingData.getBeforeVerbPrepositionPhrase().getPattern());
////        assertFalse(semanticPreprocessingData.isContainsBeforeVerbNounPhrase());
////        assertTrue(semanticPreprocessingData.isContainsBeforeVerbPrepositionPhrase());
//    }

}