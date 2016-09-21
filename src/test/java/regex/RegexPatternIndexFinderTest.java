package regex;

import com.trainingdataprocessor.data.RegexPatternIndexData;
import com.trainingdataprocessor.regex.RegexPatternIndexFinder;
import com.trainingdataprocessor.regex.RegexPatternIndexFinderImpl;
import com.trainingdataprocessor.tags.EncodedTags;
import org.junit.Test;

import java.util.List;

import static com.trainingdataprocessor.regex.RegexExpressions.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Oliver on 8/17/2016.
 */
public class RegexPatternIndexFinderTest {

    private RegexPatternIndexFinder regexPatternIndexFinder = new RegexPatternIndexFinderImpl();

    @Test
    public void testFindISRelationshipWholePatternFound(){
        String sentence = "";

//        String regexPattern =  "[#NJD$@]?[PT]?[NJD]*N[A]?I[#NJD$@]*[PT]?[Y#NJD$V]*[PT]?[Y#NJD$V]*[PT]?[Y#NJD$V]*";
        String regexPattern = BEFORE_IS_PREPOSITION_PHRASE +  BEFORE_IS_NOUN_PHRASE + EncodedTags.IS_ARE + AFTER_IS_NOUN_PHRASE +
                AFTER_IS_PREPOSITION_PHRASE + AFTER_IS_PREPOSITION_PHRASE + AFTER_IS_PREPOSITION_PHRASE + AFTER_IS_PREPOSITION_PHRASE;
        List<RegexPatternIndexData> regexPatternIndexFinderList = null;
        String encodedPath = null;

        //dog is animal
        sentence = "dog is animal";
        encodedPath = "NIN";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        //John is his son
        sentence = "John is his son";
        encodedPath = "NIYN";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        //gray wolf is wild animal
        sentence = "gray wolf is wild animal";
        encodedPath = "JNIJN";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        //gray wolf is wild animal living in deep forests
        sentence = "gray wolf is wild animal living in deep forests";
        encodedPath = "JNIJN@PJN";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        //New York City is the largest city in the United States - NNIDJNPDNN
        sentence = "New York City is the largest city in the United States";
        encodedPath = "NNNIDJNPDNN";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        //New York City was settled by Europeans in 1624 - NNNI$PNP#
        sentence = "New York City was settled by Europeans in 1624";
        encodedPath = "NNNI$PNP#";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        //New York City was settled by Europeans from The Netherlands in 1624 - NNNI$PNPDNP#
        sentence = "New York City was settled by Europeans from The Netherlands in 1624";
        encodedPath = "NNNI$PNPDNP#";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        //Subway transportation is provided by the New York City Subway system - NNI$PDNNNN
        sentence = "Subway transportation is provided by the New York City Subway system";
        encodedPath = "NNI$PDNNNNN";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        //The city is divided into five major parts called boroughs - DNI$P#JN$N
        sentence = "The city is divided into five major parts called boroughs";
        encodedPath = "DNI$P#JN$N";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        //Billy Graham was born on a dairy farm near Charlotte - NNI$PDJNPN
        sentence = "Billy Graham was born on a dairy farm near Charlotte";
        encodedPath = "NNI$PDJNPN";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        //Vivaldi probably was taught to play the violin by father - NAI$TVDNPN
        sentence = "Vivaldi probably was taught to play the violin by his father";
        encodedPath = "NAI$TVDNPYN";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        //Fans of Spartak Trnava are very aggresive = NPNNIJJ
        sentence = "Fans of Spartak Trnava are very aggresive";
        encodedPath = "NPNNIJJ";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "Vermeer was a moderately successful provincial genre painter in his lifetime";
        encodedPath = "NIDAJJNN";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(encodedPath.length(), regexPatternIndexFinderList.get(0).getPattern().length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence  = "Vermeer was rediscovered by Gustav Friedrich Waagen nn the 19th century";
        encodedPath = "NI$PNNNPD#N";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(encodedPath.length(), regexPatternIndexFinderList.get(0).getPattern().length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

    }

    @Test
    public void testFindISRelationshipPartialPatternFound() {
        String sentence = "";

//        String regexPattern =  "[#NJD$@]?[PT]?[NJD]*N[A]?I[#NJD$@]*[PT]?[Y#NJD$V]*[PT]?[Y#NJD$V]*[PT]?[Y#NJD$V]*";
        String regexPattern = BEFORE_IS_PREPOSITION_PHRASE + BEFORE_IS_NOUN_PHRASE + EncodedTags.IS_ARE + AFTER_IS_NOUN_PHRASE +
                AFTER_IS_PREPOSITION_PHRASE + AFTER_IS_PREPOSITION_PHRASE + AFTER_IS_PREPOSITION_PHRASE + AFTER_IS_PREPOSITION_PHRASE;
        List<RegexPatternIndexData> regexPatternIndexFinderList = null;
        String encodedPath = null;

        sentence = "dog is animal and mammal";
        encodedPath = "NIN<N";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals("NIN", regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(3, regexPatternIndexFinderList.get(0).getPattern().length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "Johannes Vermeer was a Dutch painter who specialized in domestic interior scenes of middle-class life";
        encodedPath = "NNIDNNW$PJJNPJN";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals("NNIDNN", regexPatternIndexFinderList.get(0).getPattern());
        assertEquals("NNIDNN".length(), regexPatternIndexFinderList.get(0).getPattern().length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        sentence = "Wayne Gretzky was hockey player who was the best of all";
        encodedPath = "NNINNWIDJPQ";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals("NNINN", regexPatternIndexFinderList.get(0).getPattern());
        assertEquals("NNINN".length(), regexPatternIndexFinderList.get(0).getPattern().length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

    }

        @Test
    public void testFindISRelationshipNoPatternFound(){
        String regexPattern =  "[NJD]*N[A]?I[#NJD$]*[PT]?[Y#NJD$V]*[PT]?[Y#NJD$V]*";
        List<RegexPatternIndexData> regexPatternIndexFinderList = null;
        String encodedPath = null;

        //gray wolf eats meat
        encodedPath = "JNVN";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(0, regexPatternIndexFinderList.size());
    }

    @Test
    public void testFindVerbRelationshipWholePatternFound(){
        String sentence = "";
        String regexPattern =  "[#NJD$@]?[PT]?[NJD]*N[A]?[V$][#NJD$]*[PT]?[Y#NJD$V]*[PT]?[Y#NJD$V]*";
        List<RegexPatternIndexData> regexPatternIndexFinderList = null;
        String encodedPath = null;

        //gray wolf eats meat
        sentence = "gray wolf eats meat";
        encodedPath = "JNVN";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        //boys play football at school
        sentence = "boys play football at school";
        encodedPath = "NVNPN";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

        //Fans of Russia attacked English fans at European Championships
        sentence = "Fans of Russia attacked English fans at European Championships";
        encodedPath = "NPN$NNPNN";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        assertEquals(encodedPath, regexPatternIndexFinderList.get(0).getPattern());
        assertEquals(sentence.split("\\ ").length, encodedPath.length());
        System.out.println(sentence + ": " + regexPatternIndexFinderList.get(0).getPattern());

    }

}