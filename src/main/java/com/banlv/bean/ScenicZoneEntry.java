package com.banlv.bean;

public class ScenicZoneEntry {
    private int scenicZoneEntry_id;
    private String scenicZoneEntry_name;

    public ScenicZoneEntry() {
    }

    public ScenicZoneEntry(int scenicZoneEntry_id, String scenicZoneEntry_name) {
        this.scenicZoneEntry_id = scenicZoneEntry_id;
        this.scenicZoneEntry_name = scenicZoneEntry_name;
    }

    public int getScenicZoneEntry_id() {
        return scenicZoneEntry_id;
    }

    public void setScenicZoneEntry_id(int scenicZoneEntry_id) {
        this.scenicZoneEntry_id = scenicZoneEntry_id;
    }

    public String getScenicZoneEntry_name() {
        return scenicZoneEntry_name;
    }

    public void setScenicZoneEntry_name(String scenicZoneEntry_name) {
        this.scenicZoneEntry_name = scenicZoneEntry_name;
    }

    @Override
    public String toString() {
        return "ScenicZoneEntry{" +
                "scenicZoneEntry_id=" + scenicZoneEntry_id +
                ", scenicZoneEntry_name='" + scenicZoneEntry_name + '\'' +
                '}';
    }
}
