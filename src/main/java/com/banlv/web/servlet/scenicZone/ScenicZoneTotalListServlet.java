package com.banlv.web.servlet.scenicZone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.banlv.bean.ScenicZone;
import com.banlv.service.ScenicZoneService;
import com.banlv.service.impl.ScenicZoneServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/scenicZonetotallistservlet")
public class ScenicZoneTotalListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        ScenicZoneService scenicZoneService = new ScenicZoneServiceImpl();
        List<ScenicZone> scenicZones = scenicZoneService.findAll();

        Map<String,Object> map = new HashMap<>();
        map.put("scenicZones",scenicZones);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
