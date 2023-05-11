package com.banlv.web.servlet.scenicZone_scenicSpot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.banlv.bean.ScenicZone_scenicSpot;
import com.banlv.service.ScenicZone_scenicSpotService;
import com.banlv.service.impl.ScenicZone_scenicSpotServiceImpl;
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

@WebServlet("/scenicZone_scenicSpotsearchservlet")
public class ScenicZone_scenicSpotSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int rows = Integer.parseInt(request.getParameter("rows"));

        Map<String, String[]> parameterMap = request.getParameterMap();

        ScenicZone_scenicSpot scenicZone_scenicSpot = new ScenicZone_scenicSpot();
        ScenicZone_scenicSpot scenicZone_scenicSpotfromWeb = new MyTool<ScenicZone_scenicSpot>(ScenicZone_scenicSpot.class).getObjectFromWeb(scenicZone_scenicSpot, parameterMap);

        ScenicZone_scenicSpotService scenicZone_scenicSpotService = new ScenicZone_scenicSpotServiceImpl();
        PageBean<ScenicZone_scenicSpot> allByPage = scenicZone_scenicSpotService.searchAllByPage(currentPage,rows,scenicZone_scenicSpotfromWeb);

        Map<String,Object> map = new HashMap<>();
        map.put("page",allByPage);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
