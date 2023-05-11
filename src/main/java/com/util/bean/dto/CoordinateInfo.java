package com.util.bean.dto;

import java.io.Serializable;
import java.util.Objects;

public class CoordinateInfo implements Serializable {
    private int index;
    private Double lng;
    private Double lat;
    private String remark;

    public CoordinateInfo() {
    }

    public CoordinateInfo(int index, Double lng, Double lat, String remark) {
        this.index = index;
        this.lng = lng;
        this.lat = lat;
        this.remark = remark;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CoordinateInfo{" +
                "index=" + index +
                ", scenicSpot_longitude=" + lng +
                ", scenicSpot_latitude=" + lat +
                ", remark='" + remark + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinateInfo that = (CoordinateInfo) o;
        return index == that.index &&
                Objects.equals(lng, that.lng) &&
                Objects.equals(lat, that.lat) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, lng, lat, remark);
    }
}
