package com.banlv.web.servlet.scenicSpot_resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.banlv.bean.ScenicSpot_resource;
import com.banlv.service.ScenicSpot_resourceService;
import com.banlv.service.impl.ScenicSpot_resourceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/scenicSpot_resourcetotallistservlet")
public class ScenicSpot_resourceTotalListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        ScenicSpot_resourceService scenicSpot_resourceService = new ScenicSpot_resourceServiceImpl();
        List<ScenicSpot_resource> scenicSpot_resources = scenicSpot_resourceService.findAll();

        Map<String,Object> map = new HashMap<>();
        map.put("scenicSpot_resources",scenicSpot_resources);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
