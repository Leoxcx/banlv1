package com.banlv.bean;

import java.io.Serializable;

public class Agent implements Serializable {
    private long agent_id;
    private String agent_name;
    private String agent_password;
    private String agent_phone;

    public Agent() {
    }

    public Agent(long agent_id, String agent_name, String agent_password, String agent_phone) {
        this.agent_id = agent_id;
        this.agent_name = agent_name;
        this.agent_password = agent_password;
        this.agent_phone = agent_phone;
    }

    public long getAgent_id() {
        return agent_id;
    }

    public void setAgent_id(long agent_id) {
        this.agent_id = agent_id;
    }

    public String getAgent_name() {
        return agent_name;
    }

    public void setAgent_name(String agent_name) {
        this.agent_name = agent_name;
    }

    public String getAgent_password() {
        return agent_password;
    }

    public void setAgent_password(String agent_password) {
        this.agent_password = agent_password;
    }

    public String getAgent_phone() {
        return agent_phone;
    }

    public void setAgent_phone(String agent_phone) {
        this.agent_phone = agent_phone;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "agent_id=" + agent_id +
                ", agent_name='" + agent_name + '\'' +
                ", agent_password='" + agent_password + '\'' +
                ", agent_phone='" + agent_phone + '\'' +
                '}';
    }
}
