package com.banlv.bean;

import java.io.Serializable;

public class ScenicZone_scenicZoneEntry implements Serializable {
    private Long scenicZone_scenicZoneEntry_id;
    private Integer scenicZoneEntry_id;
    private Long scenicZone_id;
    private Boolean scenicZone_scenicSpot_use;

    public ScenicZone_scenicZoneEntry() {
    }

    public ScenicZone_scenicZoneEntry(Long scenicZone_scenicZoneEntry_id, Integer scenicZoneEntry_id, Long scenicZone_id, Boolean scenicZone_scenicSpot_use) {
        this.scenicZone_scenicZoneEntry_id = scenicZone_scenicZoneEntry_id;
        this.scenicZoneEntry_id = scenicZoneEntry_id;
        this.scenicZone_id = scenicZone_id;
        this.scenicZone_scenicSpot_use = scenicZone_scenicSpot_use;
    }

    public Long getScenicZone_scenicZoneEntry_id() {
        return scenicZone_scenicZoneEntry_id;
    }

    public void setScenicZone_scenicZoneEntry_id(Long scenicZone_scenicZoneEntry_id) {
        this.scenicZone_scenicZoneEntry_id = scenicZone_scenicZoneEntry_id;
    }

    public Integer getScenicZoneEntry_id() {
        return scenicZoneEntry_id;
    }

    public void setScenicZoneEntry_id(Integer scenicZoneEntry_id) {
        this.scenicZoneEntry_id = scenicZoneEntry_id;
    }

    public Long getScenicZone_id() {
        return scenicZone_id;
    }

    public void setScenicZone_id(Long scenicZone_id) {
        this.scenicZone_id = scenicZone_id;
    }

    public Boolean getScenicZone_scenicSpot_use() {
        return scenicZone_scenicSpot_use;
    }

    public void setScenicZone_scenicSpot_use(Boolean scenicZone_scenicSpot_use) {
        this.scenicZone_scenicSpot_use = scenicZone_scenicSpot_use;
    }

    @Override
    public String toString() {
        return "ScenicZone_scenicZoneEntry{" +
                "scenicZone_scenicZoneEntry_id=" + scenicZone_scenicZoneEntry_id +
                ", scenicZoneEntry_id=" + scenicZoneEntry_id +
                ", scenicZone_id=" + scenicZone_id +
                ", scenicZone_scenicSpot_use=" + scenicZone_scenicSpot_use +
                '}';
    }
}
