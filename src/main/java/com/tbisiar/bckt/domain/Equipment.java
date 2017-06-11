package com.tbisiar.bckt.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;

@Entity
@Document(collection ="equipment")
public class Equipment extends MongoObject {

    private String title;
    private String description;

    protected Equipment(){}

    public Equipment(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
