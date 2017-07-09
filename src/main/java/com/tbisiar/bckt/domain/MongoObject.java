package com.tbisiar.bckt.domain;

import org.joda.time.DateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class MongoObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String id;

    private DateTime createDate;
    private DateTime updateDate;

    String title;
    String description;
    String owner;

    MongoObject() {
    }

    MongoObject(String title, String description, String owner) {
        this.title = title;
        this.description = description;
        this.owner = owner;
    }

    @PrePersist
    protected void onCreate() {
        createDate = new DateTime();
    }

    @PreUpdate
    protected void onUpdate() {
        updateDate = new DateTime();
    }

    public DateTime getCreateDate() {
        return createDate;
    }

    public DateTime getUpdateDate() {
        return updateDate;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }
}
