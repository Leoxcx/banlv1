package com.banlv.web.servlet.scenicZone_scenicZoneEntry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.banlv.bean.ScenicZone_scenicZoneEntry;
import com.banlv.service.ScenicZone_scenicZoneEntryService;
import com.banlv.service.impl.ScenicZone_scenicZoneEntryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/scenicZone_scenicZoneEntrytotallistservlet")
public class ScenicZone_scenicZoneEntryTotalListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        ScenicZone_scenicZoneEntryService scenicZone_scenicZoneEntryService = new ScenicZone_scenicZoneEntryServiceImpl();
        List<ScenicZone_scenicZoneEntry> scenicZone_scenicZoneEntrys = scenicZone_scenicZoneEntryService.findAll();

        Map<String,Object> map = new HashMap<>();
        map.put("scenicZone_scenicZoneEntrys",scenicZone_scenicZoneEntrys);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
