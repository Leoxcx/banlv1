package com.banlv.web.servlet.scenicZone_scenicZoneEntry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.banlv.bean.ScenicZone_scenicZoneEntry;
import com.banlv.service.ScenicZone_scenicZoneEntryService;
import com.banlv.service.impl.ScenicZone_scenicZoneEntryServiceImpl;
import com.util.utils.MyTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/scenicZone_scenicZoneEntryupdateservlet")
public class ScenicZone_scenicZoneEntryUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        Map<String, String[]> parameterMap = request.getParameterMap();

        ScenicZone_scenicZoneEntry scenicZone_scenicZoneEntry= new ScenicZone_scenicZoneEntry();
        ScenicZone_scenicZoneEntry scenicZone_scenicZoneEntryfromWeb = new MyTool<ScenicZone_scenicZoneEntry>(ScenicZone_scenicZoneEntry.class).getObjectFromWeb(scenicZone_scenicZoneEntry, parameterMap);

        ScenicZone_scenicZoneEntryService scenicZone_scenicZoneEntryService = new ScenicZone_scenicZoneEntryServiceImpl();
        int i = scenicZone_scenicZoneEntryService.updateScenicZone_scenicZoneEntry(scenicZone_scenicZoneEntryfromWeb);

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
