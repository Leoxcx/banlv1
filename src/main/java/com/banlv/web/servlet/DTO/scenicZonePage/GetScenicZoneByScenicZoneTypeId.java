package com.banlv.web.servlet.DTO.scenicZonePage;

import com.banlv.bean.ScenicZone;
import com.banlv.service.impl.ScenicZoneServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.bean.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/getsceniczonebysceniczonetypeid")
public class GetScenicZoneByScenicZoneTypeId extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<>();
        int city_id = Integer.parseInt(request.getParameter("city_id"));
        int scenicZoneType_id = Integer.parseInt(request.getParameter("scenicZoneType_id"));
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));

        List<ScenicZone> scenicZoneList = new ArrayList<>();

        if(city_id != 0 && scenicZoneType_id != 0 && currentPage != 0) {
            ScenicZone scenicZone = new ScenicZone();
            scenicZone.setCity_id(city_id);
            scenicZone.setScenicZoneType_id(scenicZoneType_id);

            ScenicZoneServiceImpl scenicZoneService = new ScenicZoneServiceImpl();
            PageBean<ScenicZone> scenicZonePageBean = scenicZoneService.searchAllByPage(currentPage, 10, scenicZone);
            if(!scenicZonePageBean.getList().isEmpty()) {
                scenicZoneList.addAll(scenicZonePageBean.getList());
            }
            //ToDo 当前城市景区数量不够，查找下一个城市的景区
            if(scenicZoneList.size() < 10 ) {

            }
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}