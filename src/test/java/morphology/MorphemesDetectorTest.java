package morphology;

import com.trainingdataprocessor.morphology.MorphemesDetector;
import com.trainingdataprocessor.morphology.MorphemesDetectorImpl;
import com.trainingdataprocessor.morphology.Suffixes;
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
