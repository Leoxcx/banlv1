package com.banlv.bean;

import java.io.Serializable;

public class ScenicZone_scenicSpot implements Serializable {
    private long scenicZone_scenicSpot_id;
    private long scenicZone_id;
    private long scenicSpot_id;
    private boolean scenicZone_scenicSpot_use;

    public ScenicZone_scenicSpot() {
    }

    public ScenicZone_scenicSpot(long scenicZone_scenicSpot_id, long scenicZone_id, long scenicSpot_id, boolean scenicZone_scenicSpot_use) {
        this.scenicZone_scenicSpot_id = scenicZone_scenicSpot_id;
        this.scenicZone_id = scenicZone_id;
        this.scenicSpot_id = scenicSpot_id;
        this.scenicZone_scenicSpot_use = scenicZone_scenicSpot_use;
    }

    public long getScenicZone_scenicSpot_id() {
        return scenicZone_scenicSpot_id;
    }

    public void setScenicZone_scenicSpot_id(long scenicZone_scenicSpot_id) {
        this.scenicZone_scenicSpot_id = scenicZone_scenicSpot_id;
    }

    public long getScenicZone_id() {
        return scenicZone_id;
    }

    public void setScenicZone_id(long scenicZone_id) {
        this.scenicZone_id = scenicZone_id;
    }

    public long getScenicSpot_id() {
        return scenicSpot_id;
    }

    public void setScenicSpot_id(long scenicSpot_id) {
        this.scenicSpot_id = scenicSpot_id;
    }

    public boolean isScenicZone_scenicSpot_use() {
        return scenicZone_scenicSpot_use;
    }

    public void setScenicZone_scenicSpot_use(boolean scenicZone_scenicSpot_use) {
        this.scenicZone_scenicSpot_use = scenicZone_scenicSpot_use;
    }

    @Override
    public String toString() {
        return "ScenicZone_scenicSpot{" +
                "scenicZone_scenicSpot_id=" + scenicZone_scenicSpot_id +
                ", scenicZone_id=" + scenicZone_id +
                ", scenicSpot_id=" + scenicSpot_id +
                ", scenicZone_scenicSpot_use=" + scenicZone_scenicSpot_use +
                '}';
    }
}
