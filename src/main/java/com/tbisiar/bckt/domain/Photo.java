package com.tbisiar.bckt.domain;

import javax.persistence.Entity;

/**
 * Created by tbis163 on 6/02/17.
 */
@Entity
public class Photo extends MongoObject {

    private String source;
    private String photoCredit;
    private int displayOrder;

    protected Photo(){}

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
