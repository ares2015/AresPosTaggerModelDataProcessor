package relationships;

import com.trainingdataprocessor.cache.ConstantWordsCache;
import com.trainingdataprocessor.data.ISRelationshipData;
import com.trainingdataprocessor.data.RegexPatternIndexData;
import com.trainingdataprocessor.data.RelationshipData;
import com.trainingdataprocessor.relationships.ISRelationshipsExtractor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 8/31/2016.
 */
public class ISRelationshipsExtractorTest {

    private ConstantWordsCache constantWordsCache = new ConstantWordsCache();

    private ISRelationshipsExtractor isRelationshipsExtractor = new ISRelationshipsExtractor(constantWordsCache);

    @Test
    public void testExtractNoPrepositions(){
        List<String> tokens = new ArrayList<>();
        tokens.add("Wikipedia");
        tokens.add("says");
        tokens.add("that");
        tokens.add("American");
        tokens.add("football");
        tokens.add("is");
        tokens.add("very");
        tokens.add("popular");
        tokens.add("collective");
        tokens.add("sport");

        List<RegexPatternIndexData> isPatternIndexDataList = new ArrayList<>();
        RegexPatternIndexData regexPatternIndexData = new RegexPatternIndexData("NNIJJJN", 3, 9);
        isPatternIndexDataList.add(regexPatternIndexData);

        List<ISRelationshipData> relationshipDataList = isRelationshipsExtractor.extract(isPatternIndexDataList, tokens);

        assertEquals(1, relationshipDataList.size());
        assertTrue(relationshipDataList.get(0).isPresentTense());
        assertEquals("football", relationshipDataList.get(0).getLevel1object1());
        assertEquals("sport", relationshipDataList.get(0).getLevel1object2());

        assertEquals("American football", relationshipDataList.get(0).getLevel2object1());
        assertEquals("sport", relationshipDataList.get(0).getLevel2object2());

        assertEquals("American football", relationshipDataList.get(0).getLevel3object1());
        assertEquals("very popular collective sport", relationshipDataList.get(0).getLevel3object2());

    }

    @Test
    public void testExtractOnePreposition(){
        List<String> tokens = new ArrayList<>();
        tokens.add("Wikipedia");
        tokens.add("says");
        tokens.add("that");
        tokens.add("American");
        tokens.add("football");
        tokens.add("is");
        tokens.add("very");
        tokens.add("popular");
        tokens.add("collective");
        tokens.add("sport");
        tokens.add("in");
        tokens.add("Stanford");
        tokens.add("University");

        List<RegexPatternIndexData> isPatternIndexDataList = new ArrayList<>();
        RegexPatternIndexData regexPatternIndexData = new RegexPatternIndexData("NNIJJJNPNN", 3, 12);
        isPatternIndexDataList.add(regexPatternIndexData);

        List<ISRelationshipData> relationshipDataList = isRelationshipsExtractor.extract(isPatternIndexDataList, tokens);

        assertEquals(1, relationshipDataList.size());
        assertTrue(relationshipDataList.get(0).isPresentTense());
        assertEquals("football", relationshipDataList.get(0).getLevel1object1());
        assertEquals("sport", relationshipDataList.get(0).getLevel1object2());

        assertEquals("American football", relationshipDataList.get(0).getLevel2object1());
        assertEquals("sport", relationshipDataList.get(0).getLevel2object2());

        assertEquals("American football", relationshipDataList.get(0).getLevel3object1());
        assertEquals("very popular collective sport ", relationshipDataList.get(0).getLevel3object2());

        assertEquals("American football", relationshipDataList.get(0).getLevel4object1());
        assertEquals("very popular collective sport in Stanford University", relationshipDataList.get(0).getLevel4object2());

    }

    @Test
    public void testExtractTwoPrepositions(){
        List<String> tokens = new ArrayList<>();
        tokens.add("Wikipedia");
        tokens.add("says");
        tokens.add("that");
        tokens.add("American");
        tokens.add("football");
        tokens.add("is");
        tokens.add("very");
        tokens.add("popular");
        tokens.add("collective");
        tokens.add("sport");
        tokens.add("in");
        tokens.add("Stanford");
        tokens.add("University");
        tokens.add("in");
        tokens.add("California");

        List<RegexPatternIndexData> isPatternIndexDataList = new ArrayList<>();
        RegexPatternIndexData regexPatternIndexData = new RegexPatternIndexData("NNIJJJNPNN", 3, 14);
        isPatternIndexDataList.add(regexPatternIndexData);

        List<ISRelationshipData> relationshipDataList = isRelationshipsExtractor.extract(isPatternIndexDataList, tokens);

        assertEquals(1, relationshipDataList.size());
        assertTrue(relationshipDataList.get(0).isPresentTense());
        assertEquals("football", relationshipDataList.get(0).getLevel1object1());
        assertEquals("sport", relationshipDataList.get(0).getLevel1object2());

        assertEquals("American football", relationshipDataList.get(0).getLevel2object1());
        assertEquals("sport", relationshipDataList.get(0).getLevel2object2());

        assertEquals("American football", relationshipDataList.get(0).getLevel3object1());
        assertEquals("very popular collective sport ", relationshipDataList.get(0).getLevel3object2());

        assertEquals("American football", relationshipDataList.get(0).getLevel4object1());
        assertEquals("very popular collective sport in Stanford University ", relationshipDataList.get(0).getLevel4object2());

        assertEquals("American football", relationshipDataList.get(0).getLevel5object1());
        assertEquals("very popular collective sport in Stanford University in California", relationshipDataList.get(0).getLevel5object2());

    }
}
