package com.banlv.web.servlet.DTO.mainPage;

import com.banlv.bean.ScenicSpot_resource;
import com.banlv.bean.ScenicZone_scenicSpot;
import com.banlv.model.PlayNum;
import com.banlv.service.ScenicSpot_resourceService;
import com.banlv.service.impl.ScenicSpot_resourceServiceImpl;
import com.banlv.service.impl.ScenicZone_scenicSpotServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
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

//保存播放记录
@WebServlet("/saveplayrecord")
public class SavePlayRecord extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<>();
        String resourceId = request.getParameter("resource_id");

        if(StringUtils.isEmpty(resourceId)) {
            map.put("msg", false);
        }else {
            //存资源播放记录
            PlayNum.getPlayNumModel().resourcesRecord(Long.valueOf(resourceId));


            //通过景点资源中间表，获取scenicSpotId景点id
            ScenicSpot_resource scenicSpot_resource = new ScenicSpot_resource();
            scenicSpot_resource.setResource_id(Long.parseLong(resourceId));
            ScenicSpot_resourceService spotResource = new ScenicSpot_resourceServiceImpl();
            List<ScenicSpot_resource> scenicSpot_resources = spotResource.searchAll(scenicSpot_resource);

            if (scenicSpot_resources.isEmpty()) {
                map.put("msg", false);
            } else {
                if (1 == scenicSpot_resources.get(0).getScenicSpot_resource_use()) {
                    long scenicSpotId = scenicSpot_resources.get(0).getScenicSpot_id();
                    //存资源对应景点播放记录
                    PlayNum.getPlayNumModel().scenicSpotRecord(scenicSpotId);

                    //通过景区景点中间表，scenicZoneId景区id
                    ScenicZone_scenicSpot scenicZone_scenicSpot = new ScenicZone_scenicSpot();
                    scenicZone_scenicSpot.setScenicSpot_id(scenicSpotId);
                    ScenicZone_scenicSpotServiceImpl scenicZoneScenicSpot = new ScenicZone_scenicSpotServiceImpl();
                    List<ScenicZone_scenicSpot> scenicZone_scenicSpots = scenicZoneScenicSpot.searchAll(scenicZone_scenicSpot);

                    if (scenicZone_scenicSpots.isEmpty()) {
                        map.put("msg", false);
                    } else {
                        if (1 == scenicZone_scenicSpots.get(0).getScenicZone_scenicSpot_use()) {
                            long scenicZoneId = scenicZone_scenicSpots.get(0).getScenicZone_id();
                            //存资源对应景区播放记录
                            PlayNum.getPlayNumModel().scenicZoneRecord(scenicZoneId);
                            map.put("msg", true);
                        }
                    }
                }
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}