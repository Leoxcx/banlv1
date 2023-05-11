package com.banlv.bean;

import java.io.Serializable;

public class ScenicZone implements Serializable {
    private long scenicZone_id;
    private long scenicManage_id;
    private String scenicZone_name;

    public ScenicZone() {
    }

    public ScenicZone(long scenicZone_id, long scenicManage_id, String scenicZone_name) {
        this.scenicZone_id = scenicZone_id;
        this.scenicManage_id = scenicManage_id;
        this.scenicZone_name = scenicZone_name;
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

    @Override
    public String toString() {
        return "ScenicZone{" +
                "scenicZone_id=" + scenicZone_id +
                ", scenicManage_id=" + scenicManage_id +
                ", scenicZone_name='" + scenicZone_name + '\'' +
                '}';
    }
}
