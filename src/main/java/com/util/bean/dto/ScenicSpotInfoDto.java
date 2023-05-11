package com.util.bean.dto;

import com.banlv.bean.Resource;
import com.banlv.bean.ScenicSpot;

import java.io.Serializable;
import java.util.List;

public class ScenicSpotInfoDto implements Serializable {
    private long scenicSpot_id;
    private String scenicSpot_name;
    private double scenicSpot_longitude;
    private double scenicSpot_latitude;
    private double scenicSpot_range;

    private List<Resource> resources;

    public ScenicSpotInfoDto() {
    }

    public ScenicSpotInfoDto(long scenicSpot_id, String scenicSpot_name, double scenicSpot_longitude, double scenicSpot_latitude, double scenicSpot_range, List<Resource> resources) {
        this.scenicSpot_id = scenicSpot_id;
        this.scenicSpot_name = scenicSpot_name;
        this.scenicSpot_longitude = scenicSpot_longitude;
        this.scenicSpot_latitude = scenicSpot_latitude;
        this.scenicSpot_range = scenicSpot_range;
        this.resources = resources;
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

    public double getScenicSpot_range() {
        return scenicSpot_range;
    }

    public void setScenicSpot_range(double scenicSpot_range) {
        this.scenicSpot_range = scenicSpot_range;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    @Override
    public String toString() {
        return "ScenicSpotInfoDto{" +
                "scenicSpot_id=" + scenicSpot_id +
                ", scenicSpot_name='" + scenicSpot_name + '\'' +
                ", scenicSpot_longitude=" + scenicSpot_longitude +
                ", scenicSpot_latitude=" + scenicSpot_latitude +
                ", scenicSpot_range=" + scenicSpot_range +
                ", resources=" + resources +
                '}';
    }
}
