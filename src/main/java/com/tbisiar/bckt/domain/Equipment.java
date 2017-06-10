package com.tbisiar.bckt.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by tbis163 on 6/02/17.
 */
@Entity
public class Equipment extends MongoObject {

    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String description;

    protected Equipment(){}

    public Equipment(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
