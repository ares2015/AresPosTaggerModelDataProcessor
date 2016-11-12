package semantics.extraction;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.regex.RegexPatternSearcherImpl;
import com.trainingdataprocessor.semantics.extraction.phrases.PhraseExtractor;
import com.trainingdataprocessor.semantics.extraction.phrases.VerbPhraseExtractorImpl;
import com.trainingdataprocessor.semantics.preprocessing.phrases.PhrasePreprocessor;
import com.trainingdataprocessor.semantics.preprocessing.phrases.VerbPhrasePreprocessorImpl;
import com.trainingdataprocessor.tags.EncodedTags;
import com.trainingdataprocessor.tokens.Tokenizer;
import com.trainingdataprocessor.tokens.TokenizerImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oliver on 10/22/2016.
 */
public class VerbPhraseExtractorTest {

    private RegexPatternSearcher regexPatternSearcher = new RegexPatternSearcherImpl();

    private PhrasePreprocessor verbPhrasePreprocessor = new VerbPhrasePreprocessorImpl(regexPatternSearcher);

    private Tokenizer tokenizer = new TokenizerImpl();

    private PhraseExtractor verbPhraseExtractor = new VerbPhraseExtractorImpl(tokenizer);

    @Test
    public void testBasicVerbPhrase(){
        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();
        String sentencePattern = "NPNPNVANNPNNPN";

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
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);

        String sentence = "Fans of Russia in Paris attack furiosly English supporters at European Championships in France";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        semanticPreprocessingData.setTokens(tokens);
        semanticPreprocessingData.setEncodedTags(encodedTags);
        semanticPreprocessingData.setVerbIndex(5);
        semanticPreprocessingData.setAfterVerbFirstPrepositionIndex(9);

        verbPhrasePreprocessor.preprocess(sentencePattern, semanticPreprocessingData);

        SemanticExtractionData semanticExtractionData = new SemanticExtractionData();

        verbPhraseExtractor.extract(semanticPreprocessingData, semanticExtractionData);

        assertEquals("attack furiosly ", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("attack", semanticExtractionData.getAtomicVerbPredicate());

    }

    @Test
    public void testDidNotVerbPhrase(){
        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();
        String sentencePattern = "JNNLOVANN";

        List<String> encodedTags = new ArrayList<String>();

        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.DO);
        encodedTags.add(EncodedTags.NOT);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);

        String sentence = "Stupid Russian fans did not attack furiosly English supporters";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        semanticPreprocessingData.setTokens(tokens);
        semanticPreprocessingData.setEncodedTags(encodedTags);
        semanticPreprocessingData.setVerbIndex(5);
        semanticPreprocessingData.setContainsAfterVerbPreposition(false);

        verbPhrasePreprocessor.preprocess(sentencePattern, semanticPreprocessingData);

        SemanticExtractionData semanticExtractionData = new SemanticExtractionData();

        verbPhraseExtractor.extract(semanticPreprocessingData, semanticExtractionData);

        assertEquals("attack", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("did not attack furiosly ", semanticExtractionData.getExtendedVerbPredicate());

    }

    @Test
    public void testModalNotVerbPhrase(){
        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();
        String sentencePattern = "JNNMOVNPNN";

        List<String> encodedTags = new ArrayList<String>();

        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.MODAL_VERB);
        encodedTags.add(EncodedTags.NOT);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);


        String sentence = "Stupid Russian fans can not enter France during European championships";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        semanticPreprocessingData.setTokens(tokens);
        semanticPreprocessingData.setEncodedTags(encodedTags);
        semanticPreprocessingData.setVerbIndex(5);
        semanticPreprocessingData.setContainsAfterVerbPreposition(true);

        verbPhrasePreprocessor.preprocess(sentencePattern, semanticPreprocessingData);

        SemanticExtractionData semanticExtractionData = new SemanticExtractionData();

        verbPhraseExtractor.extract(semanticPreprocessingData, semanticExtractionData);

        assertEquals("enter", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("can not enter ", semanticExtractionData.getExtendedVerbPredicate());

    }

    @Test
    public void testISnotPhrase(){
        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();
        String sentencePattern = "NIOAJNPN";

        List<String> encodedTags = new ArrayList<String>();

        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.IS_ARE);
        encodedTags.add(EncodedTags.NOT);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);


        String sentence = "cat is not very best friend of man";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        semanticPreprocessingData.setTokens(tokens);
        semanticPreprocessingData.setEncodedTags(encodedTags);
        semanticPreprocessingData.setVerbIndex(1);
        semanticPreprocessingData.setContainsAfterVerbPreposition(false);

        verbPhrasePreprocessor.preprocess(sentencePattern, semanticPreprocessingData);

        SemanticExtractionData semanticExtractionData = new SemanticExtractionData();

        verbPhraseExtractor.extract(semanticPreprocessingData, semanticExtractionData);

        assertEquals("is", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("is not very ", semanticExtractionData.getExtendedVerbPredicate());

    }
}
