package com.banlv.bean;

import java.io.Serializable;

public class ScenicManage implements Serializable {
    private long scenicManage_id;
    private long scenicManage_name;
    private long scenicManage_password;
    private long scenicManage_phone;

    public ScenicManage() {
    }

    public ScenicManage(long scenicManage_id, long scenicManage_name, long scenicManage_password, long scenicManage_phone) {
        this.scenicManage_id = scenicManage_id;
        this.scenicManage_name = scenicManage_name;
        this.scenicManage_password = scenicManage_password;
        this.scenicManage_phone = scenicManage_phone;
    }

    public long getScenicManage_id() {
        return scenicManage_id;
    }

    public void setScenicManage_id(long scenicManage_id) {
        this.scenicManage_id = scenicManage_id;
    }

    public long getScenicManage_name() {
        return scenicManage_name;
    }

    public void setScenicManage_name(long scenicManage_name) {
        this.scenicManage_name = scenicManage_name;
    }

    public long getScenicManage_password() {
        return scenicManage_password;
    }

    public void setScenicManage_password(long scenicManage_password) {
        this.scenicManage_password = scenicManage_password;
    }

    public long getScenicManage_phone() {
        return scenicManage_phone;
    }

    public void setScenicManage_phone(long scenicManage_phone) {
        this.scenicManage_phone = scenicManage_phone;
    }

    @Override
    public String toString() {
        return "ScenicManage{" +
                "scenicManage_id=" + scenicManage_id +
                ", scenicManage_name=" + scenicManage_name +
                ", scenicManage_password=" + scenicManage_password +
                ", scenicManage_phone=" + scenicManage_phone +
                '}';
    }
}
