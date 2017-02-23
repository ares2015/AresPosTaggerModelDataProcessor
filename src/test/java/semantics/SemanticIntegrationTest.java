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

    @Test
    public void test4() {
        String sentence = "Its head was so bright that as it flew through the air it made the lightning";
        List<String> tokensList = Arrays.asList(sentence.split("\\ "));
        String tags = "PRPS N IA CJ AJ WP PR PRP Ved CJ DET N PRP Ved DET N";
        List<String> tagsList = Arrays.asList(tags.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokensList, tagsList);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("head", semanticExtractionData.getAtomicSubject());
        assertEquals("", semanticExtractionData.getExtendedSubject());
        assertEquals("was", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("", semanticExtractionData.getAtomicNounPredicate());
        assertEquals("so bright ", semanticExtractionData.getExtendedNounPredicate());
    }

    @Test
    public void test5() {
        String sentence = "The sorrow of all living things at his death means the gloom of northern countries when winter comes";
        List<String> tokensList = Arrays.asList(sentence.split("\\ "));
        String tags = "DET N PR Q Ving N PR PRPS N V DET N PR AJ N WAV N V";
        List<String> tagsList = Arrays.asList(tags.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokensList, tagsList);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("", semanticExtractionData.getAtomicSubject());
        assertEquals("sorrow of all living things at death ", semanticExtractionData.getExtendedSubject());
        assertEquals("means", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("gloom", semanticExtractionData.getAtomicNounPredicate());
        assertEquals("gloom of northern countries ", semanticExtractionData.getExtendedNounPredicate());
    }

    @Test
    public void test6() {
        String sentence = "The earth will be shaken as when there is a great earthquake the waves of the sea will roar and the highest mountains will totter and fall";
        List<String> tokensList = Arrays.asList(sentence.split("\\ "));
        String tags = "DET N MV IA Ved PR WAV T IA DET AJ N DET N PR DET N MV N AO DET AJ N MV V AO V";
        List<String> tagsList = Arrays.asList(tags.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokensList, tagsList);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("earth", semanticExtractionData.getAtomicSubject());
        assertEquals("earth ", semanticExtractionData.getExtendedSubject());
        assertEquals("be", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("will be ", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("shaken", semanticExtractionData.getAtomicNounPredicate());
        assertEquals("shaken ", semanticExtractionData.getExtendedNounPredicate());
    }
}
