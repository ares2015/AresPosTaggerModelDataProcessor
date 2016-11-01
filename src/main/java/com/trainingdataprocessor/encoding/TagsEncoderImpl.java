package com.trainingdataprocessor.encoding;

import com.trainingdataprocessor.cache.TagsCodingCache;

import java.util.ArrayList;
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
    public String encodeTagsListToEncodedSubPath(List<String> tags) {
        String encodedSubPath = "";
        for (String tag : tags) {
            String encodedTag = tagsCodingCache.getEncodingMap().get(tag);
            encodedSubPath += encodedTag;
        }
        return encodedSubPath;
    }

    @Override
    public List<String> encodeTagsListToEncodedTagsList(List<String> tags) {
        List<String> encodedTagsList = new ArrayList<>();
        for (String tag : tags) {
            encodedTagsList.add(tagsCodingCache.getEncodingMap().get(tag));
        }
        return encodedTagsList;
    }

    @Override
    public List<String> encodeTagMultiListToEncodedSubPathsList(List<List<String>> tags) {
        List<String> encodedTagSubPaths = new ArrayList<>();
        for (List<String> tagList : tags) {
            String encodedTagSubPath = this.encodeTagsListToEncodedSubPath(tagList);
            encodedTagSubPaths.add(encodedTagSubPath);
        }
        return encodedTagSubPaths;
    }

    @Override
    public List<List<String>> encodeTagsMultiListToEncodedTagsMultiList(List<List<String>> tags) {
        List<List<String>> listOfLists = new ArrayList<>();
        List<String> encodedTagsList = new ArrayList<>();
        for (List<String> tagList : tags) {
            for (String tag : tagList) {
                String encodedTag = tagsCodingCache.getEncodingMap().get(tag);
                encodedTagsList.add(encodedTag);
            }
            List<String> list = new ArrayList<>(encodedTagsList);
            listOfLists.add(list);
            encodedTagsList.clear();
        }
        return listOfLists;
    }
}
