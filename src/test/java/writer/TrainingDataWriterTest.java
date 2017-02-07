package writer;

import com.trainingdataprocessor.writer.TrainingDataWriter;
import com.trainingdataprocessor.writer.TrainingDataWriterImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 2/7/2017.
 */
public class TrainingDataWriterTest {

    private TrainingDataWriter trainingDataWriter;

    @Before
    public void setup() {
        trainingDataWriter = new TrainingDataWriterImpl();
    }

    @Test
    public void testWriteEncodedPaths() throws IOException {
        List<String> encodedPaths = new ArrayList<>();
        encodedPaths.add("NVN");
        encodedPaths.add("JJN");
        trainingDataWriter.writeEncodedPath(encodedPaths);

    }

    @Test
    public void testWriteTags(){
        List<String> tags = new ArrayList<String>();
        tags.add("N");
        tags.add("V");
        trainingDataWriter.writeTags(tags);
    }
}
