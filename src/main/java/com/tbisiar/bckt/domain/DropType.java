package com.tbisiar.bckt.domain;

/**
 * Created by tbis163 on 6/02/17.
 */
public class DropType {

    // Todo: make this a configurable list
    // i.e. - Restaurant, Activity, Event, etc.
    private final String type;
    private final Equipment equipment;
    private final int effortLevel; //TODO: make this an enum
    private final int skillLevel; //TODO: make this an enum

    public DropType(String type, Equipment equipment, int effortLevel, int skillLevel) {
        this.type = type;
        this.equipment = equipment;
        this.effortLevel = effortLevel;
        this.skillLevel = skillLevel;
    }

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
