package com.banlv.web.servlet.scenicSpot_resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.banlv.bean.ScenicSpot_resource;
import com.banlv.service.ScenicSpot_resourceService;
import com.banlv.service.impl.ScenicSpot_resourceServiceImpl;
import com.util.utils.MyTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/scenicSpot_resourcesubmitservlet")
public class ScenicSpot_resourceSubmitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        Map<String, String[]> parameterMap = request.getParameterMap();

        ScenicSpot_resource scenicSpot_resource = new ScenicSpot_resource();
        ScenicSpot_resource scenicSpot_resourcefromWeb = new MyTool<ScenicSpot_resource>(ScenicSpot_resource.class).getObjectFromWeb(scenicSpot_resource, parameterMap);

        ScenicSpot_resourceService scenicSpot_resourceService = new ScenicSpot_resourceServiceImpl();
        int i = scenicSpot_resourceService.addScenicSpot_resource(scenicSpot_resourcefromWeb);

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
