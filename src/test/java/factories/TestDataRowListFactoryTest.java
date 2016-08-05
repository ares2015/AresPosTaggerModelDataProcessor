package factories;

import com.trainingdataprocessor.data.factories.TestDataRowListFactory;
import com.trainingdataprocessor.data.factories.TestDataRowListFactoryImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 8/5/2016.
 */
public class TestDataRowListFactoryTest {

    private TestDataRowListFactory testDataRowListFactory = new TestDataRowListFactoryImpl();

    @Test
    public void testCreate() {
        List<String> testDataRowStringList = new ArrayList<>();
        String testDataRowString = "boys drink beer in pub#N V N PR N";
        testDataRowStringList.add(testDataRowString);
        testDataRowListFactory.create(testDataRowStringList);
    }
}
