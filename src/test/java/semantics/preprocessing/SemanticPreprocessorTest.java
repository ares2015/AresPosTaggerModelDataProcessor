package semantics.preprocessing;

import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.regex.RegexPatternSearcherImpl;
import com.trainingdataprocessor.semantics.preprocessing.SemanticPreprocessor;
import com.trainingdataprocessor.semantics.preprocessing.SemanticPreprocessorImpl;
import com.trainingdataprocessor.semantics.preprocessing.phrases.PhrasePreprocessor;
import com.trainingdataprocessor.semantics.preprocessing.phrases.NounPhrasePreprocessorImpl;
import com.trainingdataprocessor.semantics.preprocessing.phrases.PrepositionPhrasePreprocessorImpl;
import com.trainingdataprocessor.semantics.preprocessing.phrases.VerbPhrasePreprocessorImpl;
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

    private RegexPatternSearcher regexPatternSearcher = new RegexPatternSearcherImpl();

    private PhrasePreprocessor prepositionPhrasePreprocessor = new PrepositionPhrasePreprocessorImpl(regexPatternSearcher);

    private PhrasePreprocessor nounPhrasePreprocessor = new NounPhrasePreprocessorImpl(regexPatternSearcher);

    private PhrasePreprocessor verbPhrasePreprocessor = new VerbPhrasePreprocessorImpl(regexPatternSearcher);

    private SemanticPreprocessor semanticPreprocessor = new SemanticPreprocessorImpl(prepositionPhrasePreprocessor, nounPhrasePreprocessor, verbPhrasePreprocessor);


    @Test
    public void testNoPrepositionPhrases(){
        String sentencePattern = "JNVANN";

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);

        String sentence = "brave firemen fight furiously forest fire";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(sentencePattern, tokens, encodedTags);
        assertEquals("JN", semanticPreprocessingData.getBeforeVerbNounPhrase().getPattern());
        assertEquals("NN", semanticPreprocessingData.getAfterVerbNounPhrase().getPattern());
        assertEquals("VA", semanticPreprocessingData.getVerbPhrase().getPattern());
    }

    @Test
    public void testBeforeAndAfterPrepositionPhrases(){
        String sentencePattern = "NPNPNVANNPNN";

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);


        String sentence = "Fans of Russia in Paris attack furiosly English supporters at European Championships";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(sentencePattern, tokens, encodedTags);
        assertTrue(semanticPreprocessingData.containsBeforeVerbPrepositionPhrase());
        assertTrue(semanticPreprocessingData.containsAfterVerbPrepositionPhrase());

        assertEquals("NPNPN", semanticPreprocessingData.getBeforeVerbPrepositionPhrase().getPattern());
        assertEquals("NNPNN", semanticPreprocessingData.getAfterVerbPrepositionPhrase().getPattern());

    }

}