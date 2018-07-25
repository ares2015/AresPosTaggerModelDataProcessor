package com.aresPosTaggerModelDataProcessor.writer.subpaths;

import com.aresPosTaggerModelDataProcessor.data.syntax.SubPathData;

import java.util.List;

/**
 * Created by Oliver on 2/7/2017.
 */
public interface SubPathsWriter {

    void write(List<SubPathData> subPathDataList);

}
