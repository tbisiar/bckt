package com.tbisiar.bckt.service;

import com.tbisiar.bckt.domain.DropType;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

// TODO: implement actual unit tests
public class BucketServiceTest {

    @Autowired
    private BucketService bucketService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testCreateDemoBucket() throws Exception {
        assert(bucketService.checkBucket(DropType.RESTAURANT));
    }

}