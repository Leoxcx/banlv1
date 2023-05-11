package com.banlv.web.servlet.DTO.mainPage;

import com.banlv.bean.User;
import com.banlv.bean.User_resource;
import com.banlv.service.UserService;
import com.banlv.service.User_resourceService;
import com.banlv.service.impl.UserServiceImpl;
import com.banlv.service.impl.User_resourceServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//通过resource_id和用户的id返回用户是否有权限播放
@WebServlet("/isabletoplaybyid")
public class IsAbleToPlayById extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<>();

        //获取resourceId 和 openId
        String resourceId = request.getParameter("resource_id");
        String userId = request.getParameter("user_id");

        if(StringUtils.isEmpty(resourceId) && StringUtils.isEmpty(userId)) {
            map.put("msg", false);
        }else {
            long resource_id = Long.parseLong(resourceId);
            long user_id = Long.parseLong(userId);

            //查询user_resource表
            User_resource user_resource = new User_resource();
            user_resource.setUser_id(user_id);
            user_resource.setResource_id(resource_id);
            User_resourceService user_resourceService = new User_resourceServiceImpl();
            List<User_resource> user_resources = user_resourceService.searchAll(user_resource);

            if (user_resources.isEmpty()) {
                map.put("msg", false);
            } else {
                map.put("msg",true);
            }
        }


        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}