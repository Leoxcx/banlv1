package com.banlv.bean;

import java.io.Serializable;

public class City implements Serializable {
    private int city_id;
    private String city_name;
    private String city_introduce;
    private String city_weather;
    private float city_temperature;
    private String city_bg;
    private String city_province;

    public City() {
    }

    public City(int city_id, String city_name, String city_introduce, String city_weather, float city_temperature, String city_bg, String city_province) {
        this.city_id = city_id;
        this.city_name = city_name;
        this.city_introduce = city_introduce;
        this.city_weather = city_weather;
        this.city_temperature = city_temperature;
        this.city_bg = city_bg;
        this.city_province = city_province;
    }


    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCity_introduce() {
        return city_introduce;
    }

    public void setCity_introduce(String city_introduce) {
        this.city_introduce = city_introduce;
    }

    public String getCity_weather() {
        return city_weather;
    }

    public void setCity_weather(String city_weather) {
        this.city_weather = city_weather;
    }

    public float getCity_temperature() {
        return city_temperature;
    }

    public void setCity_temperature(float city_temperature) {
        this.city_temperature = city_temperature;
    }

    public String getCity_bg() {
        return city_bg;
    }

    public void setCity_bg(String city_bg) {
        this.city_bg = city_bg;
    }

    public String getCity_province() {
        return city_province;
    }

    public void setCity_province(String city_province) {
        this.city_province = city_province;
    }

    @Override
    public String toString() {
        return "City{" +
                "city_id=" + city_id +
                ", city_name='" + city_name + '\'' +
                ", city_introduce='" + city_introduce + '\'' +
                ", city_weather='" + city_weather + '\'' +
                ", city_temperature=" + city_temperature +
                ", city_bg='" + city_bg + '\'' +
                ", city_province='" + city_province + '\'' +
                '}';
    }
}
