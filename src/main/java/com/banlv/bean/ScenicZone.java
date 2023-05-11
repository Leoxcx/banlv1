package com.banlv.bean;

import java.io.Serializable;

public class ScenicZone implements Serializable {
    private long scenicZone_id;
    private long scenicManage_id;
    private String scenicZone_name;
    private Integer city_id;
    private Integer scenicZoneType_id;
    private Float scenicZone_score;
    private String scenicZone_grade;
    private String scenicZone_loaction;
    private String scenicZone_number;
    private Double scenicZone_longitude;
    private Double scenicZone_latitude;
    private Float scenicZone_hot;

    public ScenicZone() {
    }

    public ScenicZone(long scenicZone_id, long scenicManage_id, String scenicZone_name, Integer city_id, Integer scenicZoneType_id, Float scenicZone_score, String scenicZone_grade, String scenicZone_loaction, String scenicZone_number, Double scenicZone_longitude, Double scenicZone_latitude, Float scenicZone_hot) {
        this.scenicZone_id = scenicZone_id;
        this.scenicManage_id = scenicManage_id;
        this.scenicZone_name = scenicZone_name;
        this.city_id = city_id;
        this.scenicZoneType_id = scenicZoneType_id;
        this.scenicZone_score = scenicZone_score;
        this.scenicZone_grade = scenicZone_grade;
        this.scenicZone_loaction = scenicZone_loaction;
        this.scenicZone_number = scenicZone_number;
        this.scenicZone_longitude = scenicZone_longitude;
        this.scenicZone_latitude = scenicZone_latitude;
        this.scenicZone_hot = scenicZone_hot;
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

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public Integer getScenicZoneType_id() {
        return scenicZoneType_id;
    }

    public void setScenicZoneType_id(Integer scenicZoneType_id) {
        this.scenicZoneType_id = scenicZoneType_id;
    }

    public Float getScenicZone_score() {
        return scenicZone_score;
    }

    public void setScenicZone_score(Float scenicZone_score) {
        this.scenicZone_score = scenicZone_score;
    }

    public String getScenicZone_grade() {
        return scenicZone_grade;
    }

    public void setScenicZone_grade(String scenicZone_grade) {
        this.scenicZone_grade = scenicZone_grade;
    }

    public String getScenicZone_loaction() {
        return scenicZone_loaction;
    }

    public void setScenicZone_loaction(String scenicZone_loaction) {
        this.scenicZone_loaction = scenicZone_loaction;
    }

    public String getScenicZone_number() {
        return scenicZone_number;
    }

    public void setScenicZone_number(String scenicZone_number) {
        this.scenicZone_number = scenicZone_number;
    }

    public Double getScenicZone_longitude() {
        return scenicZone_longitude;
    }

    public void setScenicZone_longitude(Double scenicZone_longitude) {
        this.scenicZone_longitude = scenicZone_longitude;
    }

    public Double getScenicZone_latitude() {
        return scenicZone_latitude;
    }

    public void setScenicZone_latitude(Double scenicZone_latitude) {
        this.scenicZone_latitude = scenicZone_latitude;
    }

    public Float getScenicZone_hot() {
        return scenicZone_hot;
    }

    public void setScenicZone_hot(Float scenicZone_hot) {
        this.scenicZone_hot = scenicZone_hot;
    }

    @Override
    public String toString() {
        return "ScenicZone{" +
                "scenicZone_id=" + scenicZone_id +
                ", scenicManage_id=" + scenicManage_id +
                ", scenicZone_name='" + scenicZone_name + '\'' +
                ", city_id=" + city_id +
                ", scenicZoneType_id=" + scenicZoneType_id +
                ", scenicZone_score=" + scenicZone_score +
                ", scenicZone_grade='" + scenicZone_grade + '\'' +
                ", scenicZone_loaction='" + scenicZone_loaction + '\'' +
                ", scenicZone_number='" + scenicZone_number + '\'' +
                ", scenicZone_longitude=" + scenicZone_longitude +
                ", scenicZone_latitude=" + scenicZone_latitude +
                ", scenicZone_hot=" + scenicZone_hot +
                '}';
    }
}
