package com.trainingdataprocessor.factories;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 8/6/2016.
 */
public class SubPathsListFactoryImpl implements SubPathsListFactory {

    @Override
    public List<List<String>> create(List<String> words) {
        List<List<String>> subPathsList = new ArrayList<List<String>>();
        List<String> subPath = new ArrayList<>();
        int index = 0;
        for (String word : words) {
            if (!(word.endsWith(","))) {
                subPath.add(word);
            } else {
                subPath.add(word);
                subPathsList.add(subPath);
                subPath = new ArrayList<>();
            }
            if (index == words.size() - 1) {
                subPathsList.add(subPath);
            }
            index++;
        }
        return subPathsList;
    }

}
