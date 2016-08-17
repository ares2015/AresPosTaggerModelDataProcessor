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
        String encodedPath = "XXXNJJINXXXXXDNNIN";
        String regexPattern =  "N[NJD]+IN";
        List<RegexPatternIndexData> regexPatternIndexFinderList = regexPatternIndexFinder.find(encodedPath, regexPattern);
        assertEquals(2, regexPatternIndexFinderList.size());

        assertEquals(3, regexPatternIndexFinderList.get(0).getStartIndex());
        assertEquals(7, regexPatternIndexFinderList.get(0).getEndIndex() - 1);
        assertEquals("NJJIN", regexPatternIndexFinderList.get(0).getPattern());

        assertEquals(14, regexPatternIndexFinderList.get(1).getStartIndex());
        assertEquals(17, regexPatternIndexFinderList.get(1).getEndIndex() - 1);
        assertEquals("NNIN", regexPatternIndexFinderList.get(1).getPattern());

    }
}
