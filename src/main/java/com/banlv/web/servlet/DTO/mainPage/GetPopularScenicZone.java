package com.banlv.web.servlet.DTO.mainPage;

import com.banlv.bean.City;
import com.banlv.bean.ScenicZone;
import com.banlv.service.impl.ScenicZoneServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.bean.PageBean;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//获取热门景区信息
@WebServlet("/getpopularsceniczone")
public class GetPopularScenicZone extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<>();

        map.put("msg", false);
        map.put("cities", null);

        int city_id = Integer.parseInt(request.getParameter("city_id"));
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));

        if(city_id != 0) {
            ScenicZone scenicZone = new ScenicZone();
            scenicZone.setCity_id(city_id);

            ScenicZoneServiceImpl scenicZoneService = new ScenicZoneServiceImpl();
            PageBean<ScenicZone> scenicZonePageBean = scenicZoneService.searchAllByPage(currentPage, 10, scenicZone);

            List<ScenicZone> ScenicZoneList = scenicZonePageBean.getList();
            if(!ScenicZoneList.isEmpty()) {
                map.put("msg", true);
                map.put("ScenicZoneList", ScenicZoneList);
            }
        }


        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}