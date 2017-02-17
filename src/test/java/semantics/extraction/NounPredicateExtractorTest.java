package semantics.extraction;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.semantics.extraction.NounPredicateExtractor;
import com.trainingdataprocessor.semantics.extraction.NounPredicateExtractorImpl;
import com.trainingdataprocessor.tags.Tags;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oliver on 2/16/2017.
 */
public class NounPredicateExtractorTest {

    private NounPredicateExtractor nounPredicateExtractor = new NounPredicateExtractorImpl();

    @Test
    public void test() {

        List<String> tags = new ArrayList<>();

        tags.add(Tags.ADJECTIVE);
        tags.add(Tags.NOUN);
        tags.add(Tags.VERB);
        tags.add(Tags.ADVERB);
        tags.add(Tags.ADJECTIVE);
        tags.add(Tags.NOUN);
        tags.add(Tags.NOUN);

        String sentence = "brave firemen fight furiously dangerous forest fire";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();
        semanticPreprocessingData.setTokensList(tokens);
        semanticPreprocessingData.setTagsList(tags);
        semanticPreprocessingData.setVerbIndex(2);
        semanticPreprocessingData.setAfterVerbFirstPrepositionIndex(-1);
        SemanticExtractionData semanticExtractionData = new SemanticExtractionData();
        nounPredicateExtractor.extract(semanticExtractionData, semanticPreprocessingData);
        assertEquals("furiously dangerous forest fire ", semanticExtractionData.getExtendedNounPredicate());
        assertEquals("fire", semanticExtractionData.getAtomicNounPredicate());
    }

    @Test
    public void testWithAfterVerbPreposition() {

        List<String> tags = new ArrayList<>();

        tags.add(Tags.ADJECTIVE);
        tags.add(Tags.NOUN);
        tags.add(Tags.VERB);
        tags.add(Tags.ADVERB);
        tags.add(Tags.NOUN);
        tags.add(Tags.NOUN);
        tags.add(Tags.PREPOSITION);
        tags.add(Tags.NOUN);

        String sentence = "brave firemen fight furiously forest fire in California";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));
        SemanticExtractionData semanticExtractionData = new SemanticExtractionData();
        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();
        semanticPreprocessingData.setTokensList(tokens);
        semanticPreprocessingData.setTagsList(tags);
        semanticPreprocessingData.setVerbIndex(2);
        semanticPreprocessingData.setAfterVerbFirstPrepositionIndex(6);


        nounPredicateExtractor.extract(semanticExtractionData, semanticPreprocessingData);
        assertEquals("furiously forest fire in California ", semanticExtractionData.getExtendedNounPredicate());
        assertEquals("fire", semanticExtractionData.getAtomicNounPredicate());
    }
}
