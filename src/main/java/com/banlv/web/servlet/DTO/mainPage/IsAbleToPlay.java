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

import static com.util.bean.transTool.TransTool.openIdToUserInfo;

//通过resource_id和用户的openid返回用户是否有权限播放
@WebServlet("/isabletoplay")
public class IsAbleToPlay extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<>();

        //获取resourceId 和 openId
        String resourceId = request.getParameter("resource_id");
        String openId = request.getParameter("user_openid");
        long userId;

        if(StringUtils.isEmpty(resourceId) && StringUtils.isEmpty(openId)) {
            map.put("msg", false);
        }else {
            //查询user_id
            List<User> users = openIdToUserInfo(openId);

            if(users.isEmpty()) {
                map.put("msg", false);
            }else {
                //获取userId
                userId = users.get(0).getUser_id();

                //查询user_resource表
                User_resource user_resource = new User_resource();
                user_resource.setUser_id(userId);
                user_resource.setResource_id(Long.parseLong(resourceId));
                User_resourceService user_resourceService = new User_resourceServiceImpl();
                List<User_resource> user_resources = user_resourceService.searchAll(user_resource);

                if (user_resources.isEmpty()) {
                    map.put("msg", false);
                } else {
                    map.put("msg",true);
                }
            }
        }


        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}