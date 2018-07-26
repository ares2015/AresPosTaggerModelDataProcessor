package com.aresPosTaggerModelDataProcessor.preprocessing;

import com.aresPosTaggerModelDataProcessor.data.preprocessing.ModelDataRow;
import com.aresPosTaggerModelDataProcessor.factories.row.ModelDataRowListFactory;
import com.aresPosTaggerModelDataProcessor.reader.AresPosTaggerModelDataReader;

import java.util.List;

/**
 * Created by Oliver on 11/12/2016.
 */
public class ModelDataPreprocessorImpl implements ModelDataPreprocessor {

    private AresPosTaggerModelDataReader aresPosTaggerModelDataReader;

    private ModelDataRowListFactory modelDataRowListFactory;

    private CapitalizedTokensProcessor capitalizedTokensProcessor;

    public ModelDataPreprocessorImpl(AresPosTaggerModelDataReader aresPosTaggerModelDataReader, ModelDataRowListFactory modelDataRowListFactory,
                                     CapitalizedTokensProcessor capitalizedTokensProcessor) {
        this.aresPosTaggerModelDataReader = aresPosTaggerModelDataReader;
        this.modelDataRowListFactory = modelDataRowListFactory;
        this.capitalizedTokensProcessor = capitalizedTokensProcessor;
    }

    @Override
    public List<ModelDataRow> preprocess() {
        List<String> modelDataRowStringList = aresPosTaggerModelDataReader.read();
        List<ModelDataRow> modelDataRows = modelDataRowListFactory.create(modelDataRowStringList);
        for (ModelDataRow modelDataRow : modelDataRows) {
            capitalizedTokensProcessor.process(modelDataRow);
        }
        return modelDataRows;
    }
}