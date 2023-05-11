package com.banlv.bean;

import java.io.Serializable;
import java.sql.Time;

public class UserPlay implements Serializable {
    private long userPlay_id;
    private long user_id;
    private long resource_id;
    private Time userPlay_time;
    private Time userPlay_to;

    public UserPlay() {
    }

    public UserPlay(long userPlay_id, long user_id, long resource_id, Time userPlay_time, Time userPlay_to) {
        this.userPlay_id = userPlay_id;
        this.user_id = user_id;
        this.resource_id = resource_id;
        this.userPlay_time = userPlay_time;
        this.userPlay_to = userPlay_to;
    }

    public long getUserPlay_id() {
        return userPlay_id;
    }

    public void setUserPlay_id(long userPlay_id) {
        this.userPlay_id = userPlay_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getResource_id() {
        return resource_id;
    }

    public void setResource_id(long resource_id) {
        this.resource_id = resource_id;
    }

    public Time getUserPlay_time() {
        return userPlay_time;
    }

    public void setUserPlay_time(Time userPlay_time) {
        this.userPlay_time = userPlay_time;
    }

    public Time getUserPlay_to() {
        return userPlay_to;
    }

    public void setUserPlay_to(Time userPlay_to) {
        this.userPlay_to = userPlay_to;
    }

    @Override
    public String toString() {
        return "UserPlay{" +
                "userPlay_id=" + userPlay_id +
                ", user_id=" + user_id +
                ", resource_id=" + resource_id +
                ", userPlay_time=" + userPlay_time +
                ", userPlay_to=" + userPlay_to +
                '}';
    }
}
