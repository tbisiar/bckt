package com.tbisiar.bckt.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tbisiar.bckt.domain.Bucket;
import com.tbisiar.bckt.service.BucketService;
import com.tbisiar.bckt.service.DemoUtils;
import com.tbisiar.bckt.service.BucketUtils;

@RestController
public class BucketController {

    private static final Logger log = LoggerFactory.getLogger(BucketController.class);

    private BucketService bucketService;

    @CrossOrigin(origins = BucketUtils.CROSS_ORIGIN_URI)
    @RequestMapping(value = "/buckets", method = RequestMethod.GET)
    public List<Bucket> loadBuckets(@RequestParam(value = "userId") String userId,
                                    @RequestParam(value = "bucketId", required = false) String bucketId) {
        List<Bucket> buckets;
        if (bucketId != null) {
            buckets = bucketService.loadBucketById(userId, bucketId);
        } else {
            buckets = bucketService.loadBucketsForUser(userId);
        }
        log.info("{} buckets loaded for {}", buckets.size(), userId);
        return buckets;
    }

    @CrossOrigin(origins = BucketUtils.CROSS_ORIGIN_URI)
    @RequestMapping(value = "/buckets/createDemoBucket", method = RequestMethod.GET)
    public List<Bucket> createDemoBucket(@RequestParam(value = "userId", defaultValue = "NoneProvided") String userId) {
        bucketService.createDemoBucket();
        List<Bucket> buckets = bucketService.loadBucketsForUser(userId);
        log.info("{} buckets loaded for {}", buckets.size(), userId);
        return buckets;
    }

    @CrossOrigin(origins = BucketUtils.CROSS_ORIGIN_URI)
    @RequestMapping(value = "/buckets/format", method = RequestMethod.GET)
    public void reformatDB(@RequestParam(value = "userId") String userId) {
        bucketService.reformatDB(userId);
    }

    @ResponseBody
    @CrossOrigin(origins = BucketUtils.CROSS_ORIGIN_URI)
    @RequestMapping(value = "/buckets/save", method = RequestMethod.POST)
    public List<Bucket> saveBucket(@RequestBody Bucket bucket) { //@RequestParam(value = "userId") String userId,
        String userId = DemoUtils.DEMO_USER_ID;
        bucket.setOwner(userId);
        bucketService.saveBucket(bucket);
        return bucketService.loadBucketsForUser(userId);
    }

    @CrossOrigin(origins = BucketUtils.CROSS_ORIGIN_URI)
    @RequestMapping(value = "/buckets/format/{bucketId}", method = RequestMethod.DELETE)
    public List<Bucket> deleteBucket(@PathVariable String bucketId, @RequestParam(value = "userId") String userId) {
        String demoUserId = DemoUtils.DEMO_USER_ID;
        bucketService.deleteBucket(demoUserId, bucketId);
        return bucketService.loadBucketsForUser(demoUserId);
    }

    @Autowired
    void setBucketService(BucketService bucketService) {
        this.bucketService = bucketService;
    }
}