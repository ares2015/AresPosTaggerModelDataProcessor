package factories;


import com.trainingdataprocessor.cache.ConstantTagsCache;
import com.trainingdataprocessor.data.syntax.SubPathData;
import com.trainingdataprocessor.factories.SubPathDataListFactory;
import com.trainingdataprocessor.factories.SubPathDataListFactoryImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SubPathDataListFactoryTest {

    ConstantTagsCache constantTagsCache = new ConstantTagsCache();
    SubPathDataListFactory subPathDataListFactory = new SubPathDataListFactoryImpl();

    @Test
    public void testCreate(){

        List<String> tags = new ArrayList<String>();
        tags.add("N");
        tags.add("V");
        tags.add("DET");
        tags.add("N");

        List<SubPathData> subPathDataList = subPathDataListFactory.create(tags);
        assertEquals(6, subPathDataList.size());

    }
}
