package com.util.bean.dto;

import java.io.Serializable;
import java.util.Objects;

public class CoordinateInfoDto implements Serializable {

    private Double scenicSpot_longitude;
    private Double scenicSpot_latitude;
    private String remark;

    public CoordinateInfoDto() {
    }

    public CoordinateInfoDto(Double scenicSpot_longitude, Double scenicSpot_latitude, String remark) {
        this.scenicSpot_longitude = scenicSpot_longitude;
        this.scenicSpot_latitude = scenicSpot_latitude;
        this.remark = remark;
    }


    public Double getScenicSpot_longitude() {
        return scenicSpot_longitude;
    }

    public void setScenicSpot_longitude(Double scenicSpot_longitude) {
        this.scenicSpot_longitude = scenicSpot_longitude;
    }

    public Double getScenicSpot_latitude() {
        return scenicSpot_latitude;
    }

    public void setScenicSpot_latitude(Double scenicSpot_latitude) {
        this.scenicSpot_latitude = scenicSpot_latitude;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CoordinateInfoDto{" +
                ", scenicSpot_longitude=" + scenicSpot_longitude +
                ", scenicSpot_latitude=" + scenicSpot_latitude +
                ", remark='" + remark + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinateInfoDto that = (CoordinateInfoDto) o;
        return Objects.equals(scenicSpot_longitude, that.scenicSpot_longitude) &&
                Objects.equals(scenicSpot_latitude, that.scenicSpot_latitude) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scenicSpot_longitude, scenicSpot_latitude, remark);
    }
}
