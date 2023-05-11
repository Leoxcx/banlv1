package com.banlv.web.servlet.scenicZone_scenicZoneEntry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.banlv.bean.ScenicZone_scenicZoneEntry;
import com.banlv.service.ScenicZone_scenicZoneEntryService;
import com.banlv.service.impl.ScenicZone_scenicZoneEntryServiceImpl;
import com.util.bean.PageBean;
import com.util.utils.MyTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/scenicZone_scenicZoneEntrysearchservlet")
public class ScenicZone_scenicZoneEntrySearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int rows = Integer.parseInt(request.getParameter("rows"));

        Map<String, String[]> parameterMap = request.getParameterMap();

        ScenicZone_scenicZoneEntry scenicZone_scenicZoneEntry = new ScenicZone_scenicZoneEntry();
        ScenicZone_scenicZoneEntry scenicZone_scenicZoneEntryfromWeb = new MyTool<ScenicZone_scenicZoneEntry>(ScenicZone_scenicZoneEntry.class).getObjectFromWeb(scenicZone_scenicZoneEntry, parameterMap);

        ScenicZone_scenicZoneEntryService scenicZone_scenicZoneEntryService = new ScenicZone_scenicZoneEntryServiceImpl();
        PageBean<ScenicZone_scenicZoneEntry> allByPage = scenicZone_scenicZoneEntryService.searchAllByPage(currentPage,rows,scenicZone_scenicZoneEntryfromWeb);

        Map<String,Object> map = new HashMap<>();
        map.put("page",allByPage);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
