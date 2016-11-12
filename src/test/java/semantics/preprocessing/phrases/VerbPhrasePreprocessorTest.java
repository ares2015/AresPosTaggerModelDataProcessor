package semantics.preprocessing.phrases;

import com.trainingdataprocessor.data.regex.RegexPatternData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.regex.RegexPatternSearcherImpl;
import com.trainingdataprocessor.semantics.preprocessing.phrases.PhrasePreprocessor;
import com.trainingdataprocessor.semantics.preprocessing.phrases.VerbPhrasePreprocessorImpl;
import com.trainingdataprocessor.semantics.preprocessing.phrases.VerbPhraseTypes;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oliver on 10/21/2016.
 */
public class VerbPhrasePreprocessorTest {

    private RegexPatternSearcher regexPatternSearcher = new RegexPatternSearcherImpl();

    private PhrasePreprocessor verbPhrasePreprocessor = new VerbPhrasePreprocessorImpl(regexPatternSearcher);

    @Test
    public void testVerbPhrase() {

        List<RegexPatternData> regexPatternIndexFinderList = null;
        String sentence = "";
        String sentencePattern = "";

        sentence = "Aggressive stupid Russian fans in Paris at Euro furiosly attack fans of English team";
        sentencePattern = "JJNNPNPNAVNPNN";
        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();
        semanticPreprocessingData.setVerbIndex(9);

        verbPhrasePreprocessor.preprocess(sentencePattern, semanticPreprocessingData);
        assertEquals("AV", semanticPreprocessingData.getVerbPhrase().getPattern());
        assertEquals(VerbPhraseTypes.VERB_PHRASE, VerbPhraseTypes.VERB_PHRASE);

    }

    @Test
    public void testModalVerbPhrase() {

        List<RegexPatternData> regexPatternIndexFinderList = null;
        String sentence = "";
        String sentencePattern = "";

        sentence = "Johny and Bobby can play guitar";
        sentencePattern = "N<NMVN";
        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();

        verbPhrasePreprocessor.preprocess(sentencePattern, semanticPreprocessingData);
        assertEquals("MV", semanticPreprocessingData.getVerbPhrase().getPattern());
        assertEquals(VerbPhraseTypes.MODAL_VERB_PHRASE, VerbPhraseTypes.MODAL_VERB_PHRASE);

    }

    @Test
    public void testVerbWithVerbConjunctionPhrase() {

        List<RegexPatternData> regexPatternIndexFinderList = null;
        String sentence = "";
        String sentencePattern = "";

        sentence = "Johny and Bobby play and sing";
        sentencePattern = "N<NV<V";
        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();

        verbPhrasePreprocessor.preprocess(sentencePattern, semanticPreprocessingData);
        assertEquals("V", semanticPreprocessingData.getVerbPhrase().getPattern());
        assertEquals(VerbPhraseTypes.VERB_PHRASE, VerbPhraseTypes.VERB_PHRASE);

    }

    @Test
    public void testModalVerbNotPhrase() {

        List<RegexPatternData> regexPatternIndexFinderList = null;
        String sentence = "";
        String sentencePattern = "";

        sentence = "Johny and Bobby can not play guitar";
        sentencePattern = "N<NMOVN";
        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();

        verbPhrasePreprocessor.preprocess(sentencePattern, semanticPreprocessingData);
        assertEquals("MOV", semanticPreprocessingData.getVerbPhrase().getPattern());
        assertEquals(VerbPhraseTypes.MODAL_VERB_NOT_PHRASE, VerbPhraseTypes.MODAL_VERB_NOT_PHRASE);

    }

    @Test
    public void testModalVerbNotWithVerbConjunctionPhrase() {

        List<RegexPatternData> regexPatternIndexFinderList = null;
        String sentence = "";
        String sentencePattern = "";

        sentence = "Johny and Bobby can not play and sing";
        sentencePattern = "N<NMOV<V";
        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();

        verbPhrasePreprocessor.preprocess(sentencePattern, semanticPreprocessingData);
        assertEquals("MOV", semanticPreprocessingData.getVerbPhrase().getPattern());
        assertEquals(VerbPhraseTypes.MODAL_VERB_NOT_PHRASE, VerbPhraseTypes.MODAL_VERB_NOT_PHRASE);

    }
}
