package com.banlv.web.servlet.DTO.mainPage;

import com.banlv.bean.ScenicSpot_resource;
import com.banlv.model.PlayNum;
import com.banlv.service.ScenicSpot_resourceService;
import com.banlv.service.impl.ScenicSpot_resourceServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//获取景点播放记录
@WebServlet("/getscenicspotplayrecord")
public class GetScenicSpotPlayRecord extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<>();
        int num = 0;

        long scenicSpotId = Long.parseLong(request.getParameter("scenicSpot_id"));

        if(scenicSpotId != 0) {
            //获取资源对应景点播放记录
            num = PlayNum.getPlayNumModel().getScenicSpotRecord(scenicSpotId);
        }


        if(num == 0){
            map.put("msg", false);
            map.put("num", null);
        } else {
            map.put("msg", true);
            map.put("num", num);
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
