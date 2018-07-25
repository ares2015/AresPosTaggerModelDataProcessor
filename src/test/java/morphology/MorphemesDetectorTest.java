package morphology;

import com.aresPosTaggerModelDataProcessor.morphology.MorphemesDetector;
import com.aresPosTaggerModelDataProcessor.morphology.MorphemesDetectorImpl;
import com.aresPosTaggerModelDataProcessor.morphology.Suffixes;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oliver on 3/20/2017.
 */
public class MorphemesDetectorTest {

    private MorphemesDetector morphemesDetector = new MorphemesDetectorImpl();

    @Test
    public void test() {
        assertEquals(Suffixes.S, morphemesDetector.detectSuffix("dogs"));
        assertEquals(Suffixes.ER, morphemesDetector.detectSuffix("jumper"));
    }


}
