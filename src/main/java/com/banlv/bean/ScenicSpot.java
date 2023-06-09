package com.banlv.bean;

import java.io.Serializable;

public class ScenicSpot implements Serializable {
    private long scenicSpot_id;
    private String scenicSpot_name;
    private double scenicSpot_longitude;
    private double scenicSpot_latitude;
    private long scenicSpot_range;
    private int scenicSpot_num;

    public ScenicSpot() {
    }

    public ScenicSpot(long scenicSpot_id, String scenicSpot_name, double scenicSpot_longitude, double scenicSpot_latitude, long scenicSpot_range, int scenicSpot_num) {
        this.scenicSpot_id = scenicSpot_id;
        this.scenicSpot_name = scenicSpot_name;
        this.scenicSpot_longitude = scenicSpot_longitude;
        this.scenicSpot_latitude = scenicSpot_latitude;
        this.scenicSpot_range = scenicSpot_range;
        this.scenicSpot_num = scenicSpot_num;
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

    public double getScenicSpot_longitude() {
        return scenicSpot_longitude;
    }

    public void setScenicSpot_longitude(double scenicSpot_longitude) {
        this.scenicSpot_longitude = scenicSpot_longitude;
    }

    public double getScenicSpot_latitude() {
        return scenicSpot_latitude;
    }

    public void setScenicSpot_latitude(double scenicSpot_latitude) {
        this.scenicSpot_latitude = scenicSpot_latitude;
    }

    public long getScenicSpot_range() {
        return scenicSpot_range;
    }

    public void setScenicSpot_range(long scenicSpot_range) {
        this.scenicSpot_range = scenicSpot_range;
    }

    public int getScenicSpot_num() {
        return scenicSpot_num;
    }

    public void setScenicSpot_num(int scenicSpot_num) {
        this.scenicSpot_num = scenicSpot_num;
    }

    @Override
    public String toString() {
        return "ScenicSpot{" +
                "scenicSpot_id=" + scenicSpot_id +
                ", scenicSpot_name='" + scenicSpot_name + '\'' +
                ", scenicSpot_longitude=" + scenicSpot_longitude +
                ", scenicSpot_latitude=" + scenicSpot_latitude +
                ", scenicSpot_range=" + scenicSpot_range +
                ", scenicSpot_num=" + scenicSpot_num +
                '}';
    }
}
