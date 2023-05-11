package com.banlv.bean;

import java.io.Serializable;

public class ScenicManage implements Serializable {
    private long scenicManage_id;
    private String scenicManage_name;
    private String scenicManage_password;
    private String scenicManage_phone;

    public ScenicManage() {
    }

    public ScenicManage(long scenicManage_id, String scenicManage_name, String scenicManage_password, String scenicManage_phone) {
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

    public String getScenicManage_name() {
        return scenicManage_name;
    }

    public void setScenicManage_name(String scenicManage_name) {
        this.scenicManage_name = scenicManage_name;
    }

    public String getScenicManage_password() {
        return scenicManage_password;
    }

    public void setScenicManage_password(String scenicManage_password) {
        this.scenicManage_password = scenicManage_password;
    }

    public String getScenicManage_phone() {
        return scenicManage_phone;
    }

    public void setScenicManage_phone(String scenicManage_phone) {
        this.scenicManage_phone = scenicManage_phone;
    }

    @Override
    public String toString() {
        return "ScenicManage{" +
                "scenicManage_id=" + scenicManage_id +
                ", scenicManage_name='" + scenicManage_name + '\'' +
                ", scenicManage_password='" + scenicManage_password + '\'' +
                ", scenicManage_phone='" + scenicManage_phone + '\'' +
                '}';
    }
}
