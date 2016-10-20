package regex.sentences.modal;

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
public class RegexPatternSearcherForModalVerbTest {

//    private RegexPatternSearcher regexPatternSearcher = new RegexPatternSearcherImpl();
//
//    @Test
//    public void testMODAL_VERB_3_LEVELWholePatternFound() {
//        String sentence = "";
//        List<RegexPatternData> regexPatternIndexFinderList = null;
//        String encodedPath = null;
//
//        sentence = "John can speak Spanish";
//        encodedPath = "NMVN";
//        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_MODAL_VERB_NOUN_RELATION_PATTERN);
//        assertEquals(1, regexPatternIndexFinderList.size());
//        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
//        assertEquals(sentence.split("\\ ").length, encodedPath.length());
//        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());
//
//        sentence = "John can speak Spanish fluently";
//        encodedPath = "NMVNA";
//        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_MODAL_VERB_NOUN_RELATION_PATTERN);
//        assertEquals(1, regexPatternIndexFinderList.size());
//        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
//        assertEquals(sentence.split("\\ ").length, encodedPath.length());
//        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());
//
//        sentence = "John can fluently speak Spanish";
//        encodedPath = "NMVAN";
//        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_MODAL_VERB_NOUN_RELATION_PATTERN);
//        assertEquals(1, regexPatternIndexFinderList.size());
//        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
//        assertEquals(sentence.split("\\ ").length, encodedPath.length());
//        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());
//
//    }
//
//    @Test
//    public void testMODAL_VERB_3_LEVELNoPatternFound() {
//        String sentence = "";
//        List<RegexPatternData> regexPatternIndexFinderList = null;
//        String encodedPath = null;
//
//        sentence = "John can speak";
//        encodedPath = "NMV";
//        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_MODAL_VERB_NOUN_RELATION_PATTERN);
//        assertEquals(0, regexPatternIndexFinderList.size());
//
//        sentence = "John speaks Spanish fluently";
//        encodedPath = "NVNA";
//        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_MODAL_VERB_NOUN_RELATION_PATTERN);
//        assertEquals(0, regexPatternIndexFinderList.size());
//
//        sentence = "John fluently speaks Spanish";
//        encodedPath = "NAVN";
//        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_MODAL_VERB_NOUN_RELATION_PATTERN);
//        assertEquals(0, regexPatternIndexFinderList.size());
//    }
//
//
//    @Test
//    public void testMODAL_VERB_NOT_3_LEVELWholePatternFound() {
//        String sentence = "";
//        List<RegexPatternData> regexPatternIndexFinderList = null;
//        String encodedPath = null;
//
//        //John can speak Spanish
//        sentence = "John can not speak Spanish";
//        encodedPath = "NMOVN";
//        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_MODAL_VERB_NOT_NOUN_RELATION_PATTERN);
//        assertEquals(1, regexPatternIndexFinderList.size());
//        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
//        assertEquals(sentence.split("\\ ").length, encodedPath.length());
//        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());
//
//        //John can speak Spanish fluently
//        sentence = "John can not speak Spanish fluently";
//        encodedPath = "NMOVNA";
//        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_MODAL_VERB_NOT_NOUN_RELATION_PATTERN);
//        assertEquals(1, regexPatternIndexFinderList.size());
//        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
//        assertEquals(sentence.split("\\ ").length, encodedPath.length());
//        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());
//
//        //John can speak Spanish fluently
//        sentence = "John can not fluently speak Spanish";
//        encodedPath = "NMOVAN";
//        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_MODAL_VERB_NOT_NOUN_RELATION_PATTERN);
//        assertEquals(1, regexPatternIndexFinderList.size());
//        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
//        assertEquals(sentence.split("\\ ").length, encodedPath.length());
//        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());
//    }
//
//
//    @Test
//    public void testMODAL_VERB_2_LEVELWholePatternFound() {
//        String sentence = "";
//        List<RegexPatternData> regexPatternIndexFinderList = null;
//        String encodedPath = null;
//
//        sentence = "gray wolf can eat";
//        encodedPath = "JNMV";
//        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_MODAL_VERB_RELATION_PATTERN);
//        assertEquals(1, regexPatternIndexFinderList.size());
//        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
//        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());
//
//        sentence = "boys can play at school";
//        encodedPath = "NMVPN";
//        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_MODAL_VERB_RELATION_PATTERN);
//        assertEquals(1, regexPatternIndexFinderList.size());
//        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
//        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());
//
//        sentence = "Fans of Russia could celebrate at European Championships";
//        encodedPath = "NPNMVPNN";
//        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_MODAL_VERB_RELATION_PATTERN);
//        assertEquals(1, regexPatternIndexFinderList.size());
//        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
//        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());
//
//        sentence = "gray wolf can't eat";
//        encodedPath = "JNMV";
//        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_MODAL_VERB_RELATION_PATTERN);
//        assertEquals(1, regexPatternIndexFinderList.size());
//        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
//        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());
//
//        sentence = "boys can't play at school";
//        encodedPath = "NMVPN";
//        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_MODAL_VERB_RELATION_PATTERN);
//        assertEquals(1, regexPatternIndexFinderList.size());
//        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
//        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());
//
//        sentence = "Fans of Russia couldn't celebrate at European Championships";
//        encodedPath = "NPNMVPNN";
//        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_MODAL_VERB_RELATION_PATTERN);
//        assertEquals(1, regexPatternIndexFinderList.size());
//        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
//        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());
//    }
//
//    @Test
//    public void testMODAL_VERB_NOT_2_LEVELWholePatternFound() {
//        String sentence = "";
//        List<RegexPatternData> regexPatternIndexFinderList = null;
//        String encodedPath = null;
//
//        sentence = "gray wolf can not eat";
//        encodedPath = "JNMOV";
//        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_MODAL_VERB_NOT_RELATION_PATTERN);
//        assertEquals(1, regexPatternIndexFinderList.size());
//        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
//        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());
//
//        sentence = "boys can not play at school";
//        encodedPath = "NMOVPN";
//        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_MODAL_VERB_NOT_RELATION_PATTERN);
//        assertEquals(1, regexPatternIndexFinderList.size());
//        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
//        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());
//
//        sentence = "Fans of Russia could not celebrate at European Championships";
//        encodedPath = "NPNMOVPNN";
//        regexPatternIndexFinderList = regexPatternSearcher.search(encodedPath, NOUN_MODAL_VERB_NOT_RELATION_PATTERN);
//        assertEquals(1, regexPatternIndexFinderList.size());
//        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
//        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());
//    }

}
