package com.banlv.bean;

import java.io.Serializable;

public class User_resource implements Serializable {
    private long user_resource_id;
    private long user_id;
    private long resource_id;

    public User_resource() {
    }

    public User_resource(long user_resource_id, long user_id, long resource_id) {
        this.user_resource_id = user_resource_id;
        this.user_id = user_id;
        this.resource_id = resource_id;
    }

    public long getUser_resource_id() {
        return user_resource_id;
    }

    public void setUser_resource_id(long user_resource_id) {
        this.user_resource_id = user_resource_id;
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

    @Override
    public String toString() {
        return "User_resource{" +
                "user_resource_id=" + user_resource_id +
                ", user_id=" + user_id +
                ", resource_id=" + resource_id +
                '}';
    }
}
