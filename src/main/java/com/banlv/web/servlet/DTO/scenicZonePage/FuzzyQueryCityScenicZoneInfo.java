package com.banlv.web.servlet.DTO.scenicZonePage;

import com.banlv.bean.City;
import com.banlv.bean.ScenicZone;
import com.banlv.independent.myService.myImpl.MyCityServiceImpl;
import com.banlv.independent.myService.myImpl.MyScenicZoneServiceImpl;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.util.bean.transTool.TransTool.cityNameToCityInfo;


@WebServlet("/fuzzyquerysceniczoneinfo")
public class FuzzyQueryCityScenicZoneInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<>();

        String scenicZone_name = request.getParameter("scenicZone_name");
        int city_id = Integer.parseInt(request.getParameter("city_id"));
        List<ScenicZone> scenicZonesList = new ArrayList<>();

        map.put("msg", false);
        map.put("scenicZonesList", null);


        if(StringUtils.isNotEmpty(scenicZone_name) && city_id != 0) {

            ScenicZone scenicZone = new ScenicZone();
            scenicZone.setCity_id(city_id);
            scenicZone.setScenicZone_name(scenicZone_name);
            ScenicZoneServiceImpl scenicZoneService = new ScenicZoneServiceImpl();
            //通过scenicZone_name 和 city_id 查询特定的景区，并添加到景区列表中
            List<ScenicZone> scenicZones = scenicZoneService.searchAll(scenicZone);
            if (!scenicZones.isEmpty()) {
                scenicZonesList.add(scenicZone);
            }

            MyScenicZoneServiceImpl myScenicZoneService = new MyScenicZoneServiceImpl();
//            通过scenicZone_name 模糊查询最多10个景区，并添加到景区列表中，直到景区列表满10个
            List<ScenicZone> scenicZones1 = myScenicZoneService.FuzzySearchByScenicZoneName(scenicZone);
            if(!scenicZones1.isEmpty()) {
                for(int i = 0 ; scenicZonesList.size() < 10 || i < scenicZones1.size() ; i++) {
                    scenicZonesList.add(scenicZones1.get(i));
                }
            }
            //若景区列表未满10个，通过city_id 查询最多10个景区，并添加到景区列表中，直到景区列表满10个
            if (scenicZonesList.size() < 10) {
                scenicZone.setScenicZone_name(null);
                PageBean<ScenicZone> szs = scenicZoneService.searchAllByPage(1,10, scenicZone);
                if(szs.getList().isEmpty()) {
                    //若景区列表未满10个，查询所有景区查出最多10个景区，并添加到景区列表中，直到景区列表满10个
                    scenicZone.setCity_id(0);
                    szs = scenicZoneService.searchAllByPage(1,10, scenicZone);
                }
                for(int i = 0 ; scenicZonesList.size() < 10 || i < szs.getList().size() ; i++) {
                    scenicZonesList.add(szs.getList().get(i));
                }
            }


            map.put("msg", true);
            map.put("scenicZonesList", scenicZonesList);

        }



        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}