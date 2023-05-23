package com.banlv.web.servlet.DTO.scenicZonePage;

import com.banlv.bean.ScenicZone;
import com.banlv.independent.myService.myImpl.MyCityServiceImpl;
import com.banlv.independent.myService.myImpl.MyScenicZoneServiceImpl;
import com.banlv.service.impl.CityServiceImpl;
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

//通过当前城市city_id和景区分类scenicZoneType_id（自然风景区、展馆、博物馆、公园）获取当前城市景区列表
@WebServlet("/getsceniczonebysceniczonetypeid")
public class GetScenicZoneByScenicZoneTypeId extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<>();
        int city_id = Integer.parseInt(request.getParameter("city_id"));
        String scenicZoneTypeId =  request.getParameter("scenicZoneType_id");
        int scenicZoneType_id = 0;
        if(scenicZoneTypeId != null){
            scenicZoneType_id = Integer.parseInt(scenicZoneTypeId);
        }
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));

        map.put("msg", false);
        map.put("scenicZoneList", null);

        List<ScenicZone> scenicZoneList = new ArrayList<>();

        if(city_id != 0 && currentPage != 0) {
            ScenicZone scenicZone = new ScenicZone();
            scenicZone.setCity_id(city_id);
            scenicZone.setScenicZoneType_id(scenicZoneType_id);

            ScenicZoneServiceImpl scenicZoneService = new ScenicZoneServiceImpl();
            Integer szNum = new MyScenicZoneServiceImpl().SearchTotalCountByCityId(city_id);
            if (szNum >= currentPage * 10) {
                PageBean<ScenicZone> scenicZonePageBean = scenicZoneService.searchAllByPage(currentPage, 10, scenicZone);
                scenicZoneList.addAll(scenicZonePageBean.getList());

            }else {
                scenicZone.setCity_id(0);
                PageBean<ScenicZone> scenicZonePageBean = scenicZoneService.searchAllByPage(currentPage - szNum % 10, 10, scenicZone);
                scenicZoneList.addAll(scenicZonePageBean.getList());
            }

//            PageBean<ScenicZone> scenicZonePageBean = scenicZoneService.searchAllByPage(currentPage, 10, scenicZone);
//            if(!scenicZonePageBean.getList().isEmpty()) {
//                scenicZoneList.addAll(scenicZonePageBean.getList());
//            }
            // 当前城市景区数量不够，查找下一个城市的景区
            //查询所有城市数
//            int totalCount = new MyCityServiceImpl().findTotalCount();
//            while (scenicZoneList.size() < 10 ) {
//
//                if(city_id < totalCount ) {
////                    不为最后一个城市，取下一个城市
//                    city_id++;
//                } else {
////                    若为最后一个城市，则从第一个城市开始,1第一个北京市
//                    city_id = 1;
//                }
//                scenicZone.setCity_id(city_id);
//                scenicZonePageBean = scenicZoneService.searchAllByPage(currentPage, 10, scenicZone);
//                if(!scenicZonePageBean.getList().isEmpty()) {
//                    scenicZoneList.addAll(scenicZonePageBean.getList());
//                }
//            }
            map.put("msg", true);
            map.put("scenicZoneList", scenicZoneList);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}