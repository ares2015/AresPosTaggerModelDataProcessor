package semantics.analysis;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.regex.RegexPatternSearcherImpl;
import com.trainingdataprocessor.semantics.analysis.SemanticAnalysisExecutor;
import com.trainingdataprocessor.semantics.analysis.SemanticAnalysisExecutorImpl;
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
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Oliver on 12/7/2016.
 */
public class SemanticAnalysisExecutorTest {

    private Tokenizer tokenizer = new TokenizerImpl();
    private SemanticPreprocessingFilter semanticPreprocessingFilter = new SemanticPreprocessingFilterImpl();
    private RegexPatternSearcher regexPatternSearcher = new RegexPatternSearcherImpl();
    private PhrasePreprocessor prepositionPhrasePreprocessor = new PrepositionPhrasePreprocessorImpl(regexPatternSearcher);
    private PhrasePreprocessor nounPhrasePreprocessor = new NounPhrasePreprocessorImpl(regexPatternSearcher);
    private PhrasePreprocessor verbPhrasePreprocessor = new VerbPhrasePreprocessorImpl(regexPatternSearcher);
    private SemanticPreprocessor semanticPreprocessor = new SemanticPreprocessorImpl(semanticPreprocessingFilter, prepositionPhrasePreprocessor, nounPhrasePreprocessor, verbPhrasePreprocessor);
    private PhraseExtractor prepositionPhraseExtractor = new PrepositionPhraseExtractorImpl(tokenizer);
    private PhraseExtractor nounPhraseExtractor = new NounPhraseExtractorImpl(tokenizer);
    private PhraseExtractor verbPhraseExtractor = new VerbPhraseExtractorImpl(tokenizer);
    private SemanticExtractor semanticExtractor = new SemanticExtractorImpl(prepositionPhraseExtractor, nounPhraseExtractor, verbPhraseExtractor);
    private SemanticAnalysisExecutor semanticAnalysisExecutor = new SemanticAnalysisExecutorImpl(semanticPreprocessor, semanticExtractor);


    @Test
    public void test() throws InterruptedException {
        String encodedPath = "NVNAPNPN#";
        String sentence = "George visited Hanover again from May to November 1719";
        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NUMBER);
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        List<TrainingDataRow> trainingDataRowList = new ArrayList<>();
        TrainingDataRow trainingDataRow = new TrainingDataRow();
        trainingDataRow.setEncodedPathAsString(encodedPath);
        trainingDataRow.setEncodedTagsList(encodedTags);
        trainingDataRow.setTokensList(tokens);

        trainingDataRowList.add(trainingDataRow);

        Optional<SemanticExtractionData> semanticExtractionData = semanticAnalysisExecutor.execute(tokens, encodedTags, 1);
        assertEquals("George", semanticExtractionData.get().getAtomicSubject());
        assertEquals("visited", semanticExtractionData.get().getAtomicVerbPredicate());
        assertEquals("Hanover", semanticExtractionData.get().getAtomicNounPredicate());
        assertEquals("Hanover again from May to November 1719 ", semanticExtractionData.get().getExtendedNounPredicate());

    }

    @Test
    public void testWithModalVerb(){
        String encodedPath = "NNMVAA";
        String sentence = "Jamaica people can run really quickly";
        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.MODAL_VERB);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.ADVERB);

        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        TrainingDataRow trainingDataRow = new TrainingDataRow();
        trainingDataRow.setEncodedPathAsString(encodedPath);
        trainingDataRow.setEncodedTagsList(encodedTags);
        trainingDataRow.setTokensList(tokens);

        List<TrainingDataRow> trainingDataRowList = new ArrayList<>();
        trainingDataRowList.add(trainingDataRow);

        Optional<SemanticExtractionData> semanticExtractionData = semanticAnalysisExecutor.execute(tokens, encodedTags, 3);
        assertEquals("people", semanticExtractionData.get().getAtomicSubject());
        assertEquals("Jamaica people ", semanticExtractionData.get().getExtendedSubject());
        assertEquals("run", semanticExtractionData.get().getAtomicVerbPredicate());
        assertEquals("can run really quickly ", semanticExtractionData.get().getExtendedVerbPredicate());

    }

    @Test
    public void testWithNotModalVerb(){
        String encodedPath = "NNMOVNPN";
        String sentence = "Slovak players could not score goal against Russia";
        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.MODAL_VERB);
        encodedTags.add(EncodedTags.NOT);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);

        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        TrainingDataRow trainingDataRow = new TrainingDataRow();
        trainingDataRow.setEncodedPathAsString(encodedPath);
        trainingDataRow.setEncodedTagsList(encodedTags);
        trainingDataRow.setTokensList(tokens);

        List<TrainingDataRow> trainingDataRowList = new ArrayList<>();
        trainingDataRowList.add(trainingDataRow);

        Optional<SemanticExtractionData> semanticExtractionData = semanticAnalysisExecutor.execute(tokens, encodedTags, 4);
        assertEquals("players", semanticExtractionData.get().getAtomicSubject());
        assertEquals("Slovak players ", semanticExtractionData.get().getExtendedSubject());
        assertEquals("score", semanticExtractionData.get().getAtomicVerbPredicate());
        assertEquals("could not score ", semanticExtractionData.get().getExtendedVerbPredicate());
        assertEquals("goal", semanticExtractionData.get().getAtomicNounPredicate());
        assertEquals("goal against Russia ", semanticExtractionData.get().getExtendedNounPredicate());
    }

    @Test
    public void testWithNotModalVerbBeforeVerbPrepositionPhrase(){
        String encodedPath = "NPNNMOVNPN";
        String sentence = "Players of Slovak team could not score goal against Russia";
        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.MODAL_VERB);
        encodedTags.add(EncodedTags.NOT);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);

        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        TrainingDataRow trainingDataRow = new TrainingDataRow();
        trainingDataRow.setEncodedPathAsString(encodedPath);
        trainingDataRow.setEncodedTagsList(encodedTags);
        trainingDataRow.setTokensList(tokens);

        List<TrainingDataRow> trainingDataRowList = new ArrayList<>();
        trainingDataRowList.add(trainingDataRow);

        Optional<SemanticExtractionData> semanticExtractionData = semanticAnalysisExecutor.execute(tokens, encodedTags, 6);
        assertEquals("", semanticExtractionData.get().getAtomicSubject());
        assertEquals("Players of Slovak team ", semanticExtractionData.get().getExtendedSubject());
        assertEquals("score", semanticExtractionData.get().getAtomicVerbPredicate());
        assertEquals("could not score ", semanticExtractionData.get().getExtendedVerbPredicate());
        assertEquals("goal", semanticExtractionData.get().getAtomicNounPredicate());
        assertEquals("goal against Russia ", semanticExtractionData.get().getExtendedNounPredicate());
    }

    @Test
    public void testNotExtracted() throws InterruptedException {
        String encodedPath = "VA";
        String sentence = "run quickly";
        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.ADVERB);
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        List<TrainingDataRow> trainingDataRowList = new ArrayList<>();
        TrainingDataRow trainingDataRow = new TrainingDataRow();
        trainingDataRow.setEncodedPathAsString(encodedPath);
        trainingDataRow.setEncodedTagsList(encodedTags);
        trainingDataRow.setTokensList(tokens);

        trainingDataRowList.add(trainingDataRow);

        Optional<SemanticExtractionData> semanticExtractionData = semanticAnalysisExecutor.execute(tokens, encodedTags, 0);
        assertFalse(semanticExtractionData.isPresent());

    }

}
