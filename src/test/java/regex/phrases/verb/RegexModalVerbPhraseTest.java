package regex.phrases.verb;

import com.trainingdataprocessor.data.regex.RegexPatternData;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.regex.RegexPatternSearcherImpl;
import com.trainingdataprocessor.regex.RegexUtils;
import org.junit.Test;

import java.util.List;

import static com.trainingdataprocessor.regex.RegexExpressions.MODAL_VERB_PHRASE;
import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 10/20/2016.
 */
public class RegexModalVerbPhraseTest {

    private RegexPatternSearcher regexPatternSearcher = new RegexPatternSearcherImpl();

    private static final String regexType = "Modal verb phrase";

    @Test
    public void testFound() {

        List<RegexPatternData> regexPatternIndexFinderList = null;
        String sentence = "";
        String encodedPath = "";

        sentence = "Johny can play guitar absolutely perfectly.";
        encodedPath = "NMVNAA";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, MODAL_VERB_PHRASE);
        assertTrue(regexPatternIndexFinderList.size() > 0);
        System.out.println(sentence);
        assertTrue(RegexUtils.containsRegex(regexPatternIndexFinderList, "MV", regexType));

        sentence = "Johny can sing absolutely perfectly.";
        encodedPath = "NMVAA";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, MODAL_VERB_PHRASE);
        assertTrue(regexPatternIndexFinderList.size() > 0);
        System.out.println(sentence);
        assertTrue(RegexUtils.containsRegex(regexPatternIndexFinderList, "MVAA", regexType));

    }

    @Test
    public void testNotFound(){
        List<RegexPatternData> regexPatternIndexFinderList = null;
        String sentence = "";
        String encodedPath = "";

        sentence = "Johny plays guitar absolutely perfectly.";
        encodedPath = "NVNAA";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, MODAL_VERB_PHRASE);
        assertTrue(regexPatternIndexFinderList.size() == 0);
        System.out.println(sentence +  " -> " + regexType + " NOT FOUND");

        sentence = "Johny sings absolutely perfectly.";
        encodedPath = "NVAA";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, MODAL_VERB_PHRASE);
        assertTrue(regexPatternIndexFinderList.size() == 0);
        System.out.println(sentence +  " -> " + regexType + " NOT FOUND");

    }

}




