package factories;


import com.trainingdataprocessor.data.syntax.SubPathData;
import com.trainingdataprocessor.factories.subpath.SubPathDataListFactory;
import com.trainingdataprocessor.factories.subpath.SubPathDataListFactoryImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SubPathDataListFactoryTest {

    SubPathDataListFactory subPathDataListFactory = new SubPathDataListFactoryImpl();

    @Test
    public void testCreate(){

        List<String> tags = new ArrayList<String>();
        tags.add("N");
        tags.add("V");
        tags.add("DET");
        tags.add("N");

        List<SubPathData> subPathDataList = subPathDataListFactory.create(tags);
        //N V
        assertFalse(subPathDataList.get(0).isConstantSubPath());
        assertEquals("N V", subPathDataList.get(0).getSubPath());
        //N V DET
        assertFalse(subPathDataList.get(1).isConstantSubPath());
        assertEquals("N V DET", subPathDataList.get(1).getSubPath());
        //N V DET N
        assertTrue(subPathDataList.get(2).isConstantSubPath());
        assertEquals("N V DET N", subPathDataList.get(2).getSubPath());
        //V DET
        assertFalse(subPathDataList.get(3).isConstantSubPath());
        assertEquals("V DET", subPathDataList.get(3).getSubPath());
        //V DET N
        assertTrue(subPathDataList.get(4).isConstantSubPath());
        assertEquals("V DET N", subPathDataList.get(4).getSubPath());
        //DET N
        assertFalse(subPathDataList.get(5).isConstantSubPath());
        assertEquals("DET N", subPathDataList.get(5).getSubPath());

        assertEquals(6, subPathDataList.size());
    }
}
