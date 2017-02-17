package semantics.extraction;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;
import com.trainingdataprocessor.semantics.extraction.VerbPredicateExtractor;
import com.trainingdataprocessor.semantics.extraction.VerbPredicateExtractorImpl;
import com.trainingdataprocessor.tags.Tags;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oliver on 2/17/2017.
 */
public class VerbPredicateExtractorTest {

    private VerbPredicateExtractor verbPredicateExtractor = new VerbPredicateExtractorImpl();

    @Test
    public void test() {
        List<String> tags = new ArrayList<>();

        tags.add(Tags.NOUN);
        tags.add(Tags.MODAL_VERB);
        tags.add(Tags.VERB);
        tags.add(Tags.ADVERB);
        tags.add(Tags.ADVERB);
        tags.add(Tags.PREPOSITION);
        tags.add(Tags.DETERMINER);
        tags.add(Tags.NOUN);
        tags.add(Tags.PREPOSITION);
        tags.add(Tags.NOUN);

        String sentence = "cheetahs can run very quickly on the savannahs of Africa";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));
        SemanticExtractionData semanticExtractionData = new SemanticExtractionData();
        SemanticPreprocessingData semanticPreprocessingData = new SemanticPreprocessingData();
        semanticPreprocessingData.setTokensList(tokens);
        semanticPreprocessingData.setTagsList(tags);
        semanticPreprocessingData.setVerbIndex(2);
        semanticPreprocessingData.setModalVerbIndex(1);
        verbPredicateExtractor.extract(semanticExtractionData, semanticPreprocessingData);
        assertEquals("can run very quickly ", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("run", semanticExtractionData.getAtomicVerbPredicate());


    }
}
