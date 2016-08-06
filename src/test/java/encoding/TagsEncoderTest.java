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

    private TagsEncoder tagsEncoder = new TagsEncoderImpl(tagsCodingCache);

    @Test
    public void testEncode(){
        List<String> tags = new ArrayList<>();
        tags.add(Tags.NOUN);
        tags.add(Tags.NOUN);
        tags.add(Tags.ADVERB);
        tags.add(Tags.VERB);
        tags.add(Tags.ADJECTIVE);
        tags.add(Tags.NOUN);
        String encodedTagPattern = tagsEncoder.encode(tags);
        assertEquals("NNAVJN", encodedTagPattern);
    }
}
