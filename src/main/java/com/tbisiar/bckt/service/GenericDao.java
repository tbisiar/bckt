package com.tbisiar.bckt.service;

import com.tbisiar.bckt.domain.MongoObject;

import org.springframework.stereotype.Repository;

/*
Based on solution from stackoverflow: https://stackoverflow.com/questions/42831907/spring-boot-how-to-avoid-too-many-jpa-repositories-for-each-domain-class#
 */
@Repository
public interface GenericDao extends Dao<MongoObject> {
    // This class should remain empty, put special queries in Dao
}