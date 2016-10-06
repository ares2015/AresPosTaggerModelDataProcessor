package semantics;

import com.trainingdataprocessor.cache.ConstantWordsCache;
import com.trainingdataprocessor.data.RegexPatternIndexData;
import com.trainingdataprocessor.data.semantics.RelationshipData;
import com.trainingdataprocessor.data.semantics.SemanticalConstantTagAnalysisData;
import com.trainingdataprocessor.semantics.RelationshipsExtractorImpl;
import com.trainingdataprocessor.semantics.SemanticConstantTagAnalyser;
import com.trainingdataprocessor.semantics.SemanticConstantTagAnalyserImpl;
import com.trainingdataprocessor.semantics.SemanticRelationConstantType;
import com.trainingdataprocessor.tags.EncodedTags;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 10/5/2016.
 */
public class SemanticConstantTagAnalyserTest {

    private ConstantWordsCache constantWordsCache = new ConstantWordsCache();

    private SemanticConstantTagAnalyser semanticConstantTagAnalyser = new SemanticConstantTagAnalyserImpl(constantWordsCache);

    @Test
    public void testIS() {

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.IS_ARE);
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);

        List<String> tokens = new ArrayList<>();
        tokens.add("American");
        tokens.add("football");
        tokens.add("is");
        tokens.add("very");
        tokens.add("popular");
        tokens.add("collective");
        tokens.add("sport");

        SemanticalConstantTagAnalysisData semanticalConstantTagAnalysisData = semanticConstantTagAnalyser.analyse(EncodedTags.IS_ARE, tokens,
                encodedTags, SemanticRelationConstantType.IS_ISNT);
        assertEquals(2, semanticalConstantTagAnalysisData.getConstantIndex());
    }

    @Test
    public void testIsNOT() {

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.IS_ARE);
        encodedTags.add(EncodedTags.NOT);
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);

        List<String> tokens = new ArrayList<>();
        tokens.add("American");
        tokens.add("football");
        tokens.add("is");
        tokens.add("not");
        tokens.add("very");
        tokens.add("popular");
        tokens.add("collective");
        tokens.add("sport");

        SemanticalConstantTagAnalysisData semanticalConstantTagAnalysisData = semanticConstantTagAnalyser.analyse(EncodedTags.IS_ARE, tokens,
                encodedTags, SemanticRelationConstantType.IS_NOT);
        assertEquals(2, semanticalConstantTagAnalysisData.getConstantIndex());
    }

    @Test
    public void testIsNOTwithAfterPreposition() {

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.IS_ARE);
        encodedTags.add(EncodedTags.NOT);
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);



        List<String> tokens = new ArrayList<>();
        tokens.add("American");
        tokens.add("football");
        tokens.add("is");
        tokens.add("not");
        tokens.add("very");
        tokens.add("popular");
        tokens.add("collective");
        tokens.add("sport");
        tokens.add("in");
        tokens.add("European");
        tokens.add("countries");

        SemanticalConstantTagAnalysisData semanticalConstantTagAnalysisData = semanticConstantTagAnalyser.analyse(EncodedTags.IS_ARE, tokens,
                encodedTags, SemanticRelationConstantType.IS_NOT);
        assertEquals(2, semanticalConstantTagAnalysisData.getConstantIndex());
        assertTrue(semanticalConstantTagAnalysisData.isPresentTense());
        assertTrue(semanticalConstantTagAnalysisData.hasExtendedSubject());
        assertTrue(semanticalConstantTagAnalysisData.isHasExtendedPredicate());
        assertTrue(semanticalConstantTagAnalysisData.containsAfterConstantTagPreposition());
    }


    @Test
    public void testModalNot() {

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.MODAL_VERB);
        encodedTags.add(EncodedTags.NOT);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);

        List<String> tokens = new ArrayList<>();
        tokens.add("Johny");
        tokens.add("can");
        tokens.add("not");
        tokens.add("speak");
        tokens.add("Spanish");
        tokens.add("language");


        SemanticalConstantTagAnalysisData semanticalConstantTagAnalysisData = semanticConstantTagAnalyser.analyse(EncodedTags.MODAL_VERB,
                tokens, encodedTags, SemanticRelationConstantType.MODAL_VERB_NOT);
        assertEquals(1, semanticalConstantTagAnalysisData.getConstantIndex());
        assertEquals(EncodedTags.MODAL_VERB, semanticalConstantTagAnalysisData.getConstantTag());
        assertTrue(semanticalConstantTagAnalysisData.isHasExtendedPredicate());

    }

    @Test
    public void testModalNotWithAfterPreposition() {

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.MODAL_VERB);
        encodedTags.add(EncodedTags.NOT);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);


        List<String> tokens = new ArrayList<>();
        tokens.add("Johny");
        tokens.add("Mnemonic");
        tokens.add("could");
        tokens.add("not");
        tokens.add("speak");
        tokens.add("Spanish");
        tokens.add("language");
        tokens.add("at");
        tokens.add("high");
        tokens.add("school");


        SemanticalConstantTagAnalysisData semanticalConstantTagAnalysisData = semanticConstantTagAnalyser.analyse(EncodedTags.MODAL_VERB,
                tokens, encodedTags, SemanticRelationConstantType.MODAL_VERB_NOT);
        assertEquals(2, semanticalConstantTagAnalysisData.getConstantIndex());
        assertEquals(EncodedTags.MODAL_VERB, semanticalConstantTagAnalysisData.getConstantTag());
        assertTrue(semanticalConstantTagAnalysisData.hasExtendedSubject());
        assertTrue(semanticalConstantTagAnalysisData.isHasExtendedPredicate());
        assertTrue(semanticalConstantTagAnalysisData.containsAfterConstantTagPreposition());
    }


}
