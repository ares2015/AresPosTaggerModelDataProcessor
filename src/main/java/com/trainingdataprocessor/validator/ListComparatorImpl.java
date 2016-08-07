package com.trainingdataprocessor.validator;

import java.util.List;

/**
 * Created by Oliver on 8/7/2016.
 */
public class ListComparatorImpl implements ListComparator {

    @Override
    public boolean compare(List<Integer> list1, List<Integer> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i <= list1.size() - 1; i++) {
            if (!(list1.get(i).equals(list2.get(i)))) {
                return false;
            }
        }
        return true;
    }
}
