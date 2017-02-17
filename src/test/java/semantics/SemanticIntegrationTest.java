package semantics;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.semantics.extraction.*;
import com.trainingdataprocessor.semantics.preprocessing.SemanticPreprocessor;
import com.trainingdataprocessor.semantics.preprocessing.SemanticPreprocessorImpl;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oliver on 2/17/2017.
 */
public class SemanticIntegrationTest {

    private SubjectExtractor subjectExtractor = new SubjectExtractorImpl();

    private VerbPredicateExtractor verbPredicateExtractor = new VerbPredicateExtractorImpl();

    private NounPredicateExtractor nounPredicateExtractor = new NounPredicateExtractorImpl();

    private SemanticExtractor semanticExtractor = new SemanticExtractorImpl(subjectExtractor, verbPredicateExtractor, nounPredicateExtractor);

    private SemanticPreprocessor semanticPreprocessor = new SemanticPreprocessorImpl();

    @Test
    public void test() {
        String sentence = "Mr Lavrov was quoted as saying by the Reuters news agency";
        List<String> tokensList = Arrays.asList(sentence.split("\\ "));
        String tags = "N N IA Ved PR Ving PR DET N N N";
        List<String> tagsList = Arrays.asList(tags.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokensList, tagsList);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("Lavrov", semanticExtractionData.getAtomicSubject());
        assertEquals("Mr Lavrov ", semanticExtractionData.getExtendedSubject());
        assertEquals("was", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("quoted as saying by Reuters news agency ", semanticExtractionData.getExtendedNounPredicate());
    }

    @Test
    public void test2() {
        String sentence = "The play was first produced in 1934 in Los Angeles under the title Woman on Trial";
        List<String> tokensList = Arrays.asList(sentence.split("\\ "));
        String tags = "DET N IA AV Ved PR NR PR N N PR DET N N PR N";
        List<String> tagsList = Arrays.asList(tags.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokensList, tagsList);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("play", semanticExtractionData.getAtomicSubject());
        assertEquals("", semanticExtractionData.getExtendedSubject());
        assertEquals("was", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("produced", semanticExtractionData.getAtomicNounPredicate());
        assertEquals("first produced in 1934 in Los Angeles under title Woman on Trial ", semanticExtractionData.getExtendedNounPredicate());
    }

    @Test
    public void test3() {
        String sentence = "A drone looks like a conflation of a giant insect and a light aircraft";
        List<String> tokensList = Arrays.asList(sentence.split("\\ "));
        String tags = "DET N V PR DET N PR DET AJ N AO DET AJ N";
        List<String> tagsList = Arrays.asList(tags.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokensList, tagsList);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("drone", semanticExtractionData.getAtomicSubject());
        assertEquals("", semanticExtractionData.getExtendedSubject());
        assertEquals("looks", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("", semanticExtractionData.getAtomicNounPredicate());
        assertEquals("like conflation of giant insect ", semanticExtractionData.getExtendedNounPredicate());
    }
}
