package com.trainingdataprocessor.main;

import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.factories.bigram.BigramDataListFactory;
import com.trainingdataprocessor.factories.subpath.SubPathDataListFactory;
import com.trainingdataprocessor.morphology.MorphemesDetector;
import com.trainingdataprocessor.preprocessing.TrainingDataPreprocessor;
import com.trainingdataprocessor.semantics.analysis.SemanticAnalyserImpl;
import com.trainingdataprocessor.syntax.SyntaxAnalyserImpl;
import com.trainingdataprocessor.writer.bigrams.BigramsWriter;
import com.trainingdataprocessor.writer.morphology.SuffixesWriterImpl;
import com.trainingdataprocessor.writer.paths.EncodedPathsWriterImpl;
import com.trainingdataprocessor.writer.semantics.SemanticsWriter;
import com.trainingdataprocessor.writer.subpaths.SubPathsWriter;
import com.trainingdataprocessor.writer.tags.TagsWriter;
import com.trainingdataprocessor.writer.tokens.TokenTagsWriterImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Oliver on 11/12/2016.
 */
public class NlpTrainingDataProcessor {

    private TrainingDataPreprocessor trainingDataPreprocessor;

    private TagsWriter tagsWriter;

    private BigramsWriter bigramsWriter;

    private SemanticsWriter semanticsWriter;

    private SubPathsWriter subPathsWriter;

    private BigramDataListFactory bigramDataListFactory;

    private SubPathDataListFactory subPathDataListFactory;

    private MorphemesDetector morphemesDetector;

    private static int NUMBER_OF_THREADS = 5;

    public NlpTrainingDataProcessor(TrainingDataPreprocessor trainingDataPreprocessor, TagsWriter tagsWriter, BigramsWriter bigramsWriter,
                                    SemanticsWriter semanticsWriter, SubPathsWriter subPathsWriter,
                                    BigramDataListFactory bigramDataListFactory, SubPathDataListFactory subPathDataListFactory,
                                    MorphemesDetector morphemesDetector) {
        this.trainingDataPreprocessor = trainingDataPreprocessor;
        this.tagsWriter = tagsWriter;
        this.bigramsWriter = bigramsWriter;
        this.semanticsWriter = semanticsWriter;
        this.subPathsWriter = subPathsWriter;
        this.bigramDataListFactory = bigramDataListFactory;
        this.subPathDataListFactory = subPathDataListFactory;
        this.morphemesDetector = morphemesDetector;
    }

    public static void main(String[] args) throws InterruptedException {
        final ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        final NlpTrainingDataProcessor nlpTrainingDataProcessor = (NlpTrainingDataProcessor) context.getBean("nlpTrainingDataProcessor");
        nlpTrainingDataProcessor.process();
    }

    public void process() throws InterruptedException {
        boolean areDataProcessed = false;
        long startTime = System.currentTimeMillis();

        List<TrainingDataRow> trainingDataRowList = trainingDataPreprocessor.preprocess();

        tagsWriter.write(trainingDataRowList);

        ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

//        EncodedPathsWriter encodedPathsWriter = new EncodedPathsWriterImpl(trainingDataRowList);
//        encodedPathsWriter.write();
//
//        SyntaxAnalyser syntaxAnalyser = new SyntaxAnalyserImpl(bigramsWriter, subPathsWriter, bigramDataListFactory, subPathDataListFactory, trainingDataRowList);
//        syntaxAnalyser.analyse();
//
//
//        SemanticAnalyser semanticAnalyser = new SemanticAnalyserImpl(semanticsWriter, trainingDataRowList);
//        semanticAnalyser.analyse();
//
//
//        TokenTagsWriter tokenTagsWriter = new TokenTagsWriterImpl(trainingDataRowList);
//        tokenTagsWriter.write();
//
//        SuffixesWriter suffixesWriter = new SuffixesWriterImpl(morphemesDetector, trainingDataRowList);
//        suffixesWriter.write();

        Runnable encodedPathsWriter = new EncodedPathsWriterImpl(trainingDataRowList);
        Runnable syntaxAnalyser = new SyntaxAnalyserImpl(bigramsWriter, subPathsWriter, bigramDataListFactory, subPathDataListFactory, trainingDataRowList);
        Runnable semanticAnalyser = new SemanticAnalyserImpl(semanticsWriter, trainingDataRowList);
        Runnable tokenTagsWriter = new TokenTagsWriterImpl(trainingDataRowList);
        Runnable suffixesWriter = new SuffixesWriterImpl(morphemesDetector, trainingDataRowList);

        Future<?> encodedPathsFuture = executor.submit(encodedPathsWriter);
        Future<?> syntaxAnalyserFuture = executor.submit(syntaxAnalyser);
        Future<?> semanticAnalyserFuture = executor.submit(semanticAnalyser);
        Future<?> tokenTagDataFuture = executor.submit(tokenTagsWriter);
        Future<?> suffixesWriterFuture = executor.submit(suffixesWriter);

        while (!areDataProcessed) {
            areDataProcessed = encodedPathsFuture.isDone() && syntaxAnalyserFuture.isDone() && semanticAnalyserFuture.isDone() &&
                    tokenTagDataFuture.isDone() && suffixesWriterFuture.isDone();
        }

        if (areDataProcessed) {
            executor.shutdown();
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            System.out.println(trainingDataRowList.size() + " training data rows processed in " + (elapsedTime / 1000) / 60 + " minutes and "
                    + +(elapsedTime / 1000) % 60 + " seconds");
        }
    }
}
