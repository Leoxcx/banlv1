package com.banlv.bean;

import java.io.Serializable;

public class ScenicSpot_resource implements Serializable {
    private long scenicSpot_resource_id;
    private long scenicSpot_id;
    private long resource_id;
    private boolean scenicSpot_resource_use;

    public ScenicSpot_resource() {
    }

    public ScenicSpot_resource(long scenicSpot_resource_id, long scenicSpot_id, long resource_id, boolean scenicSpot_resource_use) {
        this.scenicSpot_resource_id = scenicSpot_resource_id;
        this.scenicSpot_id = scenicSpot_id;
        this.resource_id = resource_id;
        this.scenicSpot_resource_use = scenicSpot_resource_use;
    }

    public long getScenicSpot_resource_id() {
        return scenicSpot_resource_id;
    }

    public void setScenicSpot_resource_id(long scenicSpot_resource_id) {
        this.scenicSpot_resource_id = scenicSpot_resource_id;
    }

    public long getScenicSpot_id() {
        return scenicSpot_id;
    }

    public void setScenicSpot_id(long scenicSpot_id) {
        this.scenicSpot_id = scenicSpot_id;
    }

    public long getResource_id() {
        return resource_id;
    }

    public void setResource_id(long resource_id) {
        this.resource_id = resource_id;
    }

    public boolean isScenicSpot_resource_use() {
        return scenicSpot_resource_use;
    }

    public void setScenicSpot_resource_use(boolean scenicSpot_resource_use) {
        this.scenicSpot_resource_use = scenicSpot_resource_use;
    }

    @Override
    public String toString() {
        return "ScenicSpot_resource{" +
                "scenicSpot_resource_id=" + scenicSpot_resource_id +
                ", scenicSpot_id=" + scenicSpot_id +
                ", resource_id=" + resource_id +
                ", scenicSpot_resource_use=" + scenicSpot_resource_use +
                '}';
    }
}
