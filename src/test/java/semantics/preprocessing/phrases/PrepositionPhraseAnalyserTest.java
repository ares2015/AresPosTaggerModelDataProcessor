package semantics.preprocessing.phrases;

import com.trainingdataprocessor.data.RegexPatternData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.regex.RegexPatternSearcherImpl;
import com.trainingdataprocessor.semantics.preprocessing.phrases.PhraseAnalyser;
import com.trainingdataprocessor.semantics.preprocessing.phrases.preposition.PrepositionPhraseAnalyserImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 10/21/2016.
 */
public class PrepositionPhraseAnalyserTest {

    private RegexPatternSearcher regexPatternSearcher = new RegexPatternSearcherImpl();

    private PhraseAnalyser prepositionPhraseAnalyser = new PrepositionPhraseAnalyserImpl(regexPatternSearcher);

    @Test
    public void test() {

        List<RegexPatternData> regexPatternIndexFinderList = null;
        String sentence = "";
        String sentencePattern = "";

        sentence = "Aggressive stupid Russian fans in Paris at Euro attack fans of English team";
        sentencePattern = "JJNNPNPNVNPNN";
        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();
        semanticPreprocessingData.setVerbIndex(8);

        prepositionPhraseAnalyser.analyse(sentencePattern, semanticPreprocessingData);
        assertTrue(semanticPreprocessingData.containsBeforeVerbPrepositionPhrase());
        assertTrue(semanticPreprocessingData.containsAfterVerbPrepositionPhrase());

    }


}