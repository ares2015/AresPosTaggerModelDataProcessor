package com.trainingdataprocessor.encoding;

import java.util.List;

/**
 * Created by Oliver on 8/5/2016.
 */
public interface TagsEncoder {

    String encodeTagSubPath(List<String> tags);

    List<String> encodeTagSubPathList(List<List<String>> tags);

    List<List<String>> encodeTagsAsListOfLists(List<List<String>> tags);

}
