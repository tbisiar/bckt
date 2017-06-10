package com.tbisiar.bckt.service;

import com.tbisiar.bckt.domain.Bucket;
import com.tbisiar.bckt.domain.Drop;
import com.tbisiar.bckt.domain.DropType;
import com.tbisiar.bckt.domain.Equipment;
import com.tbisiar.bckt.domain.Location;
import com.tbisiar.bckt.domain.Photo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class BucketService {

    @Autowired
    BucketRepo bucketRepo;

    public void createDemoBucket() {

        Bucket mockRestaurantBucket = createMockBucket(DropType.RESTAURANT);

        bucketRepo.save(mockRestaurantBucket);
    }

    private Bucket createMockBucket(String type) {

        Location demoLocation = new Location("", 0, 0, "", "", false);

        HashSet demoPhotos = new HashSet();
        Photo demoPhoto1 = new Photo("", "", 1);
        Photo demoPhoto2 = new Photo("", "", 2);
        Photo demoPhoto3 = new Photo("", "", 3);
        demoPhotos.add(demoPhoto1);
        demoPhotos.add(demoPhoto2);
        demoPhotos.add(demoPhoto3);

        Equipment demoEquipment = new Equipment("", "");
        DropType restaurantDropType = new DropType(type, demoEquipment, 0, 0);

        Drop demoRestaurantDrop = new Drop("", "", demoPhotos, demoLocation, restaurantDropType, null);

        Bucket demoRestaurantBucket = new Bucket(type + "s", null);

        demoRestaurantBucket.addDrop(demoRestaurantDrop);
        demoRestaurantBucket.addDrop(demoRestaurantDrop);
        return demoRestaurantBucket;
    }

    boolean checkBucket(String type) {
        return DropType.RESTAURANT.equals(type);
    }

    public List<Bucket> loadBucketsForUser(long userId) {
        return bucketRepo.findAll();
    }
}
