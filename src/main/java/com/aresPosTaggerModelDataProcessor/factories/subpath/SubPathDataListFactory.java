package com.aresPosTaggerModelDataProcessor.factories.subpath;

import com.aresPosTaggerModelDataProcessor.data.syntax.SubPathData;

import java.util.List;

/**
 * Created by Oliver on 7/25/2016.
 */
public interface SubPathDataListFactory {

    List<SubPathData> create(List<String> tags);

}
