package com.banlv.web.servlet.userArrive;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.banlv.bean.UserArrive;
import com.banlv.service.UserArriveService;
import com.banlv.service.impl.UserArriveServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/userArrivetotallistservlet")
public class UserArriveTotalListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        UserArriveService userArriveService = new UserArriveServiceImpl();
        List<UserArrive> userArrives = userArriveService.findAll();

        Map<String,Object> map = new HashMap<>();
        map.put("userArrives",userArrives);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
