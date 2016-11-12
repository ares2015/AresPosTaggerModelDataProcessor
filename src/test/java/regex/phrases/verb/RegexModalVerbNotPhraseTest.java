package regex.phrases.verb;

import com.trainingdataprocessor.data.regex.RegexPatternData;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.regex.RegexPatternSearcherImpl;
import com.trainingdataprocessor.regex.RegexUtils;
import org.junit.Test;

import java.util.List;

import static com.trainingdataprocessor.regex.RegexExpressions.MODAL_VERB_NOT_PHRASE;
import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 10/21/2016.
 */
public class RegexModalVerbNotPhraseTest {

    private RegexPatternSearcher regexPatternSearcher = new RegexPatternSearcherImpl();

    private static final String regexType = "Modal verb not phrase";

    @Test
    public void testFound() {

        List<RegexPatternData> regexPatternIndexFinderList = null;
        String sentence = "";
        String encodedPath = "";

        sentence = "Johny can not play guitar absolutely perfectly.";
        encodedPath = "NMOVNAA";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, MODAL_VERB_NOT_PHRASE);
        assertTrue(regexPatternIndexFinderList.size() > 0);
        System.out.println(sentence);
        assertTrue(RegexUtils.containsRegex(regexPatternIndexFinderList, "MOV", regexType));

        sentence = "Johny can not sing absolutely perfectly.";
        encodedPath = "NMOVAA";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, MODAL_VERB_NOT_PHRASE);
        assertTrue(regexPatternIndexFinderList.size() > 0);
        System.out.println(sentence);
        assertTrue(RegexUtils.containsRegex(regexPatternIndexFinderList, "MOVAA", regexType));

    }

    @Test
    public void testNotFound(){
        List<RegexPatternData> regexPatternIndexFinderList = null;
        String sentence = "";
        String encodedPath = "";

        sentence = "Johny plays guitar absolutely perfectly.";
        encodedPath = "NVNAA";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, MODAL_VERB_NOT_PHRASE);
        assertTrue(regexPatternIndexFinderList.size() == 0);
        System.out.println(sentence +  " -> " + regexType + " NOT FOUND");

        sentence = "Johny sings absolutely perfectly.";
        encodedPath = "NVAA";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, MODAL_VERB_NOT_PHRASE);
        assertTrue(regexPatternIndexFinderList.size() == 0);
        System.out.println(sentence +  " -> " + regexType + " NOT FOUND");

    }

}
