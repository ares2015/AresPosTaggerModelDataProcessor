package com.trainingdataprocessor.writer;

import com.trainingdataprocessor.data.syntax.BigramData;
import com.trainingdataprocessor.data.syntax.SubPathData;

import java.io.IOException;
import java.util.List;

/**
 * Created by Oliver on 2/6/2017.
 */
public interface TrainingDataWriter {

    void writeTags(List<String> tagsList);

    void writeTokenTags(List<String> tokensList, List<String> tagsList);

    void writeBigrams(List<BigramData> bigramDataList);

    void writeSubPaths(List<SubPathData> subPathDataList);

    void writeEncodedPath(List<String> encodedPathsList) throws IOException;

}
