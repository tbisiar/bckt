package com.tbisiar.bckt.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class BucketUtils {

    private BucketUtils() {
    }

    // Based on https://stackoverflow.com/questions/2041778/how-to-initialize-hashset-values-by-construction
    static <T> Set<T> newHashSet(T... objs) {
        Set<T> set = new HashSet<>();
        set.addAll(Arrays.asList(objs));
        return set;
    }
}
