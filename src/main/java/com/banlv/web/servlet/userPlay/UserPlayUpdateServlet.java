package com.banlv.web.servlet.userPlay;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.banlv.bean.UserPlay;
import com.banlv.service.UserPlayService;
import com.banlv.service.impl.UserPlayServiceImpl;
import com.util.utils.MyTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/userPlayupdateservlet")
public class UserPlayUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        Map<String, String[]> parameterMap = request.getParameterMap();

        UserPlay userPlay= new UserPlay();
        UserPlay userPlayfromWeb = new MyTool<UserPlay>(UserPlay.class).getObjectFromWeb(userPlay, parameterMap);

        UserPlayService userPlayService = new UserPlayServiceImpl();
        int i = userPlayService.updateUserPlay(userPlayfromWeb);

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
