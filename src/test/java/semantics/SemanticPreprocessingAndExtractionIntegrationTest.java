package semantics;

import com.trainingdataprocessor.cache.SemanticAnalysisFilterCache;
import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.regex.RegexPatternSearcherImpl;
import com.trainingdataprocessor.semantics.extraction.SemanticExtractor;
import com.trainingdataprocessor.semantics.extraction.SemanticExtractorImpl;
import com.trainingdataprocessor.semantics.extraction.phrases.NounPhraseExtractorImpl;
import com.trainingdataprocessor.semantics.extraction.phrases.PhraseExtractor;
import com.trainingdataprocessor.semantics.extraction.phrases.PrepositionPhraseExtractorImpl;
import com.trainingdataprocessor.semantics.extraction.phrases.VerbPhraseExtractorImpl;
import com.trainingdataprocessor.semantics.preprocessing.SemanticPreprocessingFilter;
import com.trainingdataprocessor.semantics.preprocessing.SemanticPreprocessingFilterImpl;
import com.trainingdataprocessor.semantics.preprocessing.SemanticPreprocessor;
import com.trainingdataprocessor.semantics.preprocessing.SemanticPreprocessorImpl;
import com.trainingdataprocessor.semantics.preprocessing.phrases.NounPhrasePreprocessorImpl;
import com.trainingdataprocessor.semantics.preprocessing.phrases.PhrasePreprocessor;
import com.trainingdataprocessor.semantics.preprocessing.phrases.PrepositionPhrasePreprocessorImpl;
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
 * Created by Oliver on 10/16/2016.
 */
public class SemanticPreprocessingAndExtractionIntegrationTest {
    private Tokenizer tokenizer = new TokenizerImpl();
    private SemanticAnalysisFilterCache semanticAnalysisFilterCache = new SemanticAnalysisFilterCache();
    private SemanticPreprocessingFilter semanticPreprocessingFilter = new SemanticPreprocessingFilterImpl();
    private RegexPatternSearcher regexPatternSearcher = new RegexPatternSearcherImpl();
    private PhrasePreprocessor prepositionPhrasePreprocessor = new PrepositionPhrasePreprocessorImpl(regexPatternSearcher);
    private PhrasePreprocessor nounPhrasePreprocessor = new NounPhrasePreprocessorImpl(regexPatternSearcher);
    private PhrasePreprocessor verbPhrasePreprocessor = new VerbPhrasePreprocessorImpl(regexPatternSearcher);
    private SemanticPreprocessor semanticPreprocessor = new SemanticPreprocessorImpl(prepositionPhrasePreprocessor, nounPhrasePreprocessor, verbPhrasePreprocessor);
    private PhraseExtractor prepositionPhraseExtractor = new PrepositionPhraseExtractorImpl(tokenizer);
    private PhraseExtractor nounPhraseExtractor = new NounPhraseExtractorImpl(tokenizer);
    private PhraseExtractor verbPhraseExtractor = new VerbPhraseExtractorImpl(tokenizer);
    private SemanticExtractor semanticExtractor = new SemanticExtractorImpl(prepositionPhraseExtractor, nounPhraseExtractor, verbPhraseExtractor);

