package com.banlv.bean;

import java.io.Serializable;

public class ScenicZone implements Serializable {
    private long scenicZone_id;
    private long scenicManage_id;
    private String scenicZone_name;
    private String scenicZone_introduce;
    private int city_id;
    private int scenicZoneType_id;
    private float scenicZone_score;
    private String scenicZone_grade;
    private String scenicZone_location;
    private String scenicZone_number;
    private double scenicZone_longitude;
    private double scenicZone_latitude;
    private float scenicZone_hot;
    private String scenicZone_bg;

    public ScenicZone() {
    }

    public ScenicZone(long scenicZone_id, long scenicManage_id, String scenicZone_name, String scenicZone_introduce, int city_id, int scenicZoneType_id, float scenicZone_score, String scenicZone_grade, String scenicZone_location, String scenicZone_number, double scenicZone_longitude, double scenicZone_latitude, float scenicZone_hot, String scenicZone_bg) {
        this.scenicZone_id = scenicZone_id;
        this.scenicManage_id = scenicManage_id;
        this.scenicZone_name = scenicZone_name;
        this.scenicZone_introduce = scenicZone_introduce;
        this.city_id = city_id;
        this.scenicZoneType_id = scenicZoneType_id;
        this.scenicZone_score = scenicZone_score;
        this.scenicZone_grade = scenicZone_grade;
        this.scenicZone_location = scenicZone_location;
        this.scenicZone_number = scenicZone_number;
        this.scenicZone_longitude = scenicZone_longitude;
        this.scenicZone_latitude = scenicZone_latitude;
        this.scenicZone_hot = scenicZone_hot;
        this.scenicZone_bg = scenicZone_bg;
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

    public String getScenicZone_introduce() {
        return scenicZone_introduce;
    }

    public void setScenicZone_introduce(String scenicZone_introduce) {
        this.scenicZone_introduce = scenicZone_introduce;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public int getScenicZoneType_id() {
        return scenicZoneType_id;
    }

    public void setScenicZoneType_id(int scenicZoneType_id) {
        this.scenicZoneType_id = scenicZoneType_id;
    }

    public float getScenicZone_score() {
        return scenicZone_score;
    }

    public void setScenicZone_score(float scenicZone_score) {
        this.scenicZone_score = scenicZone_score;
    }

    public String getScenicZone_grade() {
        return scenicZone_grade;
    }

    public void setScenicZone_grade(String scenicZone_grade) {
        this.scenicZone_grade = scenicZone_grade;
    }

    public String getScenicZone_location() {
        return scenicZone_location;
    }

    public void setScenicZone_location(String scenicZone_location) {
        this.scenicZone_location = scenicZone_location;
    }

    public String getScenicZone_number() {
        return scenicZone_number;
    }

    public void setScenicZone_number(String scenicZone_number) {
        this.scenicZone_number = scenicZone_number;
    }

    public double getScenicZone_longitude() {
        return scenicZone_longitude;
    }

    public void setScenicZone_longitude(double scenicZone_longitude) {
        this.scenicZone_longitude = scenicZone_longitude;
    }

    public double getScenicZone_latitude() {
        return scenicZone_latitude;
    }

    public void setScenicZone_latitude(double scenicZone_latitude) {
        this.scenicZone_latitude = scenicZone_latitude;
    }

    public float getScenicZone_hot() {
        return scenicZone_hot;
    }

    public void setScenicZone_hot(float scenicZone_hot) {
        this.scenicZone_hot = scenicZone_hot;
    }

    public String getScenicZone_bg() {
        return scenicZone_bg;
    }

    public void setScenicZone_bg(String scenicZone_bg) {
        this.scenicZone_bg = scenicZone_bg;
    }

    @Override
    public String toString() {
        return "ScenicZone{" +
                "scenicZone_id=" + scenicZone_id +
                ", scenicManage_id=" + scenicManage_id +
                ", scenicZone_name='" + scenicZone_name + '\'' +
                ", scenicZone_introduce='" + scenicZone_introduce + '\'' +
                ", city_id=" + city_id +
                ", scenicZoneType_id=" + scenicZoneType_id +
                ", scenicZone_score=" + scenicZone_score +
                ", scenicZone_grade='" + scenicZone_grade + '\'' +
                ", scenicZone_location='" + scenicZone_location + '\'' +
                ", scenicZone_number='" + scenicZone_number + '\'' +
                ", scenicZone_longitude=" + scenicZone_longitude +
                ", scenicZone_latitude=" + scenicZone_latitude +
                ", scenicZone_hot=" + scenicZone_hot +
                ", scenicZone_bg='" + scenicZone_bg + '\'' +
                '}';
    }
}
