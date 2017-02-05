package com.tbisiar.bckt.domain;

/**
 * Created by tbis163 on 6/02/17.
 */
public class Photo {
    private final String source;
    private final String photoCredit;
    private final int displayOrder;

    public Photo(String source, String photoCredit, int displayOrder) {
        this.source = source;
        this.photoCredit = photoCredit;
        this.displayOrder = displayOrder;
    }

    public String getSource() {
        return source;
    }

    public String getPhotoCredit() {
        return photoCredit;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }
}
