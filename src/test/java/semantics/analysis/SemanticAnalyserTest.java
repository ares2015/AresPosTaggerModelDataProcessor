package semantics.analysis;

import com.trainingdataprocessor.cache.SemanticAnalysisFilterCache;
import com.trainingdataprocessor.data.TestDataRow;
import com.trainingdataprocessor.database.TrainingDataAccessor;
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
import com.trainingdataprocessor.tags.EncodedTags;
import com.trainingdataprocessor.tokenizing.Tokenizer;
import com.trainingdataprocessor.tokenizing.TokenizerImpl;
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
 * Created by Oliver on 11/7/2016.
 */
public class SemanticAnalyserTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
    TrainingDataAccessor trainingDataAccessor = (TrainingDataAccessor) context.getBean("trainingDataAccessor");

    private Tokenizer tokenizer = new TokenizerImpl();
    private SemanticAnalysisFilterCache semanticAnalysisFilterCache = new SemanticAnalysisFilterCache();
    private SemanticPreprocessingFilter semanticPreprocessingFilter = new SemanticPreprocessingFilterImpl(semanticAnalysisFilterCache, tokenizer);
    private RegexPatternSearcher regexPatternSearcher = new RegexPatternSearcherImpl();
    private PhrasePreprocessor prepositionPhrasePreprocessor = new PrepositionPhrasePreprocessorImpl(regexPatternSearcher);
    private PhrasePreprocessor nounPhrasePreprocessor = new NounPhrasePreprocessorImpl(regexPatternSearcher);
    private PhrasePreprocessor verbPhrasePreprocessor = new VerbPhrasePreprocessorImpl(regexPatternSearcher);
    private SemanticPreprocessor semanticPreprocessor = new SemanticPreprocessorImpl(semanticPreprocessingFilter, prepositionPhrasePreprocessor, nounPhrasePreprocessor, verbPhrasePreprocessor);
    private PhraseExtractor prepositionPhraseExtractor = new PrepositionPhraseExtractorImpl(tokenizer);
    private PhraseExtractor nounPhraseExtractor = new NounPhraseExtractorImpl(tokenizer);
    private PhraseExtractor verbPhraseExtractor = new VerbPhraseExtractorImpl(tokenizer);
    private SemanticExtractor semanticExtractor = new SemanticExtractorImpl(prepositionPhraseExtractor, nounPhraseExtractor, verbPhraseExtractor);
//    private TrainingDataAccessor trainingDataAccessor = new TrainingDataAccessorMock();


    @Test
    public void test() throws InterruptedException {
        String encodedPath = "NNVNAPNPN#";
        String sentence = "King George visited Hanover again from May to November 1719";
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
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        List<TestDataRow> testDataRowList = new ArrayList<>();
        TestDataRow testDataRow = new TestDataRow();
        testDataRow.setEncodedPath(encodedPath);
        testDataRow.setEncodedTagsList(encodedTags);
        testDataRow.setTokensList(tokens);

        testDataRowList.add(testDataRow);

        Runnable semanticAnalyser = new SemanticAnalyserImpl(semanticPreprocessor, semanticExtractor, trainingDataAccessor, new SemanticAnalysisFilterCache(), testDataRowList);
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(semanticAnalyser);

        String sql = "select max(id) from jos_nlp_semantic_data";
        int id = jdbcTemplate.queryForInt(sql);

        sql = "delete from jos_nlp_semantic_data where id = ?";
        jdbcTemplate.update(sql,  new Object[]{id});

        Thread.sleep(5000);
    }

}
