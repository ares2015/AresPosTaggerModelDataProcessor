package semantics;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.semantics.SubjectExtractor;
import com.trainingdataprocessor.semantics.SubjectExtractorImpl;
import com.trainingdataprocessor.tags.EncodedTags;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oliver on 2/16/2017.
 */
public class SubjectExtractorTest {

    private SubjectExtractor subjectExtractor = new SubjectExtractorImpl();

    @Test
    public void test() {
        List<String> encodedTags = new ArrayList<>();

        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);

        String sentence = "brave firemen fight furiously forest fire";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));
        SemanticExtractionData semanticExtractionData = new SemanticExtractionData();
        subjectExtractor.extract(semanticExtractionData, tokens, encodedTags, 2, -1);
        assertEquals("brave firemen ", semanticExtractionData.getExtendedSubject());
        assertEquals("firemen", semanticExtractionData.getAtomicSubject());
    }

    @Test
    public void testWithPreposition() {
        List<String> encodedTags = new ArrayList<>();

        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);

        String sentence = "brave firemen in California fight furiously forest fire";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));
        SemanticExtractionData semanticExtractionData = new SemanticExtractionData();
        subjectExtractor.extract(semanticExtractionData, tokens, encodedTags, 4, 2);
        assertEquals("brave firemen in California ", semanticExtractionData.getExtendedSubject());
        assertEquals("", semanticExtractionData.getAtomicSubject());
    }
}
