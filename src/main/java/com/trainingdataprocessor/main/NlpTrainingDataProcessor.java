package com.trainingdataprocessor.main;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.database.TrainingDataDatabaseAccessor;
import com.trainingdataprocessor.factories.BigramDataListFactory;
import com.trainingdataprocessor.factories.SubPathDataListFactory;
import com.trainingdataprocessor.paths.EncodedPathsProcessorImpl;
import com.trainingdataprocessor.preprocessing.TrainingDataPreprocessor;
import com.trainingdataprocessor.semantics.analysis.SemanticAnalyserImpl;
import com.trainingdataprocessor.semantics.extraction.SemanticExtractor;
import com.trainingdataprocessor.semantics.preprocessing.SemanticPreprocessor;
import com.trainingdataprocessor.syntax.SyntaxAnalyserImpl;
import com.trainingdataprocessor.tags.TagsProcessor;
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

    private TagsProcessor tagsProcessor;

    private BigramDataListFactory bigramDataListFactory;

    private SubPathDataListFactory subPathDataListFactory;

    private SemanticPreprocessor semanticPreprocessor;

    private SemanticExtractor semanticExtractor;

    private static int NUMBER_OF_THREADS = 4;

    public NlpTrainingDataProcessor(TrainingDataPreprocessor trainingDataPreprocessor, TrainingDataDatabaseAccessor trainingDataDatabaseAccessor,
                                    TagsProcessor tagsProcessor,
                                    BigramDataListFactory bigramDataListFactory, SubPathDataListFactory subPathDataListFactory,
                                    SemanticPreprocessor semanticPreprocessor, SemanticExtractor semanticExtractor) {
        this.trainingDataPreprocessor = trainingDataPreprocessor;
        this.trainingDataDatabaseAccessor = trainingDataDatabaseAccessor;
        this.tagsProcessor = tagsProcessor;
        this.bigramDataListFactory = bigramDataListFactory;
        this.subPathDataListFactory = subPathDataListFactory;
        this.semanticPreprocessor = semanticPreprocessor;
        this.semanticExtractor = semanticExtractor;
    }

    public static void main(String[] args) {
        final ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        final NlpTrainingDataProcessor nlpTrainingDataProcessor = (NlpTrainingDataProcessor) context.getBean("nlpTrainingDataProcessor");
        nlpTrainingDataProcessor.process();
    }


    public void process() {
        long startTime = System.currentTimeMillis();
        List<TrainingDataRow> trainingDataRowList = trainingDataPreprocessor.preprocess();

        tagsProcessor.process(trainingDataRowList);

        ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        Runnable encodedPathsProcessor = new EncodedPathsProcessorImpl(trainingDataDatabaseAccessor, trainingDataRowList);
        Runnable syntaxAnalyser = new SyntaxAnalyserImpl(trainingDataDatabaseAccessor, bigramDataListFactory, subPathDataListFactory, trainingDataRowList);
        Runnable semanticAnalyser = new SemanticAnalyserImpl(semanticPreprocessor, semanticExtractor, trainingDataDatabaseAccessor, trainingDataRowList);
        Runnable tokenTagDataProcessor = new TokenTagDataProcessorImpl(trainingDataDatabaseAccessor, trainingDataRowList);

        executor.execute(encodedPathsProcessor);
        executor.execute(syntaxAnalyser);
        executor.execute(semanticAnalyser);
        executor.execute(tokenTagDataProcessor);

        executor.shutdown();
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Data processed in "  + elapsedTime + " miliseconds");
    }
}