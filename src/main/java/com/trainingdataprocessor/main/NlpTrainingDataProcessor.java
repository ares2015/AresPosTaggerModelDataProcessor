package com.trainingdataprocessor.main;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.database.TrainingDataDatabaseAccessor;
import com.trainingdataprocessor.factories.bigram.BigramDataListFactory;
import com.trainingdataprocessor.factories.subpath.SubPathDataListFactory;
import com.trainingdataprocessor.preprocessing.TrainingDataPreprocessor;
import com.trainingdataprocessor.tokens.Tokenizer;
import com.trainingdataprocessor.writer.bigrams.BigramsWriter;
import com.trainingdataprocessor.writer.subpaths.SubPathsWriter;
import com.trainingdataprocessor.writer.tags.TagsWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Oliver on 11/12/2016.
 */
public class NlpTrainingDataProcessor {

    private TrainingDataPreprocessor trainingDataPreprocessor;

    private TagsWriter tagsWriter;

    private BigramsWriter bigramsWriter;

    private SubPathsWriter subPathsWriter;

    private TrainingDataDatabaseAccessor trainingDataDatabaseAccessor;

    private Tokenizer tokenizer;

    private BigramDataListFactory bigramDataListFactory;

    private SubPathDataListFactory subPathDataListFactory;

    private static int NUMBER_OF_THREADS = 4;

    public NlpTrainingDataProcessor(TrainingDataPreprocessor trainingDataPreprocessor, TagsWriter tagsWriter, BigramsWriter bigramsWriter,
                                    SubPathsWriter subPathsWriter, TrainingDataDatabaseAccessor trainingDataDatabaseAccessor,
                                    Tokenizer tokenizer, BigramDataListFactory bigramDataListFactory,
                                    SubPathDataListFactory subPathDataListFactory) {
        this.trainingDataPreprocessor = trainingDataPreprocessor;
        this.tagsWriter = tagsWriter;
        this.bigramsWriter = bigramsWriter;
        this.subPathsWriter = subPathsWriter;
        this.trainingDataDatabaseAccessor = trainingDataDatabaseAccessor;
        this.tokenizer = tokenizer;
        this.bigramDataListFactory = bigramDataListFactory;
        this.subPathDataListFactory = subPathDataListFactory;
    }

    public static void main(String[] args) {
        final ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        final NlpTrainingDataProcessor nlpTrainingDataProcessor = (NlpTrainingDataProcessor) context.getBean("nlpTrainingDataProcessor");
        nlpTrainingDataProcessor.process();
    }

    public void process() {
//        boolean areDataProcessed = false;
//        long startTime = System.currentTimeMillis();
//
        List<TrainingDataRow> trainingDataRowList = trainingDataPreprocessor.preprocess();
//
//        tagsWriter.write(trainingDataRowList);
//
//        ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
//
//        Runnable encodedPathsWriter = new EncodedPathsWriterImpl(trainingDataRowList);
//        Runnable syntaxAnalyser = new SyntaxAnalyserImpl(bigramsWriter, subPathsWriter, bigramDataListFactory, subPathDataListFactory, trainingDataRowList);
//        Runnable semanticExtractor = new SemanticAnalyserImpl(semanticAnalysisExecutor, semanticPreprocessingFilter, trainingDataDatabaseAccessor, trainingDataRowList);
//        Runnable tokenTagsWriter = new TokenTagsWriterImpl(trainingDataRowList);
//
//
//        Future<?> encodedPathsFuture = executor.submit(encodedPathsWriter);
//        Future<?> syntaxAnalyserFuture = executor.submit(syntaxAnalyser);
//        Future<?> semanticAnalyserFuture = executor.submit(semanticExtractor);
//        Future<?> tokenTagDataFuture = executor.submit(tokenTagsWriter);
//
//        while (!areDataProcessed) {
//            areDataProcessed = encodedPathsFuture.isDone() && syntaxAnalyserFuture.isDone() &&
//                    semanticAnalyserFuture.isDone() &&
//                    tokenTagDataFuture.isDone();
//        }
//
//        if (areDataProcessed) {
//            executor.shutdown();
//            long stopTime = System.currentTimeMillis();
//            long elapsedTime = stopTime - startTime;
//            System.out.println(trainingDataRowList.size() + " training data rows processed in " + (elapsedTime / 1000) / 60 + " minutes and "
//                    + +(elapsedTime / 1000) % 60 + " seconds");
//        }

//        SemanticExtractor semanticExtractor = new SemanticAnalyserImpl(semanticAnalysisExecutor, semanticPreprocessingFilter, trainingDataDatabaseAccessor, trainingDataRowList);
//        semanticExtractor.extract();
    }
}