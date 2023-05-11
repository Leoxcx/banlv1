package com.banlv.web.servlet.scenicZone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.utils.MyTool;
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

@WebServlet("/scenicZonetotalsearchservlet")
public class ScenicZoneTotalSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        Map<String, String[]> parameterMap = request.getParameterMap();

        ScenicZone scenicZone = new ScenicZone();
        ScenicZone scenicZonefromWeb = new MyTool<ScenicZone>(ScenicZone.class).getObjectFromWeb(scenicZone, parameterMap);

        ScenicZoneService scenicZoneService = new ScenicZoneServiceImpl();
        List<ScenicZone> scenicZones = scenicZoneService.searchAll(scenicZonefromWeb);

        Map<String,Object> map = new HashMap<>();
        map.put("scenicZones",scenicZones);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
