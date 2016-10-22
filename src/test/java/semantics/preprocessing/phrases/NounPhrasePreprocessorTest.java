package semantics.preprocessing.phrases;

import com.trainingdataprocessor.data.RegexPatternData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.regex.RegexPatternSearcherImpl;
import com.trainingdataprocessor.semantics.preprocessing.phrases.PhrasePreprocessor;
import com.trainingdataprocessor.semantics.preprocessing.phrases.noun.NounPhrasePreprocessorImpl;
import com.trainingdataprocessor.semantics.preprocessing.phrases.preposition.PrepositionPhrasePreprocessorImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 10/21/2016.
 */
public class NounPhrasePreprocessorTest {

    private RegexPatternSearcher regexPatternSearcher = new RegexPatternSearcherImpl();

    private PhrasePreprocessor nounPhrasePreprocessor = new NounPhrasePreprocessorImpl(regexPatternSearcher);

    private PhrasePreprocessor prepositionPhrasePreprocessor = new PrepositionPhrasePreprocessorImpl(regexPatternSearcher);

    @Test
    public void test(){
        List<RegexPatternData> regexPatternIndexFinderList = null;
        String sentence = "";
        String sentencePattern = "";

        sentence = "Aggressive stupid Russian fans in Paris at Euro furiosly attack English team";
        sentencePattern = "JJNNPNPNAVNN";
        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();
        semanticPreprocessingData.setVerbIndex(9);
        semanticPreprocessingData.setContainsAfterVerbPreposition(false);

        prepositionPhrasePreprocessor.preprocess(sentencePattern, semanticPreprocessingData);
        nounPhrasePreprocessor.preprocess(sentencePattern, semanticPreprocessingData);
        assertTrue(semanticPreprocessingData.containsBeforeVerbPrepositionPhrase());
        assertFalse(semanticPreprocessingData.containsBeforeVerbNounPhrase());
        assertTrue(semanticPreprocessingData.containsAfterVerbNounPhrase());
        assertEquals("NN", semanticPreprocessingData.getAfterVerbNounPhrase().getPattern());

    }

}