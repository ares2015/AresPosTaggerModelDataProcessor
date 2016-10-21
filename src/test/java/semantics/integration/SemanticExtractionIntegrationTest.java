package semantics.integration;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oliver on 10/16/2016.
 */
public class SemanticExtractionIntegrationTest {

//    private RegexPatternSearcher regexPatternSearcher = new RegexPatternSearcherImpl();
//
//    private ConstantWordsCache constantWordsCache = new ConstantWordsCache();
//
//    private SemanticConstantTagAnalyser semanticConstantTagAnalyser = new SemanticConstantTagAnalyserImpl();
//
//    private SubjectExtractor subjectExtractor = new SubjectExtractorImpl();
//
//    private PredicateExtractor predicateExtractor = new PredicateExtractorImpl();
//
//    private VerbExtractor verbExtractor = new VerbExtractorImpl();
//
//    SemanticRelationsExtractor relationsExtractor = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser, subjectExtractor, predicateExtractor,
//            verbExtractor);
//
//    @Test
//    public void testNounVerbNoun() {
//        String sentence = "brave firemen fight furiously forest fire in California mountains";
//        List<String> tokens = Arrays.asList(sentence.split("\\ "));
//        String pattern = "JNVANNPNN";
//
//        List<String> encodedTags = new ArrayList<>();
//        encodedTags.add(EncodedTags.ADJECTIVE);
//        encodedTags.add(EncodedTags.NOUN);
//        encodedTags.add(EncodedTags.VERB);
//        encodedTags.add(EncodedTags.ADVERB);
//        encodedTags.add(EncodedTags.NOUN);
//        encodedTags.add(EncodedTags.NOUN);
//        encodedTags.add(EncodedTags.PREPOSITION);
//        encodedTags.add(EncodedTags.NOUN);
//        encodedTags.add(EncodedTags.NOUN);
//
//        List<RegexPatternData> regexPatternDataList = null;
//        for (String regexPattern : RegexExpressions.regexPatterns) {
//            regexPatternDataList = regexPatternSearcher.search(pattern, regexPattern);
//            if (regexPatternDataList.size() > 0) {
//                RegexPatternData regexPatternData = regexPatternDataList.get(0);
//
//                String constantTag = RegexExpressions.patternsConstantTagsMap.get(regexPattern);
//                SemanticRelationConstantType semanticRelationConstantType = RegexExpressions.patternsSemanticRelationConstantTypeMap.get(regexPattern);
//                SemanticRelationData semanticRelationData = relationsExtractor.extract(constantTag, regexPatternData, tokens, encodedTags, semanticRelationConstantType);
//                if (regexPatternData.getPattern() != null) {
//                    assertEquals("JNVANNPNN", regexPatternDataList.get(0).getPattern());
//                    assertEquals("firemen", semanticRelationData.getAtomicSubject());
//                    assertEquals("brave firemen", semanticRelationData.getExtendedSubject());
//                    assertEquals("fight", semanticRelationData.getAtomicVerb());
//                    assertEquals("furiously forest fire ", semanticRelationData.getExtendedPredicate());
//                    assertEquals("fire", semanticRelationData.getAtomicPredicate());
//                    break;
//                }
//            }
//        }
//    }
}
