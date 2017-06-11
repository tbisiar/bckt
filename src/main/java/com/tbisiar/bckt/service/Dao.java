package com.tbisiar.bckt.service;

import com.tbisiar.bckt.domain.MongoObject;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

/*
Based on solution here: https://stackoverflow.com/questions/42831907/spring-boot-how-to-avoid-too-many-jpa-repositories-for-each-domain-class#
 */
@NoRepositoryBean
interface Dao<T extends MongoObject> extends MongoRepository<T, Long> {
    // Put special queries in here, not GenericDao
}