package com.banlv.bean;

import java.io.Serializable;

public class Picture implements Serializable {
    private long picture_id;
    private String picture_name;
    private String picture_url;
    private String picture_thumbnail;
    private boolean picture_use;
    private String picture_remark;

    public Picture() {
    }

    public Picture(long picture_id, String picture_name, String picture_url, String picture_thumbnail, boolean picture_use, String picture_remark) {
        this.picture_id = picture_id;
        this.picture_name = picture_name;
        this.picture_url = picture_url;
        this.picture_thumbnail = picture_thumbnail;
        this.picture_use = picture_use;
        this.picture_remark = picture_remark;
    }

    public long getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(long picture_id) {
        this.picture_id = picture_id;
    }

    public String getPicture_name() {
        return picture_name;
    }

    public void setPicture_name(String picture_name) {
        this.picture_name = picture_name;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public String getPicture_thumbnail() {
        return picture_thumbnail;
    }

    public void setPicture_thumbnail(String picture_thumbnail) {
        this.picture_thumbnail = picture_thumbnail;
    }

    public boolean isPicture_use() {
        return picture_use;
    }

    public void setPicture_use(boolean picture_use) {
        this.picture_use = picture_use;
    }

    public String getPicture_remark() {
        return picture_remark;
    }

    public void setPicture_remark(String picture_remark) {
        this.picture_remark = picture_remark;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "picture_id=" + picture_id +
                ", picture_name='" + picture_name + '\'' +
                ", picture_url='" + picture_url + '\'' +
                ", picture_thumbnail='" + picture_thumbnail + '\'' +
                ", picture_use=" + picture_use +
                ", picture_remark='" + picture_remark + '\'' +
                '}';
    }
}
