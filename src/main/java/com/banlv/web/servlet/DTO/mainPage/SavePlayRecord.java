package com.banlv.web.servlet.DTO.mainPage;

import com.banlv.bean.ScenicSpot;
import com.banlv.bean.ScenicSpot_resource;
import com.banlv.bean.ScenicZone;
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

import static com.util.bean.transTool.TransTool.resourceToScenicSpot;
import static com.util.bean.transTool.TransTool.scenicSpotToScenicZone;

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

            List<ScenicSpot> scenicSpots = resourceToScenicSpot(Long.parseLong(resourceId));
            if (scenicSpots.isEmpty()) {
                map.put("msg", false);
            } else {

                long scenicSpotId = scenicSpots.get(0).getScenicSpot_id();
                //存资源对应景点播放记录
                PlayNum.getPlayNumModel().scenicSpotRecord(scenicSpotId);

                //通过景区景点中间表，scenicZoneId景区id

                List<ScenicZone> scenicZones = scenicSpotToScenicZone(scenicSpotId);
                if (scenicZones.isEmpty()) {
                    map.put("msg", false);
                } else {
                    long scenicZoneId = scenicZones.get(0).getScenicZone_id();
                    //存资源对应景区播放记录
                    PlayNum.getPlayNumModel().scenicZoneRecord(scenicZoneId);
                    map.put("msg", true);
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