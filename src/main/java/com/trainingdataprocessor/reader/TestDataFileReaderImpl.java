package com.trainingdataprocessor.reader;

import com.trainingdataprocessor.data.TestDataRow;
import com.trainingdataprocessor.validator.TestDataValidator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Oliver on 8/5/2016.
 */
public class TestDataFileReaderImpl implements TestDataReader {

    private TestDataValidator testDataValidator;

    public TestDataFileReaderImpl(TestDataValidator testDataValidator) {
        this.testDataValidator = testDataValidator;
    }

    @Override
    public List<TestDataRow> read() {
        List<TestDataRow> testDataRowList = new ArrayList<>();
        BufferedReader br = null;
        int lineNumber = 0;
        try {
            br = new BufferedReader(new FileReader("c:\\Users\\Oliver\\Documents\\NlpTestData\\NlpTestData.txt"));
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            String testDataRow = br.readLine();
            while (testDataRow != null) {
                lineNumber++;
                testDataValidator.validate(testDataRow, lineNumber);
                final String[] sentencePattern = testDataRow.split("#");

                final String[] sentence = sentencePattern[0].split("\\ ");
                final String[] pattern = sentencePattern[1].split("\\ ");
//                if (sentence.length != pattern.length) {
//                    throw new RuntimeException();
//                }
                final String[] subSentences = sentencePattern[0].split("\\,");
                final String[] subPatterns = sentencePattern[1].split("\\,");

                testDataRow = br.readLine();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return testDataRowList;
    }
}