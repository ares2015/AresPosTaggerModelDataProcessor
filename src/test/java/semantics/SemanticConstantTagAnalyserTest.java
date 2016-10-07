package semantics;

import com.trainingdataprocessor.cache.ConstantWordsCache;
import com.trainingdataprocessor.data.semantics.SemanticalConstantTagAnalysisData;
import com.trainingdataprocessor.semantics.SemanticConstantTagAnalyser;
import com.trainingdataprocessor.semantics.SemanticConstantTagAnalyserImpl;
import com.trainingdataprocessor.semantics.SemanticRelationConstantType;
import com.trainingdataprocessor.tags.EncodedTags;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
        assertFalse(semanticalConstantTagAnalysisData.hasVerbAuxiliaryVerbRelation());
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
        assertTrue(semanticalConstantTagAnalysisData.hasVerbAuxiliaryVerbRelation());
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
        assertTrue(semanticalConstantTagAnalysisData.hasExtendedPredicate());
        assertTrue(semanticalConstantTagAnalysisData.containsAfterConstantTagPreposition());
        assertTrue(semanticalConstantTagAnalysisData.hasVerbAuxiliaryVerbRelation());
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
        assertTrue(semanticalConstantTagAnalysisData.hasExtendedPredicate());
        assertTrue(semanticalConstantTagAnalysisData.hasVerbAuxiliaryVerbRelation());

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
        assertTrue(semanticalConstantTagAnalysisData.hasExtendedPredicate());
        assertTrue(semanticalConstantTagAnalysisData.containsAfterConstantTagPreposition());
        assertTrue(semanticalConstantTagAnalysisData.hasVerbAuxiliaryVerbRelation());
    }

    @Test
    public void testVerb() {

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.NOUN);

        List<String> tokens = new ArrayList<>();
        tokens.add("Johny");
        tokens.add("speaks");
        tokens.add("Spanish");


        SemanticalConstantTagAnalysisData semanticalConstantTagAnalysisData = semanticConstantTagAnalyser.analyse(EncodedTags.VERB,
                tokens, encodedTags, SemanticRelationConstantType.VERB);
        assertEquals(1, semanticalConstantTagAnalysisData.getConstantIndex());
        assertEquals(EncodedTags.VERB, semanticalConstantTagAnalysisData.getConstantTag());
        assertFalse(semanticalConstantTagAnalysisData.hasExtendedPredicate());
        assertFalse(semanticalConstantTagAnalysisData.hasVerbAuxiliaryVerbRelation());
    }

    @Test
    public void testVerbDont() {

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.DO);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);

        List<String> tokens = new ArrayList<>();
        tokens.add("Johny");
        tokens.add("didn't");
        tokens.add("speak");
        tokens.add("Spanish");
        tokens.add("language");


        SemanticalConstantTagAnalysisData semanticalConstantTagAnalysisData = semanticConstantTagAnalyser.analyse(EncodedTags.VERB,
                tokens, encodedTags, SemanticRelationConstantType.VERB_DONT);
        assertEquals(2, semanticalConstantTagAnalysisData.getConstantIndex());
        assertEquals(EncodedTags.VERB, semanticalConstantTagAnalysisData.getConstantTag());
        assertTrue(semanticalConstantTagAnalysisData.hasExtendedPredicate());
        assertTrue(semanticalConstantTagAnalysisData.hasVerbAuxiliaryVerbRelation());

    }

    @Test
    public void testVerbDoNot() {

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.DO);
        encodedTags.add(EncodedTags.NOT);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);

        List<String> tokens = new ArrayList<>();
        tokens.add("Johny");
        tokens.add("did");
        tokens.add("not");
        tokens.add("speak");
        tokens.add("Spanish");
        tokens.add("language");


        SemanticalConstantTagAnalysisData semanticalConstantTagAnalysisData = semanticConstantTagAnalyser.analyse(EncodedTags.VERB,
                tokens, encodedTags, SemanticRelationConstantType.VERB_DO_NOT);
        assertEquals(3, semanticalConstantTagAnalysisData.getConstantIndex());
        assertEquals(EncodedTags.VERB, semanticalConstantTagAnalysisData.getConstantTag());
        assertTrue(semanticalConstantTagAnalysisData.hasExtendedPredicate());
        assertTrue(semanticalConstantTagAnalysisData.hasVerbAuxiliaryVerbRelation());

    }


}