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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tbisiar.bckt.domain.Bucket;
import com.tbisiar.bckt.service.BucketService;
import com.tbisiar.bckt.service.DemoUtils;

@RestController
public class BucketController {

    private static final Logger log = LoggerFactory.getLogger(BucketController.class);

    private static final String CROSS_ORIGIN_URI = "http://localhost:63342";

    private BucketService bucketService;

    @CrossOrigin(origins = CROSS_ORIGIN_URI)
    @RequestMapping(value = "/buckets", method = RequestMethod.GET)
    public List<Bucket> loadBuckets(@RequestParam(value = "userId") String userId,
                                    @RequestParam(value = "bucketId", required = false) String bucketId) {
        List<Bucket> buckets;
        if(bucketId != null) {
            buckets = bucketService.loadBucketById(userId, bucketId);
        } else {
            buckets = bucketService.loadBucketsForUser(userId);
        }
        log.info("{} buckets loaded for {}", buckets.size(), userId);
        return buckets;
    }

    @CrossOrigin(origins = CROSS_ORIGIN_URI)
    @RequestMapping(value = "/buckets/createDemoBucket", method = RequestMethod.GET)
    public List<Bucket> createDemoBucket(@RequestParam(value = "userId", defaultValue = "NoneProvided") String userId) {
        bucketService.createDemoBucket();
        List<Bucket> buckets = bucketService.loadBucketsForUser(userId);
        log.info("{} buckets loaded for {}", buckets.size(), userId);
        return buckets;
    }

    @CrossOrigin(origins = CROSS_ORIGIN_URI)
    @RequestMapping(value = "/buckets/format", method = RequestMethod.GET)
    public void reformatDB(@RequestParam(value = "userId") String userId) {
        bucketService.reformatDB(userId);
    }

    @ResponseBody
    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(value = "/buckets/saveBucket", method = RequestMethod.POST)
    public List<Bucket> saveBucket(@RequestBody Bucket bucket) { //@RequestParam(value = "userId") String userId,
        String userId = DemoUtils.DEMO_USER_ID;
        bucket.setOwner(userId);
        bucketService.saveBucket(bucket);
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