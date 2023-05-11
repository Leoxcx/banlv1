package com.util.bean.dto;

import com.banlv.bean.ScenicSpot;

import java.io.Serializable;
import java.util.List;

public class ScenicZoneInfoDto implements Serializable {
    private long scenicZone_id;
    private long scenicManage_id;
    private String scenicZone_name;
    //景点信息
    private List<ScenicSpot> scenicSpots;

    public ScenicZoneInfoDto() {
    }

    public ScenicZoneInfoDto(long scenicZone_id, long scenicManage_id, String scenicZone_name, List<ScenicSpot> scenicSpots) {
        this.scenicZone_id = scenicZone_id;
        this.scenicManage_id = scenicManage_id;
        this.scenicZone_name = scenicZone_name;
        this.scenicSpots = scenicSpots;
    }

    public long getScenicZone_id() {
        return scenicZone_id;
    }

    public void setScenicZone_id(long scenicZone_id) {
        this.scenicZone_id = scenicZone_id;
    }

    public long getScenicManage_id() {
        return scenicManage_id;
    }

    public void setScenicManage_id(long scenicManage_id) {
        this.scenicManage_id = scenicManage_id;
    }

    public String getScenicZone_name() {
        return scenicZone_name;
    }

    public void setScenicZone_name(String scenicZone_name) {
        this.scenicZone_name = scenicZone_name;
    }

    public List<ScenicSpot> getScenicSpots() {
        return scenicSpots;
    }

    public void setScenicSpots(List<ScenicSpot> scenicSpots) {
        this.scenicSpots = scenicSpots;
    }

    @Override
    public String toString() {
        return "ScenicZoneInfoDto{" +
                "scenicZone_id=" + scenicZone_id +
                ", scenicManage_id=" + scenicManage_id +
                ", scenicZone_name='" + scenicZone_name + '\'' +
                ", scenicSpots=" + scenicSpots +
                '}';
    }
}
