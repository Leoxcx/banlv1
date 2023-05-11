package com.util.utils;

import com.banlv.bean.Agent;
import com.banlv.bean.Resource;
import com.banlv.bean.User;
import com.banlv.service.AgentService;
import com.banlv.service.ResourceService;
import com.banlv.service.UserService;
import com.banlv.service.impl.AgentServiceImpl;
import com.banlv.service.impl.ResourceServiceImpl;
import com.banlv.service.impl.UserServiceImpl;

import java.util.Random;

public class AddInfo {
    public static void main(String[] args) {
        //addUser();

//        addAgent();

        addResource();
    }

    public static void addUser(){
        UserService userService = new UserServiceImpl();
        User user = new User();
        for (int i = 0; i < 10; i++) {
            user.setUser_openid(String.valueOf(i));
            user.setUser_name("用户"+ String.valueOf(i));
            user.setUser_points(0);
            user.setUser_vip(0);
            userService.addUser(user);
        }
    }

    public static void addAgent(){
        AgentService agentService = new AgentServiceImpl();
        Agent agent = new Agent();
        for (int i = 0; i < 10; i++) {
            agent.setAgent_name("代理商" + String.valueOf(i));
            agent.setAgent_password("123456");
            agent.setAgent_phone("1234567890"+String.valueOf(i));
            agentService.addAgent(agent);
        }
    }

    public static void addResource(){
        ResourceService resourceService = new ResourceServiceImpl();
        Resource resource = new Resource();
        Random rad = new Random();
        for (int i = 0; i < 200; i++) {
            resource.setAgent_id(rad.nextInt(10));
            resource.setResource_name("资源" + String.valueOf(i));
            resource.setResource_points(rad.nextInt(10));
            resource.setResource_url("1");
            resourceService.addResource(resource);
        }
    }

}
