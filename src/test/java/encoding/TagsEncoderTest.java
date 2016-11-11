package encoding;

import com.trainingdataprocessor.cache.TagsCodingCache;
import com.trainingdataprocessor.encoding.TagsEncoder;
import com.trainingdataprocessor.encoding.TagsEncoderImpl;
import com.trainingdataprocessor.tags.Tags;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oliver on 8/6/2016.
 */
public class TagsEncoderTest {

    private TagsCodingCache tagsCodingCache = new TagsCodingCache();

    private TagsEncoder tagsEncoder = new TagsEncoderImpl();

    @Test
    public void testEncode(){
        List<String> tags = new ArrayList<>();
        tags.add(Tags.NOUN);
        tags.add(Tags.NOUN);
        tags.add(Tags.ADVERB);
        tags.add(Tags.VERB);
        tags.add(Tags.ADJECTIVE);
        tags.add(Tags.NOUN);
        String encodedTagPattern = tagsEncoder.encodeTagsListToEncodedSubPath(tags);
        assertEquals("NNAVJN", encodedTagPattern);
    }

    @Test
    public void testEncodeList(){
        List<String> tags1 = new ArrayList<>();
        tags1.add(Tags.NOUN);
        tags1.add(Tags.NOUN);

        List<String> tags2 = new ArrayList<>();
        tags2.add(Tags.ADVERB);
        tags2.add(Tags.VERB);

        List<String> tags3 = new ArrayList<>();

        tags3.add(Tags.ADJECTIVE);
        tags3.add(Tags.NOUN);

        List<List<String>> listOfLists = new ArrayList<List<String>>();
        listOfLists.add(tags1);
        listOfLists.add(tags2);
        listOfLists.add(tags3);

        List<String> encodedTagsList = tagsEncoder.encodeTagMultiListToEncodedSubPathsList(listOfLists);
        assertEquals("NN", encodedTagsList.get(0));
        assertEquals("AV", encodedTagsList.get(1));
        assertEquals("JN", encodedTagsList.get(2));

    }

    @Test
    public void testEncodeListOfLists(){
        List<String> tags1 = new ArrayList<>();
        tags1.add(Tags.NOUN);
        tags1.add(Tags.NOUN);

        List<String> tags2 = new ArrayList<>();
        tags2.add(Tags.ADVERB);
        tags2.add(Tags.VERB);

        List<String> tags3 = new ArrayList<>();

        tags3.add(Tags.ADJECTIVE);
        tags3.add(Tags.NOUN);

        List<List<String>> listOfLists = new ArrayList<List<String>>();
        listOfLists.add(tags1);
        listOfLists.add(tags2);
        listOfLists.add(tags3);

        List<List<String>> encodedTagsList = tagsEncoder.encodeTagsMultiListToEncodedTagsMultiList(listOfLists);
        assertEquals("N", encodedTagsList.get(0).get(0));
        assertEquals("N", encodedTagsList.get(0).get(1));
        assertEquals("A", encodedTagsList.get(1).get(0));
        assertEquals("V", encodedTagsList.get(1).get(1));
        assertEquals("J", encodedTagsList.get(2).get(0));
        assertEquals("N", encodedTagsList.get(2).get(1));


//        assertEquals("JN", encodedTagsList.get(0));

    }


}
