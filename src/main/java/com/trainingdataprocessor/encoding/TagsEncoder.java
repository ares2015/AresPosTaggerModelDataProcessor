package com.trainingdataprocessor.encoding;

import java.util.List;

/**
 * Created by Oliver on 8/5/2016.
 */
public interface TagsEncoder {

    String encodeTagsListToEncodedSubPath(List<String> tags);

    List<String> encodeTagsListToEncodedTagsList(List<String> tags);

    List<String> encodeTagMultiListToEncodedSubPathsList(List<List<String>> tags);

    List<List<String>> encodeTagsMultiListToEncodedTagsMultiList(List<List<String>> tags);

}
