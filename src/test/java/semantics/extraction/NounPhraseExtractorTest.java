package semantics.extraction;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.regex.RegexPatternSearcherImpl;
import com.trainingdataprocessor.semantics.extraction.phrases.NounPhraseExtractorImpl;
import com.trainingdataprocessor.semantics.extraction.phrases.PhraseExtractor;
import com.trainingdataprocessor.semantics.extraction.phrases.PrepositionPhraseExtractorImpl;
import com.trainingdataprocessor.semantics.preprocessing.phrases.NounPhrasePreprocessorImpl;
import com.trainingdataprocessor.semantics.preprocessing.phrases.PhrasePreprocessor;
import com.trainingdataprocessor.semantics.preprocessing.phrases.PrepositionPhrasePreprocessorImpl;
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
public class NounPhraseExtractorTest {

    private RegexPatternSearcher regexPatternSearcher = new RegexPatternSearcherImpl();

    private PhrasePreprocessor prepositionPhrasePreprocessor = new PrepositionPhrasePreprocessorImpl(regexPatternSearcher);

    private PhrasePreprocessor nounPhrasePreprocessor = new NounPhrasePreprocessorImpl(regexPatternSearcher);

    private Tokenizer tokenizer = new TokenizerImpl();

    private PhraseExtractor prepositionPhraseExtractor = new PrepositionPhraseExtractorImpl(tokenizer);

    private PhraseExtractor nounPhraseExtractor = new NounPhraseExtractorImpl(tokenizer);

    @Test
    public void testSimpleNounVerbNoun(){
        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();
        String sentencePattern = "NVN";

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.NOUN);

        String sentence = "boys drink beer";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        semanticPreprocessingData.setTokens(tokens);
        semanticPreprocessingData.setEncodedTags(encodedTags);
        semanticPreprocessingData.setVerbIndex(1);

        nounPhrasePreprocessor.preprocess(sentencePattern, semanticPreprocessingData);

        SemanticExtractionData semanticExtractionData = new SemanticExtractionData();

        nounPhraseExtractor.extract(semanticPreprocessingData, semanticExtractionData);

        assertEquals("", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("boys", semanticExtractionData.getAtomicSubject());
        assertEquals("", semanticExtractionData.getExtendedSubject());
        assertEquals("beer", semanticExtractionData.getAtomicNounPredicate());
        assertEquals("", semanticExtractionData.getExtendedNounPredicate());

    }

    @Test
    public void testBeforeAndAfterPrepositionPhrases(){
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
        semanticPreprocessingData.setContainsAfterVerbPreposition(true);

        prepositionPhrasePreprocessor.preprocess(sentencePattern, semanticPreprocessingData);
        nounPhrasePreprocessor.preprocess(sentencePattern, semanticPreprocessingData);

        SemanticExtractionData semanticExtractionData = new SemanticExtractionData();

        prepositionPhraseExtractor.extract(semanticPreprocessingData, semanticExtractionData);
        nounPhraseExtractor.extract(semanticPreprocessingData, semanticExtractionData);


        assertEquals("Fans of Russia in Paris ", semanticExtractionData.getExtendedSubject());
        assertEquals("English supporters at European Championships in France ", semanticExtractionData.getExtendedNounPredicate());
        assertEquals("supporters", semanticExtractionData.getAtomicNounPredicate());

    }

    @Test
    public void testNoPrepositionPhrases(){
        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();
        String sentencePattern = "JNNVANN";

        List<String> encodedTags = new ArrayList<String>();

        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);

        String sentence = "Stupid Russian fans attack furiosly English supporters";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        semanticPreprocessingData.setTokens(tokens);
        semanticPreprocessingData.setEncodedTags(encodedTags);
        semanticPreprocessingData.setVerbIndex(3);
        semanticPreprocessingData.setContainsAfterVerbPreposition(false);

        prepositionPhrasePreprocessor.preprocess(sentencePattern, semanticPreprocessingData);
        nounPhrasePreprocessor.preprocess(sentencePattern, semanticPreprocessingData);

        SemanticExtractionData semanticExtractionData = new SemanticExtractionData();

        prepositionPhraseExtractor.extract(semanticPreprocessingData, semanticExtractionData);
        nounPhraseExtractor.extract(semanticPreprocessingData, semanticExtractionData);

        assertEquals("fans", semanticExtractionData.getAtomicSubject());
        assertEquals("Stupid Russian fans ", semanticExtractionData.getExtendedSubject());
        assertEquals("English supporters ", semanticExtractionData.getExtendedNounPredicate());
        assertEquals("supporters", semanticExtractionData.getAtomicNounPredicate());

    }


}
