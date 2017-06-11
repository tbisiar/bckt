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

    public DateTime createDate;
    public DateTime updateDate;

    @PrePersist
    protected void onCreate() {
        createDate = new DateTime();
    }

    @PreUpdate
    protected void onUpdate() {
        updateDate = new DateTime();
    }

}
