package com.banlv.bean;

import java.io.Serializable;

public class ScenicZoneType implements Serializable {
    private Integer scenicZoneEntry_id;
    private String scenicZoneEntry_name;

    public ScenicZoneType() {
    }

    public ScenicZoneType(Integer scenicZoneEntry_id, String scenicZoneEntry_name) {
        this.scenicZoneEntry_id = scenicZoneEntry_id;
        this.scenicZoneEntry_name = scenicZoneEntry_name;
    }

    public Integer getScenicZoneEntry_id() {
        return scenicZoneEntry_id;
    }

    public void setScenicZoneEntry_id(Integer scenicZoneEntry_id) {
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
        return "ScenicZoneType{" +
                "scenicZoneEntry_id=" + scenicZoneEntry_id +
                ", scenicZoneEntry_name='" + scenicZoneEntry_name + '\'' +
                '}';
    }
}
