package com.tbisiar.bckt.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by tbis163 on 6/02/17.
 */
@Entity
public class DropType extends MongoObject {

    public static final String RESTAURANT = "Restaurant";
    public static final String ACTIVITY = "Activity";
    public static final String EVENT = "Event";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    // Todo: make this a configurable list
    // i.e. - Restaurant, Activity, Event, etc.
    private String type;
    private Equipment equipment;
    private int effortLevel; //TODO: make this an enum
    private int skillLevel; //TODO: make this an enum

    // This constructor is required for JPA
    protected DropType(){}

    public DropType(String type, Equipment equipment, int effortLevel, int skillLevel) {
        // TODO: add check on String 'type'
        this.type = type;
        this.equipment = equipment;
        this.effortLevel = effortLevel;
        this.skillLevel = skillLevel;
    }

    public long getId() { return id; }

    public String getType() {
        return type;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public int getEffortLevel() {
        return effortLevel;
    }

    public int getSkillLevel() {
        return skillLevel;
    }
}
