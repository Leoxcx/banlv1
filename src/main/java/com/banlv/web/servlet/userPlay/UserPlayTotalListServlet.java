package com.banlv.web.servlet.userPlay;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.banlv.bean.UserPlay;
import com.banlv.service.UserPlayService;
import com.banlv.service.impl.UserPlayServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/userPlaytotallistservlet")
public class UserPlayTotalListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        UserPlayService userPlayService = new UserPlayServiceImpl();
        List<UserPlay> userPlays = userPlayService.findAll();

        Map<String,Object> map = new HashMap<>();
        map.put("userPlays",userPlays);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
