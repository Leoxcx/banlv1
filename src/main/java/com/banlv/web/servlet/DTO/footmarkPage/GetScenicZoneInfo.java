package com.banlv.web.servlet.DTO.footmarkPage;

import com.banlv.bean.ScenicSpot;
import com.banlv.bean.ScenicZone;
import com.banlv.bean.ScenicZone_scenicSpot;
import com.banlv.service.impl.ScenicSpotServiceImpl;
import com.banlv.service.impl.ScenicZoneServiceImpl;
import com.banlv.service.impl.ScenicZone_scenicSpotServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.bean.dto.ScenicZoneInfoDto;

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

//查看所有景区信息,以及景区对应的所有景点信息
@WebServlet("/getsceniczoneinfo")
public class GetScenicZoneInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<>();

        //查看所有景区信息（建一个DTO类）DTO类里面加一个scenicSpot的List存储对应景区的所有景点信息
        List<ScenicZone> scenicZones = new ScenicZoneServiceImpl().findAll();
        if(scenicZones.isEmpty()) {
            map.put("msg", false);
            map.put("scenicZoneInfoDto", null);
        } else {

            ScenicZone_scenicSpotServiceImpl scenicZone_scenicSpotService = new ScenicZone_scenicSpotServiceImpl();

            ScenicZone_scenicSpot scenicZone_scenicSpot = new ScenicZone_scenicSpot();

            ScenicSpot scenicSpot = new ScenicSpot();

            ScenicSpotServiceImpl scenicSpotService = new ScenicSpotServiceImpl();
            List<ScenicZoneInfoDto> scenicZoneInfoDtos = new ArrayList<>();
            for (ScenicZone sz: scenicZones) {

                scenicZone_scenicSpot.setScenicZone_id(sz.getScenicZone_id());

                List<ScenicZone_scenicSpot> scenicZone_scenicSpots = scenicZone_scenicSpotService.searchAll(scenicZone_scenicSpot);


                for (ScenicZone_scenicSpot szsp: scenicZone_scenicSpots) {

                    scenicSpot.setScenicSpot_id(szsp.getScenicSpot_id());

                    List<ScenicSpot> scenicSpots = scenicSpotService.searchAll(scenicSpot);

                    ScenicZoneInfoDto scenicZoneInfoDto = new ScenicZoneInfoDto();
                    scenicZoneInfoDto.setScenicZone_id(sz.getScenicZone_id());
                    scenicZoneInfoDto.setScenicManage_id(sz.getScenicManage_id());
                    scenicZoneInfoDto.setScenicZone_name(sz.getScenicZone_name());
                    scenicZoneInfoDto.setScenicSpots(scenicSpots);

                    scenicZoneInfoDtos.add(scenicZoneInfoDto);
                }
            }
            map.put("msg", true);
            map.put("scenicZoneInfoDtos", scenicZoneInfoDtos);
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}