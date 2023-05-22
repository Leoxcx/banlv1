package com.banlv.bean;

import java.io.Serializable;

public class ScenicZoneType implements Serializable {
    private int scenicZoneType_id;
    private String scenicZoneType_name;

    public ScenicZoneType() {
    }

    public ScenicZoneType(int scenicZoneType_id, String scenicZoneType_name) {
        this.scenicZoneType_id = scenicZoneType_id;
        this.scenicZoneType_name = scenicZoneType_name;
    }

    public int getScenicZoneType_id() {
        return scenicZoneType_id;
    }

    public void setScenicZoneType_id(int scenicZoneType_id) {
        this.scenicZoneType_id = scenicZoneType_id;
    }

    public String getScenicZoneType_name() {
        return scenicZoneType_name;
    }

    public void setScenicZoneType_name(String scenicZoneType_name) {
        this.scenicZoneType_name = scenicZoneType_name;
    }

    @Override
    public String toString() {
        return "ScenicZoneType{" +
                "scenicZoneType_id=" + scenicZoneType_id +
                ", scenicZoneType_name='" + scenicZoneType_name + '\'' +
                '}';
    }
}
