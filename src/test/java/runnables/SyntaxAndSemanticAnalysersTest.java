package runnables;

import com.trainingdataprocessor.cache.SemanticAnalysisFilterCache;
import com.trainingdataprocessor.data.preprocessing.TrainingDataRow;
import com.trainingdataprocessor.database.TrainingDataDatabaseAccessor;
import com.trainingdataprocessor.factories.BigramDataListFactory;
import com.trainingdataprocessor.factories.BigramDataListFactoryImpl;
import com.trainingdataprocessor.factories.StartTagEndTagPairsListFactory;
import com.trainingdataprocessor.factories.StartTagEndTagPairsListFactoryImpl;
import com.trainingdataprocessor.regex.RegexPatternSearcher;
import com.trainingdataprocessor.regex.RegexPatternSearcherImpl;
import com.trainingdataprocessor.semantics.analysis.SemanticAnalyserImpl;
import com.trainingdataprocessor.semantics.extraction.SemanticExtractor;
import com.trainingdataprocessor.semantics.extraction.SemanticExtractorImpl;
import com.trainingdataprocessor.semantics.extraction.phrases.NounPhraseExtractorImpl;
import com.trainingdataprocessor.semantics.extraction.phrases.PhraseExtractor;
import com.trainingdataprocessor.semantics.extraction.phrases.PrepositionPhraseExtractorImpl;
import com.trainingdataprocessor.semantics.extraction.phrases.VerbPhraseExtractorImpl;
import com.trainingdataprocessor.semantics.preprocessing.SemanticPreprocessingFilter;
import com.trainingdataprocessor.semantics.preprocessing.SemanticPreprocessingFilterImpl;
import com.trainingdataprocessor.semantics.preprocessing.SemanticPreprocessor;
import com.trainingdataprocessor.semantics.preprocessing.SemanticPreprocessorImpl;
import com.trainingdataprocessor.semantics.preprocessing.phrases.NounPhrasePreprocessorImpl;
import com.trainingdataprocessor.semantics.preprocessing.phrases.PhrasePreprocessor;
import com.trainingdataprocessor.semantics.preprocessing.phrases.PrepositionPhrasePreprocessorImpl;
import com.trainingdataprocessor.semantics.preprocessing.phrases.VerbPhrasePreprocessorImpl;
import com.trainingdataprocessor.syntax.SyntaxAnalyserImpl;
import com.trainingdataprocessor.tags.EncodedTags;
import com.trainingdataprocessor.tags.Tags;
import com.trainingdataprocessor.tokens.Tokenizer;
import com.trainingdataprocessor.tokens.TokenizerImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Oliver on 11/12/2016.
 */
public class SyntaxAndSemanticAnalysersTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
    TrainingDataDatabaseAccessor trainingDataDatabaseAccessor = (TrainingDataDatabaseAccessor) context.getBean("trainingDataDatabaseAccessor");

    BigramDataListFactory bigramDataListFactory = new BigramDataListFactoryImpl();

    StartTagEndTagPairsListFactory startTagEndTagPairsListFactory = new StartTagEndTagPairsListFactoryImpl();


    private Tokenizer tokenizer = new TokenizerImpl();
    private SemanticAnalysisFilterCache semanticAnalysisFilterCache = new SemanticAnalysisFilterCache();
    private SemanticPreprocessingFilter semanticPreprocessingFilter = new SemanticPreprocessingFilterImpl();
    private RegexPatternSearcher regexPatternSearcher = new RegexPatternSearcherImpl();
    private PhrasePreprocessor prepositionPhrasePreprocessor = new PrepositionPhrasePreprocessorImpl(regexPatternSearcher);
    private PhrasePreprocessor nounPhrasePreprocessor = new NounPhrasePreprocessorImpl(regexPatternSearcher);
    private PhrasePreprocessor verbPhrasePreprocessor = new VerbPhrasePreprocessorImpl(regexPatternSearcher);
    private SemanticPreprocessor semanticPreprocessor = new SemanticPreprocessorImpl(semanticPreprocessingFilter, prepositionPhrasePreprocessor, nounPhrasePreprocessor, verbPhrasePreprocessor);
    private PhraseExtractor prepositionPhraseExtractor = new PrepositionPhraseExtractorImpl(tokenizer);
    private PhraseExtractor nounPhraseExtractor = new NounPhraseExtractorImpl(tokenizer);
    private PhraseExtractor verbPhraseExtractor = new VerbPhraseExtractorImpl(tokenizer);
    private SemanticExtractor semanticExtractor = new SemanticExtractorImpl(prepositionPhraseExtractor, nounPhraseExtractor, verbPhraseExtractor);

    @Test
    public void test() throws InterruptedException {
        String sentence = "King George visited Hanover again from May to November 1719";
        String encodedPath = "NNVNAPNPN#";

        List<String> tagsList = new ArrayList<String>();
        tagsList.add(Tags.NOUN);
        tagsList.add(Tags.NOUN);
        tagsList.add(Tags.VERB);
        tagsList.add(Tags.NOUN);
        tagsList.add(Tags.ADVERB);
        tagsList.add(Tags.PREPOSITION);
        tagsList.add(Tags.NOUN);
        tagsList.add(Tags.PREPOSITION);
        tagsList.add(Tags.NOUN);
        tagsList.add(Tags.NUMBER);

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NUMBER);
        List<String> tokensList = Arrays.asList(sentence.split("\\ "));


        List<TrainingDataRow> trainingDataRowList = new ArrayList<>();
        TrainingDataRow trainingDataRow = new TrainingDataRow();
        trainingDataRow.setTagsList(tagsList);
        trainingDataRow.setEncodedPath(encodedPath);
        trainingDataRow.setEncodedTagsList(encodedTags);
        trainingDataRow.setTokensList(tokensList);


        trainingDataRowList.add(trainingDataRow);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Runnable syntaxAnalyser = new SyntaxAnalyserImpl(trainingDataDatabaseAccessor, bigramDataListFactory, startTagEndTagPairsListFactory, trainingDataRowList);
        Runnable semanticAnalyser = new SemanticAnalyserImpl(semanticPreprocessor, semanticExtractor, trainingDataDatabaseAccessor, trainingDataRowList);

        executor.execute(syntaxAnalyser);
        executor.execute(semanticAnalyser);

//        String sql = "select max(id) from jos_nlp_semantic_data";
//        int id = jdbcTemplate.queryForInt(sql);
//
//        sql = "delete from jos_nlp_semantic_data where id = ?";
//        jdbcTemplate.update(sql,  new Object[]{id});

        Thread.sleep(7000);
    }





}


