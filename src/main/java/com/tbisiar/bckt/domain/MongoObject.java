package com.tbisiar.bckt.domain;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class MongoObject {

    @Id
    public String id;
//    (.... created, last updated, general stuff here....)

}
