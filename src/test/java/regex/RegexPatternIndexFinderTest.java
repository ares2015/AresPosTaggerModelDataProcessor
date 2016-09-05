package regex;

import com.trainingdataprocessor.data.RegexPatternIndexData;
import com.trainingdataprocessor.regex.RegexPatternIndexFinder;
import com.trainingdataprocessor.regex.RegexPatternIndexFinderImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oliver on 8/17/2016.
 */
public class RegexPatternIndexFinderTest {

    private RegexPatternIndexFinder regexPatternIndexFinder = new RegexPatternIndexFinderImpl();

    @Test
    public void testFindOK(){
        //
        String regexPattern =  "[NJD]*N[A]?I[#NJD$]*[PT]?[Y#NJD$V]*[PT]?[Y#NJD$V]*";
        List<RegexPatternIndexData> regexPatternIndexFinderList = null;
        String encodedPath = null;
//        New York City is the largest city in the United States - NNIDJNPDNN
        encodedPath = "NNIDJNPDNN";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        System.out.println(regexPatternIndexFinderList.get(0).getPattern());
        //New York City was settled by Europeans from The Netherlands in 1624 - NNNI$PNPDNP#
        encodedPath = "NNNI$PNPDNP#";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        System.out.println(regexPatternIndexFinderList.get(0).getPattern());
        //Subway transportation is provided by the New York City Subway system - NNI$PDNNNN
        encodedPath = "NNI$PDNNNN";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        System.out.println(regexPatternIndexFinderList.get(0).getPattern());
        //The city is divided into five major parts called boroughs - DNI$P#JN$N
        encodedPath = "DNI$P#JN$N";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        System.out.println(regexPatternIndexFinderList.get(0).getPattern());
//        Billy Graham was born on a dairy farm near Charlotte - NNI$PDJNPN
        encodedPath = "NNI$PDJNPN";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        System.out.println(regexPatternIndexFinderList.get(0).getPattern());
        //Vivaldi probably was taught to play the violin by father - NAI$TVDNPN
        encodedPath = "NAI$TVDNPYN";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        System.out.println(regexPatternIndexFinderList.get(0).getPattern());
        encodedPath = "NIN";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        System.out.println(regexPatternIndexFinderList.get(0).getPattern());
        encodedPath = "NIYN";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(1, regexPatternIndexFinderList.size());
        System.out.println(regexPatternIndexFinderList.get(0).getPattern());
    }

    @Test
    public void testFindNOK(){
        String regexPattern =  "[NJD]*N[A]?I[#NJD$]*[PT]?[Y#NJD$V]*[PT]?[Y#NJD$V]*";
        List<RegexPatternIndexData> regexPatternIndexFinderList = null;
        String encodedPath = null;
        encodedPath = "NNVN";
        regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(0, regexPatternIndexFinderList.size());


    }
}
