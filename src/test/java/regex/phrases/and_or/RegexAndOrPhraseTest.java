package regex.phrases.and_or;

import com.trainingdataprocessor.data.regex.RegexPatternData;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.regex.RegexPatternSearcherImpl;
import com.trainingdataprocessor.regex.RegexUtils;
import org.junit.Test;

import java.util.List;

import static com.trainingdataprocessor.regex.RegexExpressions.AND_OR_PHRASE;
import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 10/21/2016.
 */
public class RegexAndOrPhraseTest {

    private RegexPatternSearcher regexPatternSearcher = new RegexPatternSearcherImpl();

    private static final String regexType = "AndOr phrase";

    @Test
    public void testFound() {

        List<RegexPatternData> regexPatternIndexFinderList = null;
        String sentence = "";
        String encodedPath = "";

        sentence = "Johny and Bobby can play guitar absolutely perfectly.";
        encodedPath = "N<NMVNAA";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, AND_OR_PHRASE);
        assertTrue(regexPatternIndexFinderList.size() > 0);
        System.out.println(sentence);
        assertTrue(RegexUtils.containsRegex(regexPatternIndexFinderList, "N<N", regexType));

        sentence = "Johny can sing and dance absolutely perfectly.";
        encodedPath = "NMV<VAA";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, AND_OR_PHRASE);
        assertTrue(regexPatternIndexFinderList.size() > 0);
        System.out.println(sentence);
        assertTrue(RegexUtils.containsRegex(regexPatternIndexFinderList, "V<V", regexType));

    }

}
