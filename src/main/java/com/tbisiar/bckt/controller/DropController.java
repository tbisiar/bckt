package com.tbisiar.bckt.controller;

import com.tbisiar.bckt.domain.Drop;
import com.tbisiar.bckt.service.BucketUtils;
import com.tbisiar.bckt.service.DropService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tbis163 on 17/07/17.
 */
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

    @Autowired
    void setDropService(DropService dropService) {
        this.dropService = dropService;
    }
}
