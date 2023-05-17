package com.banlv.bean;

import java.io.Serializable;

public class ScenicZone_scenicZoneEntry implements Serializable {
    private long scenicZone_scenicZoneEntry_id;
    private int scenicZoneEntry_id;
    private long scenicZone_id;
    private boolean scenicZone_scenicSpot_use;

    public ScenicZone_scenicZoneEntry() {
    }



    public ScenicZone_scenicZoneEntry(long scenicZone_scenicZoneEntry_id, int scenicZoneEntry_id, long scenicZone_id, boolean scenicZone_scenicSpot_use) {
        this.scenicZone_scenicZoneEntry_id = scenicZone_scenicZoneEntry_id;
        this.scenicZoneEntry_id = scenicZoneEntry_id;
        this.scenicZone_id = scenicZone_id;
        this.scenicZone_scenicSpot_use = scenicZone_scenicSpot_use;
    }

    public long getScenicZone_scenicZoneEntry_id() {
        return scenicZone_scenicZoneEntry_id;
    }

    public void setScenicZone_scenicZoneEntry_id(long scenicZone_scenicZoneEntry_id) {
        this.scenicZone_scenicZoneEntry_id = scenicZone_scenicZoneEntry_id;
    }

    public int getScenicZoneEntry_id() {
        return scenicZoneEntry_id;
    }

    public void setScenicZoneEntry_id(int scenicZoneEntry_id) {
        this.scenicZoneEntry_id = scenicZoneEntry_id;
    }

    public long getScenicZone_id() {
        return scenicZone_id;
    }

    public void setScenicZone_id(long scenicZone_id) {
        this.scenicZone_id = scenicZone_id;
    }

    public boolean isScenicZone_scenicSpot_use() {
        return scenicZone_scenicSpot_use;
    }

    public void setScenicZone_scenicSpot_use(boolean scenicZone_scenicSpot_use) {
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
