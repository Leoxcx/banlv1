package com.banlv.web.servlet.scenicZoneEntry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.banlv.bean.ScenicZoneEntry;
import com.banlv.service.ScenicZoneEntryService;
import com.banlv.service.impl.ScenicZoneEntryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/scenicZoneEntrytotallistservlet")
public class ScenicZoneEntryTotalListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        ScenicZoneEntryService scenicZoneEntryService = new ScenicZoneEntryServiceImpl();
        List<ScenicZoneEntry> scenicZoneEntrys = scenicZoneEntryService.findAll();

        Map<String,Object> map = new HashMap<>();
        map.put("scenicZoneEntrys",scenicZoneEntrys);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
