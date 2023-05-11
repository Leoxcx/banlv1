package com.banlv.web.servlet.scenicSpot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.banlv.bean.ScenicSpot;
import com.banlv.service.ScenicSpotService;
import com.banlv.service.impl.ScenicSpotServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/scenicSpottotallistservlet")
public class ScenicSpotTotalListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        ScenicSpotService scenicSpotService = new ScenicSpotServiceImpl();
        List<ScenicSpot> scenicSpots = scenicSpotService.findAll();

        Map<String,Object> map = new HashMap<>();
        map.put("scenicSpots",scenicSpots);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
