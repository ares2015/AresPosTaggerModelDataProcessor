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
    public void testFind(){
        String encodedPath = "NNIJNNXXJNINNN";
        String regexPattern =  "[NJD]+NI[NJD]+N";
        List<RegexPatternIndexData> regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(2, regexPatternIndexFinderList.size());

        assertEquals(0, regexPatternIndexFinderList.get(0).getStartIndex());
        assertEquals(5, regexPatternIndexFinderList.get(0).getEndIndex() - 1);
        assertEquals("NNIJNN", regexPatternIndexFinderList.get(0).getPattern());

        assertEquals(8, regexPatternIndexFinderList.get(1).getStartIndex());
        assertEquals(13, regexPatternIndexFinderList.get(1).getEndIndex() - 1);
        assertEquals("JNINNN", regexPatternIndexFinderList.get(1).getPattern());

    }
}
