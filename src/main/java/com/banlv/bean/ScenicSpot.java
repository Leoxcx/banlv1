package com.banlv.bean;

import java.io.Serializable;

public class ScenicSpot implements Serializable {
    private long scenicSpot_id;
    private String scenicSpot_name;
    private long scenicSpot_longitude;
    private long scenicSpot_latitude;
    private long scenicSpot_range;

    public ScenicSpot() {
    }

    public ScenicSpot(long scenicSpot_id, String scenicSpot_name, long scenicSpot_longitude, long scenicSpot_latitude, long scenicSpot_range) {
        this.scenicSpot_id = scenicSpot_id;
        this.scenicSpot_name = scenicSpot_name;
        this.scenicSpot_longitude = scenicSpot_longitude;
        this.scenicSpot_latitude = scenicSpot_latitude;
        this.scenicSpot_range = scenicSpot_range;
    }

    public long getScenicSpot_id() {
        return scenicSpot_id;
    }

    public void setScenicSpot_id(long scenicSpot_id) {
        this.scenicSpot_id = scenicSpot_id;
    }

    public String getScenicSpot_name() {
        return scenicSpot_name;
    }

    public void setScenicSpot_name(String scenicSpot_name) {
        this.scenicSpot_name = scenicSpot_name;
    }

    public long getScenicSpot_longitude() {
        return scenicSpot_longitude;
    }

    public void setScenicSpot_longitude(long scenicSpot_longitude) {
        this.scenicSpot_longitude = scenicSpot_longitude;
    }

    public long getScenicSpot_latitude() {
        return scenicSpot_latitude;
    }

    public void setScenicSpot_latitude(long scenicSpot_latitude) {
        this.scenicSpot_latitude = scenicSpot_latitude;
    }

    public long getScenicSpot_range() {
        return scenicSpot_range;
    }

    public void setScenicSpot_range(long scenicSpot_range) {
        this.scenicSpot_range = scenicSpot_range;
    }

    @Override
    public String toString() {
        return "ScenicSpot{" +
                "scenicSpot_id=" + scenicSpot_id +
                ", scenicSpot_name='" + scenicSpot_name + '\'' +
                ", scenicSpot_longitude=" + scenicSpot_longitude +
                ", scenicSpot_latitude=" + scenicSpot_latitude +
                ", scenicSpot_range=" + scenicSpot_range +
                '}';
    }
}
