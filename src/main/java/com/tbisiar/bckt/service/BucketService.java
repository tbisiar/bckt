package com.tbisiar.bckt.service;

import com.tbisiar.bckt.domain.Bucket;
import com.tbisiar.bckt.domain.DropType;
import com.tbisiar.bckt.domain.MongoObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BucketService {

    private GenericDao repo;
    private MongoTemplate mongoTemplate;

    private static final Logger log = LoggerFactory.getLogger(BucketService.class);

    public void createDemoBucket() {
        log.debug("Creating demo buckets");
        Bucket demoBucket = DemoUtils.getDemoBucket();
        repo.save(demoBucket);
    }

    public void reformatDB(Long userId) {
        if (userId != null) {
            repo.delete(userId);
        } else {
            repo.deleteAll();
        }
    }

    boolean checkBucket(String type) {
        return DropType.RESTAURANT.equals(type);
    }

    public List<MongoObject> loadBucketsForUser(String userId) {
        log.debug("Loading buckets for {}", userId);
        Query query = new Query(Criteria.where("owner").is(userId).andOperator(Criteria.where("_class").is("com.tbisiar.bckt.domain.Bucket")));
        return mongoTemplate.find(query, MongoObject.class);
    }

    public void saveBucket(String userId, Bucket bucket) {
        log.debug("Saving bucket {} for {}", bucket, userId);
        mongoTemplate.save(bucket);
    }

    @Autowired
    public void setRepo(GenericDao genericDao) {
        this.repo = genericDao;
    }

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

}
