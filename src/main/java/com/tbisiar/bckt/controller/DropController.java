package com.tbisiar.bckt.controller;

import com.tbisiar.bckt.domain.Drop;
import com.tbisiar.bckt.service.BucketUtils;
import com.tbisiar.bckt.service.DemoUtils;
import com.tbisiar.bckt.service.DropService;

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

import java.util.List;

@RestController
public class DropController {

    private static final Logger log = LoggerFactory.getLogger(DropController.class);

    private DropService dropService;

    @CrossOrigin(origins = BucketUtils.CROSS_ORIGIN_URI)
    @RequestMapping(value = "/drops", method = RequestMethod.GET)
    public List<Drop> loadDrops(@RequestParam(value = "userId") String userId,
                                    @RequestParam(value = "dropId", required = false) String dropId) {
        List<Drop> drops;
        if (dropId != null) {
            drops = dropService.loadDropById(userId, dropId);
        } else {
            drops = dropService.loadDropsForUser(userId);
        }
        log.info("{} drops loaded for {}", drops.size(), userId);
        return drops;
    }

    @ResponseBody
    @CrossOrigin(origins = BucketUtils.CROSS_ORIGIN_URI)
    @RequestMapping(value = "/drops/save", method = RequestMethod.POST)
    public List<Drop> saveDrop(@RequestBody Drop drop) {
        String userId = DemoUtils.DEMO_USER_ID;
        drop.setOwner(userId);
        dropService.saveDrop(drop);
        return dropService.loadDropsForUser(userId);
    }

    @CrossOrigin(origins = BucketUtils.CROSS_ORIGIN_URI)
    @RequestMapping(value = "/drops/format/{dropId}", method = RequestMethod.DELETE)
    public List<Drop> deleteDrop(@PathVariable String dropId) {
        String demoUserId = DemoUtils.DEMO_USER_ID;
        dropService.deleteDrop(demoUserId, dropId);
        return dropService.loadDropsForUser(demoUserId);
    }

    @Autowired
    void setDropService(DropService dropService) {
        this.dropService = dropService;
    }
}
