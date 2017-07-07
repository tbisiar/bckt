package com.tbisiar.bckt.service;

import com.tbisiar.bckt.domain.Photo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class BucketUtils {

    private BucketUtils(){}

    static Set<Photo> newPhotoSet(Photo... objs) {
        Set<Photo> photoSet = new HashSet<>();
        photoSet.addAll(Arrays.asList(objs));
        return photoSet;
    }
}
