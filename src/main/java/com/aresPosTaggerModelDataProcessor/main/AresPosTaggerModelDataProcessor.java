package com.aresPosTaggerModelDataProcessor.main;

import com.aresPosTaggerModelDataProcessor.data.preprocessing.ModelDataRow;
import com.aresPosTaggerModelDataProcessor.factories.bigram.BigramDataListFactory;
import com.aresPosTaggerModelDataProcessor.factories.subpath.SubPathDataListFactory;
import com.aresPosTaggerModelDataProcessor.morphology.MorphemesDetector;
import com.aresPosTaggerModelDataProcessor.preprocessing.ModelDataPreprocessor;
import com.aresPosTaggerModelDataProcessor.syntax.SyntaxAnalyserImpl;
import com.aresPosTaggerModelDataProcessor.writer.bigrams.BigramsWriter;
import com.aresPosTaggerModelDataProcessor.writer.morphology.SuffixesWriterImpl;
import com.aresPosTaggerModelDataProcessor.writer.subpaths.SubPathsWriter;
import com.aresPosTaggerModelDataProcessor.writer.tags.TagsWriter;
import com.aresPosTaggerModelDataProcessor.writer.tokens.TokenTagsWriterImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Oliver on 11/12/2016.
 */
public class AresPosTaggerModelDataProcessor {

    private ModelDataPreprocessor modelDataPreprocessor;

    private TagsWriter tagsWriter;

    private BigramsWriter bigramsWriter;

    private SubPathsWriter subPathsWriter;

    private BigramDataListFactory bigramDataListFactory;

    private SubPathDataListFactory subPathDataListFactory;

    private MorphemesDetector morphemesDetector;

    private static int NUMBER_OF_THREADS = 5;

    public AresPosTaggerModelDataProcessor(ModelDataPreprocessor modelDataPreprocessor, TagsWriter tagsWriter,
                                           BigramsWriter bigramsWriter, SubPathsWriter subPathsWriter,
                                           BigramDataListFactory bigramDataListFactory, SubPathDataListFactory subPathDataListFactory,
                                           MorphemesDetector morphemesDetector) {
        this.modelDataPreprocessor = modelDataPreprocessor;
        this.tagsWriter = tagsWriter;
        this.bigramsWriter = bigramsWriter;
        this.subPathsWriter = subPathsWriter;
        this.bigramDataListFactory = bigramDataListFactory;
        this.subPathDataListFactory = subPathDataListFactory;
        this.morphemesDetector = morphemesDetector;
    }

    public static void main(String[] args) throws InterruptedException {
        final ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        final AresPosTaggerModelDataProcessor aresPosTaggerModelDataProcessor = (AresPosTaggerModelDataProcessor) context.getBean("aresPosTaggerModelDataProcessor");
        aresPosTaggerModelDataProcessor.process();
    }

    public void process() throws InterruptedException {
        boolean areDataProcessed = false;
        long startTime = System.currentTimeMillis();

        List<ModelDataRow> modelDataRowList = modelDataPreprocessor.preprocess();

        tagsWriter.write(modelDataRowList);

        ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

//        EncodedPathsWriter encodedPathsWriter = new EncodedPathsWriterImpl(modelDataRowList);
//        encodedPathsWriter.write();
//
//        SyntaxAnalyser syntaxAnalyser = new SyntaxAnalyserImpl(bigramsWriter, subPathsWriter, bigramDataListFactory, subPathDataListFactory, modelDataRowList);
//        syntaxAnalyser.analyse();
//
//
//        SemanticAnalyser semanticAnalyser = new SemanticAnalyserImpl(semanticsWriter, modelDataRowList);
//        semanticAnalyser.analyse();
//
//
//        TokenTagsWriter tokenTagsWriter = new TokenTagsWriterImpl(modelDataRowList);
//        tokenTagsWriter.write();
//
//        SuffixesWriter suffixesWriter = new SuffixesWriterImpl(morphemesDetector, modelDataRowList);
//        suffixesWriter.write();


        Runnable syntaxAnalyser = new SyntaxAnalyserImpl(bigramsWriter, subPathsWriter, bigramDataListFactory, subPathDataListFactory, modelDataRowList);
        Runnable tokenTagsWriter = new TokenTagsWriterImpl(modelDataRowList);
        Runnable suffixesWriter = new SuffixesWriterImpl(morphemesDetector, modelDataRowList);


        Future<?> syntaxAnalyserFuture = executor.submit(syntaxAnalyser);
        Future<?> tokenTagDataFuture = executor.submit(tokenTagsWriter);
        Future<?> suffixesWriterFuture = executor.submit(suffixesWriter);

        while (!areDataProcessed) {
            areDataProcessed = syntaxAnalyserFuture.isDone() && tokenTagDataFuture.isDone() && suffixesWriterFuture.isDone();
        }

        if (areDataProcessed) {
            executor.shutdown();
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            System.out.println(modelDataRowList.size() + " training data rows processed in " + (elapsedTime / 1000) / 60 + " minutes and "
                    + +(elapsedTime / 1000) % 60 + " seconds");
        }
    }
}
