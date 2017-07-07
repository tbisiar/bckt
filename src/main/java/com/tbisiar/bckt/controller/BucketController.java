package com.tbisiar.bckt.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tbisiar.bckt.domain.Bucket;
import com.tbisiar.bckt.domain.MongoObject;
import com.tbisiar.bckt.service.BucketService;

@RestController
public class BucketController {

    private static final Logger log = LoggerFactory.getLogger(BucketController.class);

    private static final String CROSS_ORIGIN_URI = "http://localhost:63342";

    private BucketService bucketService;

    @CrossOrigin(origins = CROSS_ORIGIN_URI)
    @RequestMapping(value = "/buckets", method = RequestMethod.GET)
    public List<MongoObject> loadBuckets(@RequestParam(value = "userId", defaultValue = "NoneProvided") String userId) {
        List<MongoObject> buckets = bucketService.loadBucketsForUser(userId);
        log.info("{} buckets loaded for {}", buckets.size(), userId);
        return buckets;
    }

    @CrossOrigin(origins = CROSS_ORIGIN_URI)
    @RequestMapping(value = "/buckets/createDemoBucket", method = RequestMethod.GET)
    public List<MongoObject> createDemoBucket(@RequestParam(value = "userId", defaultValue = "NoneProvided") String userId) {
        bucketService.createDemoBucket();
        List<MongoObject> buckets = bucketService.loadBucketsForUser(userId);
        log.info("{} buckets loaded for {}", buckets.size(), userId);
        return buckets;
    }

    @CrossOrigin(origins = CROSS_ORIGIN_URI)
    @RequestMapping(value = "/buckets/format", method = RequestMethod.GET)
    public void reformatDB(@RequestParam(value = "userId") Long userId) {
        bucketService.reformatDB(userId);
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(value = "/buckets/saveBucket", method = RequestMethod.POST)
    public List<MongoObject> saveBucket(@RequestParam(value = "userId") String userId, @RequestBody Bucket bucket) {
        bucketService.saveBucket(userId, bucket);
        return bucketService.loadBucketsForUser(userId);
    }

    // TODO: implement account validation per: https://spring.io/guides/tutorials/bookmarks/
    private void validateUser(String userId) {
//        this.accountRepository.findByUsername(userId).orElseThrow(
//                () -> new UserNotFoundException(userId));
    }

    @Autowired
    void setBucketService(BucketService bucketService) {
        this.bucketService = bucketService;
    }
}