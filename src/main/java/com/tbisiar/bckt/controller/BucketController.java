package com.tbisiar.bckt.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.tbisiar.bckt.domain.Bucket;
import com.tbisiar.bckt.domain.Drop;
import com.tbisiar.bckt.domain.DropType;
import com.tbisiar.bckt.domain.Equipment;
import com.tbisiar.bckt.domain.Location;
import com.tbisiar.bckt.domain.Photo;
import com.tbisiar.bckt.domain.Restriction;

@RestController
public class BucketController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value="/bucket", method = RequestMethod.GET)
    public Bucket greeting(@RequestParam(value="name", defaultValue="World") String name) {
        Drop drop1 = createMockDrop1();
        Set<Drop> dropSet = new HashSet<>();
        dropSet.add(drop1);

        return new Bucket(counter.incrementAndGet(),
                String.format(template, name), dropSet);
    }

    private Drop createMockDrop1() {
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
                1,
                "Jandals",
                "Protect you feet while allowing them to get wet!");

        DropType dropType = new DropType(
                "Activity",
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
                1,
                "Waipu Caves",
                "Get lost in some unbelievable glow worm caves",
                photoSet,
                location,
                dropType,
                restrictionSet);
    }
}