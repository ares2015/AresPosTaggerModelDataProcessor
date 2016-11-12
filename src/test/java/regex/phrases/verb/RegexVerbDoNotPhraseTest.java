package regex.phrases.verb;

import com.trainingdataprocessor.data.regex.RegexPatternData;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.regex.RegexPatternSearcherImpl;
import com.trainingdataprocessor.regex.RegexUtils;
import org.junit.Test;

import java.util.List;

import static com.trainingdataprocessor.regex.RegexExpressions.VERB_PHRASE_DONT;
import static com.trainingdataprocessor.regex.RegexExpressions.VERB_PHRASE_DO_NOT;
import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 10/21/2016.
 */
public class RegexVerbDoNotPhraseTest {

    private RegexPatternSearcher regexPatternSearcher = new RegexPatternSearcherImpl();

    private static final String regexType = "Verb do not phrase";

    @Test
    public void testFound(){
        List<RegexPatternData> regexPatternIndexFinderList = null;
        String sentence = "";
        String encodedPath = "";

        sentence = "Russian fans in Paris at Euro does not furiously attack fans of English team";
        encodedPath = "NNPNPNLOAVNPNN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, VERB_PHRASE_DO_NOT);
        assertTrue(regexPatternIndexFinderList.size() > 0);
        System.out.println(sentence);
        assertTrue(RegexUtils.containsRegex(regexPatternIndexFinderList, "LOAV", regexType));

    }

    @Test
    public void testNotFound(){
        List<RegexPatternData> regexPatternIndexFinderList = null;
        String sentence = "";
        String encodedPath = "";

        sentence = "Russian fans in Paris at Euro furiously attack fans of English team";
        encodedPath = "NNPNPNAVNPNN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, VERB_PHRASE_DONT);
        assertTrue(regexPatternIndexFinderList.size() == 0);
        System.out.println(sentence +  " -> " + regexType + " NOT FOUND");

    }
}
