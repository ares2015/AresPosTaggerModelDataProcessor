package com.trainingdataprocessor.data.syntax;

/**
 * Created by Oliver on 7/25/2016.
 */
public final class SubPathData {

    private String startTag;

    private String endTag;

    private String subPath;

    private int length;

    private int startIndex;

    private int endIndex;

    private boolean isConstantSubPath = false;

    public SubPathData(String startTag, String endTag, String subPath, int length, int startIndex, int endIndex, boolean isConstantSubPath) {
        this.startTag = startTag;
        this.endTag = endTag;
        this.subPath = subPath;
        this.length = length;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.isConstantSubPath = isConstantSubPath;
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

    public boolean isConstantSubPath() {
        return isConstantSubPath;
    }

}
