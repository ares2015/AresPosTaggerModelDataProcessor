package regex.phrases.noun;

import com.trainingdataprocessor.data.RegexPatternData;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.regex.RegexPatternSearcherImpl;
import com.trainingdataprocessor.regex.RegexUtils;
import org.junit.Test;

import java.util.List;

import static com.trainingdataprocessor.regex.RegexExpressions.NOUN_PHRASE;
import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 10/20/2016.
 */
public class RegexNounPhraseTest {

    private RegexPatternSearcher regexPatternSearcher = new RegexPatternSearcherImpl();

    private static final String regexType = "Preposition phrase";

    @Test
    public void testFound(){
        List<RegexPatternData> regexPatternIndexFinderList = null;
        String sentence = "";
        String encodedPath = "";

        sentence = "Aggressive stupid Russian fans in Paris at Euro attack fans of English team";
        encodedPath = "JJNNPNPNVNPNN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_PHRASE);
        assertTrue(regexPatternIndexFinderList.size() > 0);
        System.out.println(sentence);
        assertTrue(RegexUtils.containsRegex(regexPatternIndexFinderList, "JJNN", regexType));
        assertTrue(RegexUtils.containsRegex(regexPatternIndexFinderList, "N", regexType));
        assertTrue(RegexUtils.containsRegex(regexPatternIndexFinderList, "NN", regexType));

    }
}
