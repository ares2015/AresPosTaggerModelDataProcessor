package regex.verb;

import com.trainingdataprocessor.data.RegexPatternData;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.regex.RegexPatternSearcherImpl;
import org.junit.Test;

import java.util.List;

import static com.trainingdataprocessor.regex.RegexExpressions.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Oliver on 10/11/2016.
 */
public class RegexPatternSearcherForVerbTest {

    private RegexPatternSearcher regexPatternSearcher = new RegexPatternSearcherImpl();

    @Test
    public void testVERB_3_LEVELWholePatternFound() {
        String sentence = "";
        List<RegexPatternData> regexPatternIndexFinderList = null;
        String encodedPath = null;

        sentence = "gray wolf eats meat";
        encodedPath = "JNVN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "boys play football at school";
        encodedPath = "NVNPN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "Fans of Russia attacked English fans at European Championships";
        encodedPath = "NPN$NNPNN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());
    }

    @Test
    public void testVERB_3_LEVELPartialPatternFound() {
        String sentence = "";
        List<RegexPatternData> regexPatternIndexFinderList = null;
        String encodedPath = null;

        sentence = "gray wolf eats meat and vegetables";
        encodedPath = "JNVN>N";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals("JNVN", regexPatternIndexFinderList.get(0).getPattern());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "boys and girls play football at school";
        encodedPath = "N>NVNPN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals("NVNPN", regexPatternIndexFinderList.get(0).getPattern());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "Fans of Russia attacked English fans and players at European Championships";
        encodedPath = "NPN$NN>NPNN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals("NPN$NN", regexPatternIndexFinderList.get(0).getPattern());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());
    }

    @Test
    public void testVERB_3_LEVELNoPatternFound(){
        String sentence = "";
        List<RegexPatternData> regexPatternIndexFinderList = null;
        String encodedPath = null;

        sentence = "John speaks";
        encodedPath = "NV";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_NOUN_RELATION_PATTERN);
        assertEquals(0, regexPatternIndexFinderList.size());


        sentence = "John can speak Spanish";
        encodedPath = "NMVN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_NOUN_RELATION_PATTERN);
        assertEquals(0, regexPatternIndexFinderList.size());

        sentence = "John can speak Spanish fluently";
        encodedPath = "NMVNA";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_NOUN_RELATION_PATTERN);
        assertEquals(0, regexPatternIndexFinderList.size());

        sentence = "John can fluently speak Spanish";
        encodedPath = "NMVAN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_NOUN_RELATION_PATTERN);
        assertEquals(0, regexPatternIndexFinderList.size());

        sentence = "John don't speak Spanish";
        encodedPath = "NLVN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_NOUN_RELATION_PATTERN);
        assertEquals(0, regexPatternIndexFinderList.size());

        sentence = "John don't speak Spanish fluently";
        encodedPath = "NLVNA";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_NOUN_RELATION_PATTERN);
        assertEquals(0, regexPatternIndexFinderList.size());

        sentence = "John don't fluently speak Spanish";
        encodedPath = "NLVAN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_NOUN_RELATION_PATTERN);
        assertEquals(0, regexPatternIndexFinderList.size());

        sentence = "John does not speak Spanish";
        encodedPath = "NLOVN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_NOUN_RELATION_PATTERN);
        assertEquals(0, regexPatternIndexFinderList.size());

        sentence = "John does not speak Spanish fluently";
        encodedPath = "NLOVNA";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_NOUN_RELATION_PATTERN);
        assertEquals(0, regexPatternIndexFinderList.size());

        sentence = "John does not fluently speak Spanish";
        encodedPath = "NLOVAN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_NOUN_RELATION_PATTERN);
        assertEquals(0, regexPatternIndexFinderList.size());
    }

    @Test
    public void testVERB_DONT_3_LEVELWholePatternFound() {
        String sentence = "";
        List<RegexPatternData> regexPatternIndexFinderList = null;
        String encodedPath = null;

        sentence = "John don't speak Spanish";
        encodedPath = "NLVN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_DONT_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "John don't speak Spanish fluently";
        encodedPath = "NLVNA";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_DONT_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "John don't fluently speak Spanish";
        encodedPath = "NLVAN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_DONT_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

    }

    @Test
    public void testVERB_DONT_3_LEVELNoPatternFound() {
        String sentence = "";
        List<RegexPatternData> regexPatternIndexFinderList = null;
        String encodedPath = null;

        sentence = "gray wolf eats";
        encodedPath = "JNV";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_DONT_NOUN_RELATION_PATTERN);
        assertEquals(0, regexPatternIndexFinderList.size());


        sentence = "boys play at school";
        encodedPath = "NVPN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_DONT_NOUN_RELATION_PATTERN);
        assertEquals(0, regexPatternIndexFinderList.size());

        sentence = "Fans of Russia celebrated at European Championships";
        encodedPath = "NPN$PNN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_DONT_NOUN_RELATION_PATTERN);
        assertEquals(0, regexPatternIndexFinderList.size());
    }

    @Test
    public void testVERB_DO_NOT_3_LEVELWholePatternFound() {
        String sentence = "";
        List<RegexPatternData> regexPatternIndexFinderList = null;
        String encodedPath = null;

        sentence = "John does not speak Spanish";
        encodedPath = "NLOVN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_DO_NOT_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "John does not speak Spanish fluently";
        encodedPath = "NLOVNA";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_DO_NOT_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "John does not fluently speak Spanish";
        encodedPath = "NLOVAN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_DO_NOT_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());
    }

    @Test
    public void testVERB_2_LEVELWholePatternFound() {
        String sentence = "";
        List<RegexPatternData> regexPatternIndexFinderList = null;
        String encodedPath = null;

        sentence = "gray wolf eats";
        encodedPath = "JNV";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "boys play at school";
        encodedPath = "NVPN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "Fans of Russia celebrated at European Championships";
        encodedPath = "NPN$PNN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());
    }

    @Test
    public void testVERB_DONT_2_LEVELWholePatternFound() {
        String sentence = "";
        List<RegexPatternData> regexPatternIndexFinderList = null;
        String encodedPath = null;

        sentence = "gray wolf doesn't eat";
        encodedPath = "JNLV";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_DONT_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "boys don't play at school";
        encodedPath = "NLVPN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_DONT_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "Fans of Russia didn't celebrate at European Championships";
        encodedPath = "NPNLVPNN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_DONT_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());
    }

    @Test
    public void testVERB_DO_NOT_2_LEVELWholePatternFound() {
        String sentence = "";
        List<RegexPatternData> regexPatternIndexFinderList = null;
        String encodedPath = null;

        sentence = "gray wolf does not eat";
        encodedPath = "JNLOV";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_DO_NOT_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "boys do not play at school";
        encodedPath = "NLOVPN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_DO_NOT_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "Fans of Russia did not celebrate at European Championships";
        encodedPath = "NPNLOVPNN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_DO_NOT_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "Fans of Russia did not celebrate wildly at European Championships";
        encodedPath = "NPNLOVAPNN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_DO_NOT_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "Fans of Russia did not wildy celebrate at European Championships";
        encodedPath = "NPNLOAVPNN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_VERB_DO_NOT_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());
    }


}
