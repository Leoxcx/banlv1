package com.banlv.web.servlet.scenicZoneEntry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.utils.MyTool;
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

@WebServlet("/scenicZoneEntrytotalsearchservlet")
public class ScenicZoneEntryTotalSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        Map<String, String[]> parameterMap = request.getParameterMap();

        ScenicZoneEntry scenicZoneEntry = new ScenicZoneEntry();
        ScenicZoneEntry scenicZoneEntryfromWeb = new MyTool<ScenicZoneEntry>(ScenicZoneEntry.class).getObjectFromWeb(scenicZoneEntry, parameterMap);

        ScenicZoneEntryService scenicZoneEntryService = new ScenicZoneEntryServiceImpl();
        List<ScenicZoneEntry> scenicZoneEntrys = scenicZoneEntryService.searchAll(scenicZoneEntryfromWeb);

        Map<String,Object> map = new HashMap<>();
        map.put("scenicZoneEntrys",scenicZoneEntrys);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
