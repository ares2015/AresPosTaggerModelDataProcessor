package regex.phrases.verb;

import com.trainingdataprocessor.data.RegexPatternData;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.regex.RegexPatternSearcherImpl;
import com.trainingdataprocessor.regex.RegexUtils;
import org.junit.Test;

import java.util.List;

import static com.trainingdataprocessor.regex.RegexExpressions.PREPOSITION_PHRASE;
import static com.trainingdataprocessor.regex.RegexExpressions.VERB_PHRASE;
import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 10/20/2016.
 */
public class RegexVerbPhraseTest {

    private RegexPatternSearcher regexPatternSearcher = new RegexPatternSearcherImpl();

    private static final String regexType = "Verb phrase";

    @Test
    public void testFound(){
        List<RegexPatternData> regexPatternIndexFinderList = null;
        String sentence = "";
        String encodedPath = "";

        sentence = "Aggressive stupid Russian fans in Paris at Euro furiously attack fans of English team";
        encodedPath = "JJNNPNPNAVNPNN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, VERB_PHRASE);
        assertTrue(regexPatternIndexFinderList.size() > 0);
        System.out.println(sentence);
        assertTrue(RegexUtils.containsRegex(regexPatternIndexFinderList, "AV", regexType));

        sentence = "dog is best friend of man";
        encodedPath = "NIJNPN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, VERB_PHRASE);
        assertTrue(regexPatternIndexFinderList.size() > 0);
        System.out.println(sentence);
        assertTrue(RegexUtils.containsRegex(regexPatternIndexFinderList, "I", regexType));

    }


}
