package com.tbisiar.bckt;

import com.tbisiar.bckt.domain.Bucket;
import com.tbisiar.bckt.domain.Drop;
import com.tbisiar.bckt.domain.DropType;
import com.tbisiar.bckt.domain.Location;
import com.tbisiar.bckt.domain.Photo;
import com.tbisiar.bckt.service.BucketUtils;
import com.tbisiar.bckt.service.GenericDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BucketApp implements CommandLineRunner {

    private GenericDao repo;

    public static void main(String[] args) {
        SpringApplication.run(BucketApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        repo.deleteAll();

        // save a photo
        Photo demoPhoto1 = new Photo("../image/waipuCaves.jpg", "Terry Bisiar", 0);
        repo.save(demoPhoto1);

        // save a location
        Location demoLocation1 = new Location(
                "Bay of Islands",
                -35.2144466,
                174.0873659,
                "N/A",
                null,
                false);
        repo.save(demoLocation1);

        // save a dropType
        DropType demoDropType1 = new DropType(
                DropType.ACTIVITY,
                null,
                8,
                6);
        repo.save(demoDropType1);

        // save a drop
        Drop demoDrop1 = new Drop(
                "Cape Brett Track",
                "Amazing tramping with intense hills and views to match",
                BucketUtils.newHashSet(demoPhoto1),
                demoLocation1,
                demoDropType1,
                null);
        repo.save(demoDrop1);

        // save a bucket
        Bucket demoBucket1 = new Bucket(
                "demoBucket1",
                BucketUtils.newHashSet(demoDrop1));
        repo.save(demoBucket1);
    }

    @Autowired
    public void setRepo(GenericDao genericDao) {
        this.repo = genericDao;
    }
}
