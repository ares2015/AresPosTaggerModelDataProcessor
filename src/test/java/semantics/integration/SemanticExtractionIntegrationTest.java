package semantics.integration;

import com.trainingdataprocessor.cache.ConstantWordsCache;
import com.trainingdataprocessor.data.RegexPatternIndexData;
import com.trainingdataprocessor.data.semantics.SemanticRelationData;
import com.trainingdataprocessor.regex.RegexExpressions;
import com.trainingdataprocessor.regex.RegexPatternIndexFinder;
import com.trainingdataprocessor.regex.RegexPatternIndexFinderImpl;
import com.trainingdataprocessor.semantics.*;
import com.trainingdataprocessor.tags.EncodedTags;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oliver on 10/16/2016.
 */
public class SemanticExtractionIntegrationTest {

    private RegexPatternIndexFinder regexPatternIndexFinder = new RegexPatternIndexFinderImpl();

    private ConstantWordsCache constantWordsCache = new ConstantWordsCache();

    private SemanticConstantTagAnalyser semanticConstantTagAnalyser = new SemanticConstantTagAnalyserImpl(constantWordsCache);

    private SubjectExtractor subjectExtractor = new SubjectExtractorImpl();

    private PredicateExtractor predicateExtractor = new PredicateExtractorImpl();

    private VerbExtractor verbExtractor = new VerbExtractorImpl();

    SemanticRelationsExtractor relationsExtractor = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser, subjectExtractor, predicateExtractor,
            verbExtractor);

    @Test
    public void testNounVerbNoun() {
        String sentence = "brave firemen fight furiously forest fire in California mountains";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));
        String pattern = "JNVANNPNN";

        List<String> encodedTags = new ArrayList<>();
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.ADVERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);

        List<RegexPatternIndexData> regexPatternIndexDataList = null;
        for (String regexPattern : RegexExpressions.regexPatterns) {
            regexPatternIndexDataList = regexPatternIndexFinder.find(pattern, regexPattern);
            if (regexPatternIndexDataList.size() > 0) {
                RegexPatternIndexData regexPatternIndexData = regexPatternIndexDataList.get(0);

                String constantTag = RegexExpressions.patternsConstantTagsMap.get(regexPattern);
                SemanticRelationConstantType semanticRelationConstantType = RegexExpressions.patternsSemanticRelationConstantTypeMap.get(regexPattern);
                SemanticRelationData semanticRelationData = relationsExtractor.extract(constantTag, regexPatternIndexData, tokens, encodedTags, semanticRelationConstantType);
                if (regexPatternIndexData.getPattern() != null) {
                    assertEquals("JNVANNPNN", regexPatternIndexDataList.get(0).getPattern());
                    assertEquals("firemen", semanticRelationData.getAtomicSubject());
                    assertEquals("brave firemen", semanticRelationData.getExtendedSubject());
                    assertEquals("fight", semanticRelationData.getAtomicVerb());
                    assertEquals("furiously forest fire ", semanticRelationData.getExtendedPredicate());
                    assertEquals("fire", semanticRelationData.getAtomicPredicate());
                    break;
                }
            }
        }
    }
}
