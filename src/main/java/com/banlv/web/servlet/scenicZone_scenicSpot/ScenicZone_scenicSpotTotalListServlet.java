package com.banlv.web.servlet.scenicZone_scenicSpot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.banlv.bean.ScenicZone_scenicSpot;
import com.banlv.service.ScenicZone_scenicSpotService;
import com.banlv.service.impl.ScenicZone_scenicSpotServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/scenicZone_scenicSpottotallistservlet")
public class ScenicZone_scenicSpotTotalListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        ScenicZone_scenicSpotService scenicZone_scenicSpotService = new ScenicZone_scenicSpotServiceImpl();
        List<ScenicZone_scenicSpot> scenicZone_scenicSpots = scenicZone_scenicSpotService.findAll();

        Map<String,Object> map = new HashMap<>();
        map.put("scenicZone_scenicSpots",scenicZone_scenicSpots);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
