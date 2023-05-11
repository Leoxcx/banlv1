package com.banlv.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserArrive implements Serializable {
    private long userArrive_id;
    private long user_id;
    private long scenicSpot_id;
    private Timestamp userArrive_time;

    public UserArrive() {
    }

    public UserArrive(long userArrive_id, long user_id, long scenicSpot_id, Timestamp userArrive_time) {
        this.userArrive_id = userArrive_id;
        this.user_id = user_id;
        this.scenicSpot_id = scenicSpot_id;
        this.userArrive_time = userArrive_time;
    }

    public long getUserArrive_id() {
        return userArrive_id;
    }

    public void setUserArrive_id(long userArrive_id) {
        this.userArrive_id = userArrive_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getScenicSpot_id() {
        return scenicSpot_id;
    }

    public void setScenicSpot_id(long scenicSpot_id) {
        this.scenicSpot_id = scenicSpot_id;
    }

    public Timestamp getUserArrive_time() {
        return userArrive_time;
    }

    public void setUserArrive_time(Timestamp userArrive_time) {
        this.userArrive_time = userArrive_time;
    }

    @Override
    public String toString() {
        return "UserArrive{" +
                "userArrive_id=" + userArrive_id +
                ", user_id=" + user_id +
                ", scenicSpot_id=" + scenicSpot_id +
                ", userArrive_time=" + userArrive_time +
                '}';
    }
}
