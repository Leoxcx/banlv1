package com.banlv.web.servlet.DTO.footmarkPage;

import com.banlv.bean.Resource;
import com.banlv.bean.ScenicSpot;
import com.banlv.bean.ScenicZone;
import com.banlv.service.impl.ScenicSpotServiceImpl;
import com.banlv.service.impl.ScenicZoneServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.bean.dto.ScenicSpotInfoDto;

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

import static com.util.bean.transTool.TransTool.spotToResource;

//查看所有景点信息，以及景点内的资源
@WebServlet("/getscenicspotinfo")
public class GetScenicSpotInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<>();
        List<ScenicSpotInfoDto> scenicSpotInfoDtos = new ArrayList<>();

        //查看所有景点信息（DTO类）里面需要一个存储该景点标记的资源（resource）的list属性
        List<ScenicSpot> scenicSpots = new ScenicSpotServiceImpl().findAll();
        if(scenicSpots.isEmpty()) {
            map.put("msg", false);
            map.put("scenicZoneInfoDto", null);
        } else {
            for (ScenicSpot sp: scenicSpots) {
                List<Resource> resources = spotToResource(sp.getScenicSpot_id());
//                        ScenicSpotRecommendServlet.spotToResource(sp.getScenicSpot_id());
                if(resources.isEmpty()) {
                    map.put("msg", false);
                    map.put("scenicZoneInfoDto", null);
                }
                ScenicSpotInfoDto scenicSpotInfoDto = new ScenicSpotInfoDto();
                scenicSpotInfoDto.setScenicSpot_id(sp.getScenicSpot_id());
                scenicSpotInfoDto.setScenicSpot_name(sp.getScenicSpot_name());
                scenicSpotInfoDto.setScenicSpot_latitude(sp.getScenicSpot_latitude());
                scenicSpotInfoDto.setScenicSpot_longitude(sp.getScenicSpot_longitude());
                scenicSpotInfoDto.setScenicSpot_range(sp.getScenicSpot_range());
                scenicSpotInfoDto.setResources(resources);

                scenicSpotInfoDtos.add(scenicSpotInfoDto);
            }
            map.put("msg", true);
            map.put("scenicZoneInfoDto", scenicSpotInfoDtos);
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}