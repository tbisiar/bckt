package com.tbisiar.bckt.service;

import com.tbisiar.bckt.domain.Drop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DropService {

    private MongoTemplate mongoTemplate;

    private static final Logger log = LoggerFactory.getLogger(DropService.class);

    public List<Drop> loadDropsForUser(String userId) {
        log.debug("Loading drops for {}", userId);
        Query query = new Query(Criteria.where(BucketUtils.OWNER).is(userId));
        List<Drop> drops = mongoTemplate.find(query, Drop.class);
        log.debug("Found {} drops: {}", drops.size(), drops);
        return drops;
    }

    public List<Drop> loadDropById(String userId, String dropId) {
        log.debug("Loading drop for id {}", dropId);
        Query query = new Query(Criteria.where(BucketUtils.OWNER).is(userId).andOperator(Criteria.where("_id").is(dropId)));
        List<Drop> drops = mongoTemplate.find(query, Drop.class);
        log.debug("Found {} drops: {}", drops.size(), drops);
        return drops;
    }

    public void saveDrop(Drop drop) {
        log.debug("Saving drop {}", drop);
        mongoTemplate.save(drop);
    }

    public void deleteDrop(String userId, String dropId) {
        Query query = new Query(Criteria.where(BucketUtils.OWNER).is(userId).andOperator(Criteria.where("_id").is(dropId)));
        Drop drop = mongoTemplate.findAndRemove(query, Drop.class);
        log.debug("Deleted drop {}", drop);
    }

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

}
