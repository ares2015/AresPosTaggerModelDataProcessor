package com.trainingdataprocessor.main;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.database.TrainingDataDatabaseAccessor;
import com.trainingdataprocessor.factories.BigramDataListFactory;
import com.trainingdataprocessor.factories.SubPathDataListFactory;
import com.trainingdataprocessor.paths.EncodedPathsProcessorImpl;
import com.trainingdataprocessor.preprocessing.TrainingDataPreprocessor;
import com.trainingdataprocessor.semantics.analysis.SemanticAnalyserImpl;
import com.trainingdataprocessor.semantics.analysis.SemanticAnalysisExecutor;
import com.trainingdataprocessor.syntax.SyntaxAnalyserImpl;
import com.trainingdataprocessor.tags.TagsProcessor;
import com.trainingdataprocessor.tokens.TokenTagDataProcessorImpl;
import com.trainingdataprocessor.tokens.Tokenizer;
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

    private Tokenizer tokenizer;

    private TagsProcessor tagsProcessor;

    private BigramDataListFactory bigramDataListFactory;

    private SubPathDataListFactory subPathDataListFactory;

    private SemanticAnalysisExecutor semanticAnalysisExecutor;

    private static int NUMBER_OF_THREADS = 4;

    public NlpTrainingDataProcessor(TrainingDataPreprocessor trainingDataPreprocessor, TrainingDataDatabaseAccessor trainingDataDatabaseAccessor, Tokenizer tokenizer, TagsProcessor tagsProcessor,
                                    BigramDataListFactory bigramDataListFactory, SubPathDataListFactory subPathDataListFactory, SemanticAnalysisExecutor semanticAnalysisExecutor) {
        this.trainingDataPreprocessor = trainingDataPreprocessor;
        this.trainingDataDatabaseAccessor = trainingDataDatabaseAccessor;
        this.tokenizer = tokenizer;
        this.tagsProcessor = tagsProcessor;
        this.bigramDataListFactory = bigramDataListFactory;
        this.subPathDataListFactory = subPathDataListFactory;
        this.semanticAnalysisExecutor = semanticAnalysisExecutor;
    }

    public static void main(String[] args) {
        final ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        final NlpTrainingDataProcessor nlpTrainingDataProcessor = (NlpTrainingDataProcessor) context.getBean("nlpTrainingDataProcessor");
        nlpTrainingDataProcessor.process();
    }

    public void process() {
        boolean areDataProcessed = false;
        long startTime = System.currentTimeMillis();

        List<TrainingDataRow> trainingDataRowList = trainingDataPreprocessor.preprocess();

        tagsProcessor.process(trainingDataRowList);

        ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        Runnable encodedPathsProcessor = new EncodedPathsProcessorImpl(tokenizer, trainingDataDatabaseAccessor, trainingDataRowList);
        Runnable syntaxAnalyser = new SyntaxAnalyserImpl(trainingDataDatabaseAccessor, bigramDataListFactory, subPathDataListFactory, trainingDataRowList);
        Runnable semanticAnalyser = new SemanticAnalyserImpl(semanticAnalysisExecutor, trainingDataDatabaseAccessor, trainingDataRowList);
        Runnable tokenTagDataProcessor = new TokenTagDataProcessorImpl(trainingDataDatabaseAccessor, trainingDataRowList);

        executor.execute(encodedPathsProcessor);
        executor.execute(syntaxAnalyser);
//        executor.execute(semanticAnalyser);
        executor.execute(tokenTagDataProcessor);

        executor.shutdown();


//        EncodedPathsProcessor encodedPathsProcessor = new EncodedPathsProcessorImpl(tokenizer, trainingDataDatabaseAccessor, trainingDataRowList);
//        SyntaxAnalyser syntaxAnalyser = new SyntaxAnalyserImpl(trainingDataDatabaseAccessor, bigramDataListFactory, subPathDataListFactory, trainingDataRowList);
//        SemanticAnalyser semanticAnalyser = new SemanticAnalyserImpl(semanticAnalysisExecutor, trainingDataDatabaseAccessor, trainingDataRowList);
//        TokenTagDataProcessor tokenTagDataProcessor = new TokenTagDataProcessorImpl(trainingDataDatabaseAccessor, trainingDataRowList);
//
//        encodedPathsProcessor.process();
//        syntaxAnalyser.analyse();
//        semanticAnalyser.analyse();
//        tokenTagDataProcessor.process();

//        Future<?> encodedPathsAnalyserFuture = executor.submit(encodedPathsProcessor);
//        Future<?> syntaxAnalyserFuture = executor.submit(syntaxAnalyser);
//        Future<?> semanticAnalyserFuture = executor.submit(semanticAnalyser);
//        Future<?> tokenTagDataProcessorFuture = executor.submit(tokenTagDataProcessor);

//        while (!areDataProcessed) {
//            areDataProcessed = encodedPathsAnalyserFuture.isDone() && syntaxAnalyserFuture.isDone() &&
//                    semanticAnalyserFuture.isDone() && tokenTagDataProcessorFuture.isDone();
//        }
//
//        if (areDataProcessed) {
//            executor.shutdown();
//            long stopTime = System.currentTimeMillis();
//            long elapsedTime = stopTime - startTime;
//            System.out.println("Data processed in " + elapsedTime / 1000 + " seconds");
//        }
    }
}