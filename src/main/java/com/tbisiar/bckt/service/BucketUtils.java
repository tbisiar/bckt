package com.tbisiar.bckt.service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tbis163 on 11/06/17.
 */
public class BucketUtils {

    public static <T> Set<T> newHashSet(T... objs) {
        Set<T> set = new HashSet<T>();
        for (T o : objs) {
            set.add(o);
        }
        return set;
    }
}
