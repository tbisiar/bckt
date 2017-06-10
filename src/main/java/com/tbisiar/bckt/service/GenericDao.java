package com.tbisiar.bckt.service;

import com.tbisiar.bckt.domain.MongoObject;

import org.springframework.stereotype.Repository;

@Repository
public interface GenericDao extends Dao<MongoObject> {
}