    @Test
    public void test() {
        String encodedPath = "";
        List<String> tokens = null;
        List<String> encodedTags = null;
        String sentence = "";
        SemanticPreprocessingData semanticPreprocessingData = null;
        SemanticExtractionData semanticExtractionData = null;
        /*********************************************************************************************/
        encodedPath = "JNVANN";
        encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        sentence = "brave firemen fight furiously forest fire";
        tokens = Arrays.asList(sentence.split("\\ "));
        semanticPreprocessingData = semanticPreprocessor.preprocess(encodedPath, tokens, encodedTags, 2);
        semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("brave firemen ", semanticExtractionData.getExtendedSubject());
        assertEquals("firemen", semanticExtractionData.getAtomicSubject());
        assertEquals("fight", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("fight furiously ", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("forest fire ", semanticExtractionData.getExtendedNounPredicate());
        assertEquals("fire", semanticExtractionData.getAtomicNounPredicate());
        /*********************************************************************************************/
        encodedPath = "JN<NVANN";
        encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.AND_OR);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        sentence = "brave soldiers and firemen fight furiously forest fire";
        tokens = Arrays.asList(sentence.split("\\ "));
        semanticPreprocessingData = semanticPreprocessor.preprocess(encodedPath, tokens, encodedTags, 4);
        semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("", semanticExtractionData.getExtendedSubject());
        assertEquals("firemen", semanticExtractionData.getAtomicSubject());
        assertEquals("fight", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("fight furiously ", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("forest fire ", semanticExtractionData.getExtendedNounPredicate());
        assertEquals("fire", semanticExtractionData.getAtomicNounPredicate());
        /*********************************************************************************************/
        encodedPath = "NPNPNVANNPNN";
        encodedTags = new ArrayList<String>();
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
        sentence = "Fans of Russia in Paris attack furiously English supporters at European Championships";
        tokens = Arrays.asList(sentence.split("\\ "));
        semanticPreprocessingData = semanticPreprocessor.preprocess(encodedPath, tokens, encodedTags, 5);
        semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("Fans of Russia in Paris ", semanticExtractionData.getExtendedSubject());
        assertEquals("attack", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("attack furiously ", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("English supporters at European Championships ", semanticExtractionData.getExtendedNounPredicate());
        assertEquals("supporters", semanticExtractionData.getAtomicNounPredicate());
        /*********************************************************************************************/
        encodedPath = "NVANPNNN";
        encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        sentence = "George exercised little control over British domestic policy";
        tokens = Arrays.asList(sentence.split("\\ "));
        semanticPreprocessingData = semanticPreprocessor.preprocess(encodedPath, tokens, encodedTags, 1);
        semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("George", semanticExtractionData.getAtomicSubject());
        assertEquals("", semanticExtractionData.getExtendedSubject());
        assertEquals("exercised", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("exercised little ", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("control over British domestic policy ", semanticExtractionData.getExtendedNounPredicate());
        assertEquals("control", semanticExtractionData.getAtomicNounPredicate());
        /*********************************************************************************************/
        encodedPath = "NNVNAPNPN#";
        encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NUMBER);
        sentence = "King George visited Hanover again from May to November 1719";
        tokens = Arrays.asList(sentence.split("\\ "));
        semanticPreprocessingData = semanticPreprocessor.preprocess(encodedPath, tokens, encodedTags, 2);
        semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("George", semanticExtractionData.getAtomicSubject());
        assertEquals("King George ", semanticExtractionData.getExtendedSubject());
        assertEquals("visited", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("Hanover again from May to November 1719 ", semanticExtractionData.getExtendedNounPredicate());
        assertEquals("Hanover", semanticExtractionData.getAtomicNounPredicate());
        /*********************************************************************************************/
        encodedPath = "$NNIAAJNPNN";
        encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.VERB_ED);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.IS_ARE);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);

        sentence = "Shot John Lennon is still very popular singer in United States";
        tokens = Arrays.asList(sentence.split("\\ "));
        semanticPreprocessingData = semanticPreprocessor.preprocess(encodedPath, tokens, encodedTags, 3);
        semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("Lennon", semanticExtractionData.getAtomicSubject());
        assertEquals("Shot John Lennon ", semanticExtractionData.getExtendedSubject());
        assertEquals("is", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("is still very ", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("popular singer in United States ", semanticExtractionData.getExtendedNounPredicate());
        assertEquals("singer", semanticExtractionData.getAtomicNounPredicate());

        /*********************************************************************************************/
        encodedPath = "NPNMVAAPN";
        encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.MODAL_VERB);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);


        sentence = "Members of Fragile can sing absolutely perfectly before audience";
        tokens = Arrays.asList(sentence.split("\\ "));
        semanticPreprocessingData = semanticPreprocessor.preprocess(encodedPath, tokens, encodedTags, 4);
        semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("Members of Fragile ", semanticExtractionData.getExtendedSubject());
        assertEquals("sing", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("can sing absolutely perfectly ", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("absolutely perfectly before audience ", semanticExtractionData.getExtendedNounPredicate());
        assertEquals("", semanticExtractionData.getAtomicNounPredicate());

        /*********************************************************************************************/
        encodedPath = "NIJPN";
        encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.IS_ARE);
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);


        sentence = "Bob is alone at home";
        tokens = Arrays.asList(sentence.split("\\ "));
        semanticPreprocessingData = semanticPreprocessor.preprocess(encodedPath, tokens, encodedTags, 1);
        semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("", semanticExtractionData.getExtendedSubject());
        assertEquals("Bob", semanticExtractionData.getAtomicSubject());
        assertEquals("is", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("alone at home ", semanticExtractionData.getExtendedNounPredicate());
        assertEquals("alone", semanticExtractionData.getAtomicNounPredicate());

        /*********************************************************************************************/
        encodedPath = "DQNVN";
        encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.DETERMINER);
        encodedTags.add(EncodedTags.QUANTIFIER);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.NOUN);

        sentence = "a few scholars defend Louis";
        tokens = Arrays.asList(sentence.split("\\ "));
        semanticPreprocessingData = semanticPreprocessor.preprocess(encodedPath, tokens, encodedTags, 3);
        semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("a few scholars ", semanticExtractionData.getExtendedSubject());
        assertEquals("scholars", semanticExtractionData.getAtomicSubject());
        assertEquals("defend", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("", semanticExtractionData.getExtendedNounPredicate());
        assertEquals("Louis", semanticExtractionData.getAtomicNounPredicate());

        /*********************************************************************************************/

        encodedPath = "NVTN";
        encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.TO);
        encodedTags.add(EncodedTags.NOUN);


        sentence = "Raphael moved to Rome";
        tokens = Arrays.asList(sentence.split("\\ "));
        semanticPreprocessingData = semanticPreprocessor.preprocess(encodedPath, tokens, encodedTags, 1);
        semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("", semanticExtractionData.getExtendedSubject());
        assertEquals("Raphael", semanticExtractionData.getAtomicSubject());
        assertEquals("moved", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("to Rome ", semanticExtractionData.getExtendedNounPredicate());
        assertEquals("", semanticExtractionData.getAtomicNounPredicate());

        /*********************************************************************************************/
        encodedPath = "J$NPJNIP#NAPN";
        encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.VERB_ED);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.IS_ARE);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NUMBER);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);

        sentence = "earliest known fossil of domestic dog is from 31700 years ago in Belgium";
        tokens = Arrays.asList(sentence.split("\\ "));
        semanticPreprocessingData = semanticPreprocessor.preprocess(encodedPath, tokens, encodedTags, 6);
        semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("earliest known fossil of domestic dog ", semanticExtractionData.getExtendedSubject());
        assertEquals("", semanticExtractionData.getAtomicSubject());
        assertEquals("is", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("from 31700 years ago in Belgium ", semanticExtractionData.getExtendedNounPredicate());
        assertEquals("", semanticExtractionData.getAtomicNounPredicate());


        /*********************************************************************************************/
        sentence = "oldest material found in Solar System is dated to 4.5672 billion years ago";

        encodedPath = "JN$PNNI$T##NA";
        encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.VERB_ED);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.IS_ARE);
        encodedTags.add(EncodedTags.VERB_ED);
        encodedTags.add(EncodedTags.TO);
        encodedTags.add(EncodedTags.NUMBER);
        encodedTags.add(EncodedTags.NUMBER);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.ADVERB);
        tokens = Arrays.asList(sentence.split("\\ "));
        semanticPreprocessingData = semanticPreprocessor.preprocess(encodedPath, tokens, encodedTags, 6);
        semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("oldest material found in Solar System ", semanticExtractionData.getExtendedSubject());
        assertEquals("", semanticExtractionData.getAtomicSubject());
        assertEquals("is", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("dated to 4.5672 billion years ago ", semanticExtractionData.getExtendedNounPredicate());
        assertEquals("dated", semanticExtractionData.getAtomicNounPredicate());




    }
}