package com.banlv.web.servlet.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.banlv.bean.User;
import com.banlv.service.UserService;
import com.banlv.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/usertotallistservlet")
public class UserTotalListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        UserService userService = new UserServiceImpl();
        List<User> users = userService.findAll();

        Map<String,Object> map = new HashMap<>();
        map.put("users",users);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
