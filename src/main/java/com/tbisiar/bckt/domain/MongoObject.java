package com.tbisiar.bckt.domain;

import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class MongoObject {
    @Id
    @GeneratedValue
    private Long id;
//    (.... created, last updated, general stuff here....)

    public long getId() {
        return id;
    }

}
