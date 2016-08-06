package com.trainingdataprocessor.encoding;

import com.trainingdataprocessor.cache.TagsCodingCache;

import java.util.List;

/**
 * Created by Oliver on 8/5/2016.
 */
public class TagsEncoderImpl implements TagsEncoder {

    private TagsCodingCache tagsCodingCache;

    public TagsEncoderImpl(TagsCodingCache tagsCodingCache) {
        this.tagsCodingCache = tagsCodingCache;
    }

    @Override
    public String encode(List<String> tags) {
        String encodedTagPattern = "";
        for(String tag : tags){
            String encodedTag = tagsCodingCache.getEncodingMap().get(tag);
            encodedTagPattern += encodedTag;
        }
        return encodedTagPattern;
    }
}
