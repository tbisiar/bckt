package com.tbisiar.bckt.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.tbisiar.bckt.domain.MongoObject;
import com.tbisiar.bckt.service.BucketService;

@RestController
public class BucketController {

    private BucketService bucketService;

    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(value="/bucket", method = RequestMethod.GET)
    public List<MongoObject> greeting(@RequestParam(value="name", defaultValue="World") String name) {
        bucketService.createDemoBucket();
        return bucketService.loadBucketsForUser(1);
    }

    @Autowired
    void setBucketService(BucketService bucketService) {
        this.bucketService = bucketService;
    }
}