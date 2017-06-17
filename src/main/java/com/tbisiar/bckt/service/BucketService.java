package com.tbisiar.bckt.service;

import com.tbisiar.bckt.domain.Bucket;
import com.tbisiar.bckt.domain.Drop;
import com.tbisiar.bckt.domain.DropType;
import com.tbisiar.bckt.domain.Equipment;
import com.tbisiar.bckt.domain.Location;
import com.tbisiar.bckt.domain.MongoObject;
import com.tbisiar.bckt.domain.Photo;
import com.tbisiar.bckt.domain.Restriction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BucketService {

    private GenericDao repo;

    private static Logger log = LoggerFactory.getLogger(BucketService.class);

    public void createDemoBucket() {
        log.debug("Creating demo buckets");

        Bucket demoBucket = new Bucket("Content", new HashSet<>());

        demoBucket.addDrop(createDemoRestaurantDrop());
        demoBucket.addDrop(createDemoActivityDrop());
        demoBucket.addDrop(createDemoEventDrop());

        repo.save(demoBucket);
    }

    private Drop createDemoRestaurantDrop() {
        Set<Photo> photoSet = new HashSet<>();
        Photo photo = new Photo(
                "../image/dinner.jpg",
                "Jamie Bisiar",
                1);
        photoSet.add(photo);

        Location location = new Location(
                "Auckland Central",
                -36.8443166,
                174.7678149,
                "52 Tyler St, Auckland, 1010, New Zealand",
                "Enter from Tyler or Quay Street and proceed to level 2 via elevator or stairs",
                true);

        DropType dropType = new DropType(
                "Restaurant",
                null,
                4,
                8);

        Set<Restriction> restrictionSet = new HashSet<>();
        Restriction restriction = new Restriction(
                null,
                null,
                null,
                null,
                "1-2 hrs");
        restrictionSet.add(restriction);

        return new Drop(
                "Ostro Brasserie & Bar",
                "Sleek, modern place offering inventive NZ cuisine, cocktails & panoramic views of Waitemata Harbour. - Google",
                photoSet,
                location,
                dropType,
                restrictionSet);
    }

    private Drop createDemoActivityDrop() {
        Set<Photo> photoSet = new HashSet<>();
        Photo photo = new Photo(
                "../image/waipuCaves.jpg",
                "Terry Bisiar",
                1);
        photoSet.add(photo);

        Location location = new Location(
                "Northland",
                -35.9344232,
                174.0692087,
                null,
                "The track starts at the Whangarei District Council reserve, by the cave's entrance, off Waipu Caves Road. There is a large flat area suitable for picnics and a Whangarei District Council administered public toilet at this point. Note: the entrance to the caves is directly across the Council Reserve before the track starts. Look across the large flat grassed area for the orange post, which indicates the start of the track.",
                true);

        Equipment equipment = new Equipment(
                "Jandals",
                "Protect you feet while allowing them to get wet!");

        DropType dropType = new DropType(
                DropType.ACTIVITY,
                equipment,
                4,
                5);

        Set<Restriction> restrictionSet = new HashSet<>();
        Restriction restriction = new Restriction(
                null,
                null,
                null,
                null,
                "1-2 hrs");
        restrictionSet.add(restriction);

        return new Drop(
                "Waipu Caves",
                "Get lost in some unbelievable glow worm caves",
                photoSet,
                location,
                dropType,
                restrictionSet);
    }

    private Drop createDemoEventDrop() {
    // save a photo
    Photo demoPhoto1 = new Photo(
            "../image/laneway2017.jpg",
            "Terry Bisiar",
            0);

    // save a location
    Location demoLocation1 = new Location(
            "Albert Park",
            -36.8518108,
            174.7688307,
            "N/A",
            null,
            false);

    // save a dropType
    DropType demoDropType1 = new DropType(
            DropType.EVENT,
            null,
            4,
            2);

    // save a drop
    return new Drop(
            "Laneway Festival",
            "Hipster music festival originating in Australia",
            BucketUtils.newHashSet(demoPhoto1),
            demoLocation1,
            demoDropType1,
            null);
    }

    boolean checkBucket(String type) {
        return DropType.RESTAURANT.equals(type);
    }

    public List<MongoObject> loadBucketsForUser(long userId) {
        log.debug("Loading buckets for {}", userId);
        return repo.findAll();
    }

    @Autowired
    public void setRepo(GenericDao genericDao) {
        this.repo = genericDao;
    }

}
