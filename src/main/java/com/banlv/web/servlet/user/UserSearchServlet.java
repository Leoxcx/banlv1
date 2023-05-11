package com.banlv.web.servlet.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.banlv.bean.User;
import com.banlv.service.UserService;
import com.banlv.service.impl.UserServiceImpl;
import com.util.bean.PageBean;
import com.util.utils.MyTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/usersearchservlet")
public class UserSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int rows = Integer.parseInt(request.getParameter("rows"));

        Map<String, String[]> parameterMap = request.getParameterMap();

        User user = new User();
        User userfromWeb = new MyTool<User>(User.class).getObjectFromWeb(user, parameterMap);

        UserService userService = new UserServiceImpl();
        PageBean<User> allByPage = userService.searchAllByPage(currentPage,rows,userfromWeb);

        Map<String,Object> map = new HashMap<>();
        map.put("page",allByPage);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
