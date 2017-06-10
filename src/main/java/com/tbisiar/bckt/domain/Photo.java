package com.tbisiar.bckt.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by tbis163 on 6/02/17.
 */
@Entity
public class Photo extends MongoObject {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String source;
    private String photoCredit;
    private int displayOrder;

    protected Photo(){}

    public Photo(String source, String photoCredit, int displayOrder) {
        this.source = source;
        this.photoCredit = photoCredit;
        this.displayOrder = displayOrder;
    }

    public long getId() { return id; }

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
