package com.tbisiar.bckt.service;

import com.tbisiar.bckt.domain.Bucket;
import com.tbisiar.bckt.domain.DropType;

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

    public void reformatDB(String userId) {
        if (userId != null) {
            repo.delete(Long.valueOf(userId));
        } else {
            repo.deleteAll();
        }
    }

    boolean checkBucket(String type) {
        return DropType.RESTAURANT.equals(type);
    }

    public List<Bucket> loadBucketsForUser(String userId) {
        log.debug("Loading buckets for {}", userId);
        Query query = new Query(Criteria.where(BucketUtils.OWNER).is(userId));
        List<Bucket> buckets = mongoTemplate.find(query, Bucket.class);
        log.debug("Found {} buckets: {}", buckets.size(), buckets);
        return buckets;
    }

    public List<Bucket> loadBucketById(String userId, String bucketId) {
        log.debug("Loading bucket for id {}", bucketId);
        Query query = new Query(Criteria.where(BucketUtils.OWNER).is(userId).andOperator(Criteria.where("_id").is(bucketId)));
        List<Bucket> buckets = mongoTemplate.find(query, Bucket.class);
        log.debug("Found {} buckets: {}", buckets.size(), buckets);
        return buckets;
    }

    public void saveBucket(Bucket bucket) {
        log.debug("Saving bucket {}", bucket);
        mongoTemplate.save(bucket);
    }

    public void deleteBucket(String userId, String bucketId) {
        Query query = new Query(Criteria.where(BucketUtils.OWNER).is(userId).andOperator(Criteria.where("_id").is(bucketId)));
        Bucket bucket = mongoTemplate.findAndRemove(query, Bucket.class);
        log.debug("Deleted bucket {}", bucket);
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
