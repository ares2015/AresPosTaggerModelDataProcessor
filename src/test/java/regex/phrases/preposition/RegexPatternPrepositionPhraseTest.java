package regex.phrases.preposition;

import com.trainingdataprocessor.data.RegexPatternData;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.regex.RegexPatternSearcherImpl;
import com.trainingdataprocessor.tags.EncodedTags;
import org.junit.Test;
import com.trainingdataprocessor.regex.RegexUtils;

import java.util.List;

import static com.trainingdataprocessor.regex.RegexExpressions.PREPOSITION_PHRASE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 10/20/2016.
 */
public class RegexPatternPrepositionPhraseTest {

    private RegexPatternSearcher regexPatternSearcher = new RegexPatternSearcherImpl();

    private static final String regexType = "Preposition phrase";

    @Test
    public void testFound(){
        List<RegexPatternData> regexPatternIndexFinderList = null;
        String sentence = "";
        String encodedPath = "";

        sentence = "Aggressive stupid Russian fans in Paris at Euro attack fans of English team";
        encodedPath = "JJNNPNPNVNPNN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, PREPOSITION_PHRASE);
        assertTrue(regexPatternIndexFinderList.size() > 0);
        System.out.println(sentence);
        assertTrue(RegexUtils.containsRegex(regexPatternIndexFinderList, "JJNNPNPN", regexType));
        assertTrue(RegexUtils.containsRegex(regexPatternIndexFinderList, "NPNN", regexType));
    }

    @Test
    public void testFoundWitNot(){
        List<RegexPatternData> regexPatternIndexFinderList = null;
        String sentence = "";
        String encodedPath = "";

        sentence = "the English Peasant Revolt was led not by peasants";
        encodedPath = "DNNNI$OPN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, PREPOSITION_PHRASE);
        System.out.println(sentence);
        assertTrue(RegexUtils.containsRegex(regexPatternIndexFinderList, "$OPN", regexType));
    }

    @Test
    public void testPartiallyFound(){
        List<RegexPatternData> regexPatternIndexFinderList = null;
        String sentence = "";
        String encodedPath = "";

        sentence = "Aggressive stupid Russian fans and hooligans in Paris at Euro attack fans and supporters of English team";
        encodedPath = "JJNN<NPNPNVN<NPNN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, PREPOSITION_PHRASE);
        assertTrue(regexPatternIndexFinderList.size() > 0);
        System.out.println(sentence);
        assertTrue(RegexUtils.containsRegex(regexPatternIndexFinderList, "NPNPN", regexType));
        assertTrue(RegexUtils.containsRegex(regexPatternIndexFinderList, "NPNN", regexType));
    }

    @Test
    public void testNotFound(){
        List<RegexPatternData> regexPatternIndexFinderList = null;
        String sentence = "";
        String encodedPath = "";
        sentence = "Aggressive stupid Russian fans attack English team";
        encodedPath = "JJNNVNN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, PREPOSITION_PHRASE);
        System.out.println(sentence +  " -> " + regexType + " NOT FOUND");
        assertFalse(RegexUtils.containSingleTagInRegex(regexPatternIndexFinderList, EncodedTags.PREPOSITION));
    }

}
