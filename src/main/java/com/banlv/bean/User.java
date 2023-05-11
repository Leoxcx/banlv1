package com.banlv.bean;

import java.io.Serializable;

public class User implements Serializable {
    private long user_id;
    private String user_openid;
    private String user_name;
    private int user_points;
    private int user_vip;

    public User() {
    }

    public User(long user_id, String user_openid, String user_name, int user_points, int user_vip) {
        this.user_id = user_id;
        this.user_openid = user_openid;
        this.user_name = user_name;
        this.user_points = user_points;
        this.user_vip = user_vip;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUser_openid() {
        return user_openid;
    }

    public void setUser_openid(String user_openid) {
        this.user_openid = user_openid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_points() {
        return user_points;
    }

    public void setUser_points(int user_points) {
        this.user_points = user_points;
    }

    public int getUser_vip() {
        return user_vip;
    }

    public void setUser_vip(int user_vip) {
        this.user_vip = user_vip;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_openid='" + user_openid + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_points=" + user_points +
                ", user_vip=" + user_vip +
                '}';
    }
}
