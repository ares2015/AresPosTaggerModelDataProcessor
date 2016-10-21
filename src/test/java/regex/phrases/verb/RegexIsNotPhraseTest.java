package regex.phrases.verb;

import com.trainingdataprocessor.data.RegexPatternData;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.regex.RegexPatternSearcherImpl;
import com.trainingdataprocessor.regex.RegexUtils;
import org.junit.Test;

import java.util.List;

import static com.trainingdataprocessor.regex.RegexExpressions.IS_NOT_PHRASE;
import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 10/21/2016.
 */
public class RegexIsNotPhraseTest {

    private RegexPatternSearcher regexPatternSearcher = new RegexPatternSearcherImpl();

    private static final String regexType = "Is not phrase";

    @Test
    public void testFound(){
        List<RegexPatternData> regexPatternIndexFinderList = null;
        String sentence = "";
        String encodedPath = "";

        sentence = "dog is not very best friend of man";
        encodedPath = "NIOAJNPN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, IS_NOT_PHRASE);
        assertTrue(regexPatternIndexFinderList.size() > 0);
        System.out.println(sentence);
        assertTrue(RegexUtils.containsRegex(regexPatternIndexFinderList, "IOA", regexType));
    }

    @Test
    public void testNotFound(){
        List<RegexPatternData> regexPatternIndexFinderList = null;
        String sentence = "";
        String encodedPath = "";

        sentence = "dog is very best friend of man";
        encodedPath = "NIAJNPN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, IS_NOT_PHRASE);
        assertTrue(regexPatternIndexFinderList.size() == 0);
        System.out.println(sentence +  " -> " + regexType + " NOT FOUND");
    }
}
