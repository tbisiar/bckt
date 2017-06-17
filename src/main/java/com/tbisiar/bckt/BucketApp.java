package com.tbisiar.bckt;

import com.tbisiar.bckt.service.BucketService;
import com.tbisiar.bckt.service.GenericDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BucketApp implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(BucketApp.class);

    private GenericDao repo;
    private BucketService bucketService;

    public static void main(String[] args) {
        SpringApplication.run(BucketApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.debug("Running BucketApp with repo {}", repo);
        bucketService.createDemoBucket();
    }

    @Autowired
    public void setRepo(GenericDao genericDao) {
        this.repo = genericDao;
    }

    @Autowired
    public void setBucketService(BucketService bucketService) {
        this.bucketService = bucketService;
    }
}
