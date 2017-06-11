package com.tbisiar.bckt.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BucketUtils {

    private BucketUtils(){}

    // Based on https://stackoverflow.com/questions/2041778/how-to-initialize-hashset-values-by-construction
    public static <T> Set<T> newHashSet(T... objs) {
        Set<T> set = new HashSet<T>();
        set.addAll(Arrays.asList(objs));
        for (T o : objs) {
            set.add(o);
        }
        return set;
    }
}
