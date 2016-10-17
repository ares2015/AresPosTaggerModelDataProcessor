package regex.is;

import com.trainingdataprocessor.data.RegexPatternData;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.regex.RegexPatternSearcherImpl;
import org.junit.Test;

import java.util.List;

import static com.trainingdataprocessor.regex.RegexExpressions.NOUN_IS_NOT_NOUN_RELATION_PATTERN;
import static com.trainingdataprocessor.regex.RegexExpressions.NOUN_IS_NOUN_RELATION_PATTERN;
import static org.junit.Assert.assertEquals;

/**
 * Created by Oliver on 10/11/2016.
 */
public class RegexPatternSearcherForISTest {

    private RegexPatternSearcher regexPatternSearcher = new RegexPatternSearcherImpl();

    @Test
    public void testIS_ISNT_3_LEVELWholePatternFound() {
        String sentence = "";

        List<RegexPatternData> regexPatternIndexFinderList = null;
        String encodedPath = null;

        sentence = "dog is animal";
        encodedPath = "NIN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "John is his son";
        encodedPath = "NIYN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "gray wolf is wild animal";
        encodedPath = "JNIJN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "gray wolf is wild animal living in deep forests";
        encodedPath = "JNIJN@PJN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "New York City is the largest city in the United States";
        encodedPath = "NNNIDJNPDNN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "New York City was settled by Europeans in 1624";
        encodedPath = "NNNI$PNP#";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "New York City was settled by Europeans from The Netherlands in 1624";
        encodedPath = "NNNI$PNPDNP#";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "Subway transportation is provided by the New York City Subway system";
        encodedPath = "NNI$PDNNNNN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "The city is divided into five major parts called boroughs";
        encodedPath = "DNI$P#JN$N";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "Billy Graham was born on a dairy farm near Charlotte";
        encodedPath = "NNI$PDJNPN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "Vivaldi probably was taught to play the violin by his father";
        encodedPath = "NAI$TVDNPYN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "Fans of Spartak Trnava are very aggresive";
        encodedPath = "NPNNIJJ";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "Vermeer was a moderately successful provincial genre painter in his lifetime";
        encodedPath = "NIDAJJNN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(encodedPath.length(), regexPatternIndexFinderList.get(0).getPattern().length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "Vermeer was rediscovered by Gustav Friedrich Waagen nn the 19th century";
        encodedPath = "NI$PNNNPD#N";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(encodedPath.length(), regexPatternIndexFinderList.get(0).getPattern().length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

    }

    @Test
    public void testIS_ISNT_3_LEVELPartialPatternFound() {
        String sentence = "";

        List<RegexPatternData> regexPatternIndexFinderList = null;
        String encodedPath = null;

        sentence = "dog is animal and mammal";
        encodedPath = "NIN<N";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals("NIN", regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(3, regexPatternIndexFinderList.get(0).getPattern().length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "Johannes Vermeer was a Dutch painter who specialized in domestic interior scenes of middle-class life";
        encodedPath = "NNIDNNW$PJJNPJN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals("NNIDNN", regexPatternIndexFinderList.get(0).getPattern());
        assertEquals("NNIDNN".length(), regexPatternIndexFinderList.get(0).getPattern().length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "Wayne Gretzky was hockey player who was the best of all";
        encodedPath = "NNINNWIDJPQ";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals("NNINN", regexPatternIndexFinderList.get(0).getPattern());
        assertEquals("NNINN".length(), regexPatternIndexFinderList.get(0).getPattern().length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

    }

    @Test
    public void testIS_ISNT_3_LEVELNoPatternFound() {
        List<RegexPatternData> regexPatternIndexFinderList = null;
        String encodedPath = null;

        encodedPath = "JNVN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOUN_RELATION_PATTERN);
        assertEquals(0, regexPatternIndexFinderList.size());
    }

    @Test
    public void testIS_NOT_3_LEVELWholePatternFound() {
        String sentence = "";

        List<RegexPatternData> regexPatternIndexFinderList = null;
        String encodedPath = null;

        sentence = "stone is not animal";
        encodedPath = "NION";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOT_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "John is not his son";
        encodedPath = "NIOYN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOT_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "gray wolf is not home animal";
        encodedPath = "JNIOJN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOT_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());
    }

    @Test
    public void testIS_NOT_3_LEVELPartialPatternFound() {
        String sentence = "";

        List<RegexPatternData> regexPatternIndexFinderList = null;
        String encodedPath = null;

        sentence = "dog is not animal and mammal";
        encodedPath = "NION<N";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOT_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals("NION", regexPatternIndexFinderList.get(0).getPattern());
        assertEquals("NION".length(), regexPatternIndexFinderList.get(0).getPattern().length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "Johannes Vermeer was not a Dutch painter who specialized in domestic interior scenes of middle-class life";
        encodedPath = "NNIODNNW$PJJNPJN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOT_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals("NNIODNN", regexPatternIndexFinderList.get(0).getPattern());
        assertEquals("NNIODNN".length(), regexPatternIndexFinderList.get(0).getPattern().length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "Wayne Gretzky was hockey player who was the best of all";
        encodedPath = "NNIONNWIDJPQ";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOT_NOUN_RELATION_PATTERN);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals("NNIONN", regexPatternIndexFinderList.get(0).getPattern());
        assertEquals("NNIONN".length(), regexPatternIndexFinderList.get(0).getPattern().length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

    }

    @Test
    public void testIS_NOT_3_LEVELNoPatternFound() {
        String sentence = "";

        List<RegexPatternData> regexPatternIndexFinderList = null;
        String encodedPath = null;

        sentence = "stone is animal";
        encodedPath = "NIN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOT_NOUN_RELATION_PATTERN);
        assertEquals(0, regexPatternIndexFinderList.size());

        sentence = "John is his son";
        encodedPath = "NIYN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOT_NOUN_RELATION_PATTERN);
        assertEquals(0, regexPatternIndexFinderList.size());

        sentence = "gray wolf is home animal";
        encodedPath = "JNIJN";
        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_IS_NOT_NOUN_RELATION_PATTERN);
        assertEquals(0, regexPatternIndexFinderList.size());
    }

}
