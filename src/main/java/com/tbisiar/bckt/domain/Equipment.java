package com.tbisiar.bckt.domain;

/**
 * Created by tbis163 on 6/02/17.
 */
public class Equipment {

    private final long id;
    private final String title;
    private final String description;

    public Equipment(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
