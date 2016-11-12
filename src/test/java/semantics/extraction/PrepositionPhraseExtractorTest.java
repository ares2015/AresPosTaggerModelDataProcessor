package semantics.extraction;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.regex.RegexPatternSearcherImpl;
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
 * Created by Oliver on 10/21/2016.
 */
public class PrepositionPhraseExtractorTest {

    private RegexPatternSearcher regexPatternSearcher = new RegexPatternSearcherImpl();

    private PhrasePreprocessor prepositionPhrasePreprocessor = new PrepositionPhrasePreprocessorImpl(regexPatternSearcher);

    private PhrasePreprocessor nounPhrasePreprocessor = new NounPhrasePreprocessorImpl(regexPatternSearcher);

    private Tokenizer tokenizer = new TokenizerImpl();

    private PhraseExtractor prepositionPhraseExtractor = new PrepositionPhraseExtractorImpl(tokenizer);

    @Test
    public void test(){
        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();
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

        semanticPreprocessingData.setTokens(tokens);
        semanticPreprocessingData.setEncodedTags(encodedTags);
        semanticPreprocessingData.setVerbIndex(5);
        semanticPreprocessingData.setAfterVerbFirstPrepositionIndex(9);

        prepositionPhrasePreprocessor.preprocess(sentencePattern, semanticPreprocessingData);
        nounPhrasePreprocessor.preprocess(sentencePattern, semanticPreprocessingData);

        SemanticExtractionData semanticExtractionData = new SemanticExtractionData();

        prepositionPhraseExtractor.extract(semanticPreprocessingData, semanticExtractionData);

        assertEquals("Fans of Russia in Paris ", semanticExtractionData.getExtendedSubject());
        assertEquals("English supporters at European Championships ", semanticExtractionData.getExtendedNounPredicate());

    }


    @Test
    public void testWithNot(){
        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();
        String sentencePattern = "NNNP#I$OPN";


        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NUMBER);
        encodedTags.add(EncodedTags.IS_ARE);
        encodedTags.add(EncodedTags.VERB_ED);
        encodedTags.add(EncodedTags.NOT);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);

        String sentence = "English Peasant Revolt of 1381 was led not by peasants";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        semanticPreprocessingData.setTokens(tokens);
        semanticPreprocessingData.setEncodedTags(encodedTags);
        semanticPreprocessingData.setVerbIndex(5);
        semanticPreprocessingData.setAfterVerbFirstPrepositionIndex(8);

        prepositionPhrasePreprocessor.preprocess(sentencePattern, semanticPreprocessingData);
        nounPhrasePreprocessor.preprocess(sentencePattern, semanticPreprocessingData);

        SemanticExtractionData semanticExtractionData = new SemanticExtractionData();

        prepositionPhraseExtractor.extract(semanticPreprocessingData, semanticExtractionData);

        assertEquals("English Peasant Revolt of 1381 ", semanticExtractionData.getExtendedSubject());
        assertEquals("led not by peasants ", semanticExtractionData.getExtendedNounPredicate());

    }

}
