package com.banlv.web.servlet.user_resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.banlv.service.User_resourceService;
import com.banlv.service.impl.User_resourceServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user_resourcedeleteservlet")
public class User_resourceDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        Map<String, String[]> parameterMap = request.getParameterMap();

        int id = Integer.parseInt(request.getParameter("id"));

        User_resourceService user_resourceService = new User_resourceServiceImpl();
        int i = user_resourceService.deleteByUser_resourceId(id);

        Map<String,Object> map = new HashMap<>();
        if (i == 1) map.put("message",true);
        else map.put("message",false);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
