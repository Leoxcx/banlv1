package com.banlv.bean;

import java.io.Serializable;

public class Resource implements Serializable {
    private long resource_id;
    private long agent_id;
    private String resource_name;
    private String resource_url;
    private String resource_thumbnail;
    private int resource_points;
    private int resource_type;
    private int resource_num;

    public Resource() {
    }

    public Resource(long resource_id, long agent_id, String resource_name, String resource_url, String resource_thumbnail, int resource_points, int resource_type, int resource_num) {
        this.resource_id = resource_id;
        this.agent_id = agent_id;
        this.resource_name = resource_name;
        this.resource_url = resource_url;
        this.resource_thumbnail = resource_thumbnail;
        this.resource_points = resource_points;
        this.resource_type = resource_type;
        this.resource_num = resource_num;
    }

    public long getResource_id() {
        return resource_id;
    }

    public void setResource_id(long resource_id) {
        this.resource_id = resource_id;
    }

    public long getAgent_id() {
        return agent_id;
    }

    public void setAgent_id(long agent_id) {
        this.agent_id = agent_id;
    }

    public String getResource_name() {
        return resource_name;
    }

    public void setResource_name(String resource_name) {
        this.resource_name = resource_name;
    }

    public String getResource_url() {
        return resource_url;
    }

    public void setResource_url(String resource_url) {
        this.resource_url = resource_url;
    }

    public String getResource_thumbnail() {
        return resource_thumbnail;
    }

    public void setResource_thumbnail(String resource_thumbnail) {
        this.resource_thumbnail = resource_thumbnail;
    }

    public int getResource_points() {
        return resource_points;
    }

    public void setResource_points(int resource_points) {
        this.resource_points = resource_points;
    }

    public int getResource_type() {
        return resource_type;
    }

    public void setResource_type(int resource_type) {
        this.resource_type = resource_type;
    }

    public int getResource_num() {
        return resource_num;
    }

    public void setResource_num(int resource_num) {
        this.resource_num = resource_num;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "resource_id=" + resource_id +
                ", agent_id=" + agent_id +
                ", resource_name='" + resource_name + '\'' +
                ", resource_url='" + resource_url + '\'' +
                ", resource_thumbnail='" + resource_thumbnail + '\'' +
                ", resource_points=" + resource_points +
                ", resource_type=" + resource_type +
                ", resource_num=" + resource_num +
                '}';
    }
}
