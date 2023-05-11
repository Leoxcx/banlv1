package com.banlv.web.servlet.scenicSpot_resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.banlv.bean.ScenicSpot_resource;
import com.banlv.service.ScenicSpot_resourceService;
import com.banlv.service.impl.ScenicSpot_resourceServiceImpl;
import com.util.bean.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/scenicSpot_resourcelistservlet")
public class ScenicSpot_resourceListServlet  extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int rows = Integer.parseInt(request.getParameter("rows"));

        ScenicSpot_resourceService scenicSpot_resourceService = new ScenicSpot_resourceServiceImpl();
        PageBean<ScenicSpot_resource> allByPage = scenicSpot_resourceService.findAllByPage(currentPage, rows);

        Map<String,Object> map = new HashMap<>();
        map.put("page",allByPage);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
