package validator;

import com.trainingdataprocessor.validator.ListComparator;
import com.trainingdataprocessor.validator.ListComparatorImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 8/7/2016.
 */
public class ListComparatorTest {

    private ListComparator listComparator = new ListComparatorImpl();

    @Test
    public void testCompareDifferentValues(){
        List<Integer> list1 = new ArrayList<>();
        list1.add(5);
        list1.add(10);

        List<Integer> list2 = new ArrayList<>();
        list2.add(5);
        list2.add(11);

        assertFalse(listComparator.compare(list1, list2));
    }

    @Test
    public void testCompareDifferentSizes(){
        List<Integer> list1 = new ArrayList<>();
        list1.add(5);

        List<Integer> list2 = new ArrayList<>();
        list2.add(5);
        list2.add(11);

        assertFalse(listComparator.compare(list1, list2));
    }

    @Test
    public void testCompareAreEqual(){
        List<Integer> list1 = new ArrayList<>();
        list1.add(5);
        list1.add(10);

        List<Integer> list2 = new ArrayList<>();
        list2.add(5);
        list2.add(10);

        assertTrue(listComparator.compare(list1, list2));
    }
}
