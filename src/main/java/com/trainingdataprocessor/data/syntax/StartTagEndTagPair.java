package com.trainingdataprocessor.data.syntax;

/**
 * Created by Oliver on 7/25/2016.
 */
public final class StartTagEndTagPair {

    private String startTag;

    private String endTag;

    private String subPath;

    private int length;

    private int startIndex;

    private int endIndex;

    private boolean containsConstant = false;

    public StartTagEndTagPair(String startTag, String endTag, String subPath, int length, int startIndex, int endIndex, boolean containsConstant) {
        this.startTag = startTag;
        this.endTag = endTag;
        this.subPath = subPath;
        this.length = length;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.containsConstant = containsConstant;
    }

    public String getStartTag() {
        return startTag;
    }

    public String getEndTag() {
        return endTag;
    }

    public String getSubPath() {
        return subPath;
    }

    public int getLength() {
        return length;
    }

    public boolean containsConstant() {
        return containsConstant;
    }

    public void setContainsConstant(boolean containsConstant) {
        this.containsConstant = containsConstant;
    }
}
