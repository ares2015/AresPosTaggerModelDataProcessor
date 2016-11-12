package com.trainingdataprocessor.main;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.database.TrainingDataDatabaseAccessor;
import com.trainingdataprocessor.factories.BigramDataListFactory;
import com.trainingdataprocessor.factories.StartTagEndTagPairsListFactory;
import com.trainingdataprocessor.preprocessing.TrainingDataPreprocessor;
import com.trainingdataprocessor.semantics.analysis.SemanticAnalyserImpl;
import com.trainingdataprocessor.semantics.extraction.SemanticExtractor;
import com.trainingdataprocessor.semantics.preprocessing.SemanticPreprocessor;
import com.trainingdataprocessor.syntax.SyntaxAnalyserImpl;
import com.trainingdataprocessor.tokens.TokenTagDataProcessorImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Oliver on 11/12/2016.
 */
public class NlpTrainingDataProcessor {

    private TrainingDataPreprocessor trainingDataPreprocessor;

    private TrainingDataDatabaseAccessor trainingDataDatabaseAccessor;

    private BigramDataListFactory bigramDataListFactory;

    private StartTagEndTagPairsListFactory startTagEndTagPairsListFactory;

    private SemanticPreprocessor semanticPreprocessor;

    private SemanticExtractor semanticExtractor;

    public NlpTrainingDataProcessor(TrainingDataPreprocessor trainingDataPreprocessor, TrainingDataDatabaseAccessor trainingDataDatabaseAccessor,
                                    BigramDataListFactory bigramDataListFactory, StartTagEndTagPairsListFactory startTagEndTagPairsListFactory,
                                    SemanticPreprocessor semanticPreprocessor, SemanticExtractor semanticExtractor) {
        this.trainingDataPreprocessor = trainingDataPreprocessor;
        this.trainingDataDatabaseAccessor = trainingDataDatabaseAccessor;
        this.bigramDataListFactory = bigramDataListFactory;
        this.startTagEndTagPairsListFactory = startTagEndTagPairsListFactory;
        this.semanticPreprocessor = semanticPreprocessor;
        this.semanticExtractor = semanticExtractor;
    }

    public static void main(String[] args) {
        final ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        final NlpTrainingDataProcessor nlpTrainingDataProcessor = (NlpTrainingDataProcessor) context.getBean("nlpTrainingDataProcessor");
        nlpTrainingDataProcessor.process();
    }


    public void process() {
        List<TrainingDataRow> trainingDataRowList = trainingDataPreprocessor.preprocess();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Runnable syntaxAnalyser = new SyntaxAnalyserImpl(trainingDataDatabaseAccessor, bigramDataListFactory, startTagEndTagPairsListFactory, trainingDataRowList);
        Runnable semanticAnalyser = new SemanticAnalyserImpl(semanticPreprocessor, semanticExtractor, trainingDataDatabaseAccessor, trainingDataRowList);
        Runnable tokenTagDataProcessor = new TokenTagDataProcessorImpl(trainingDataDatabaseAccessor, trainingDataRowList);
        executor.execute(syntaxAnalyser);
        executor.execute(semanticAnalyser);
        executor.execute(tokenTagDataProcessor);

    }

}