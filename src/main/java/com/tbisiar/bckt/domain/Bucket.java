package com.tbisiar.bckt.domain;

import java.util.Set;

/**
* Created by tbis163 on 6/02/17.
*/
public class Bucket {

    private final long id;
    private final String content;
    private final Set<Drop> drops;

    public Bucket(long id, String content, Set<Drop> drops) {
        this.id = id;
        this.content = content;
        this.drops = drops;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Set<Drop> getDrops() {
        return drops;
    }

}
