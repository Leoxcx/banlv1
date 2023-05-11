package com.banlv.web.servlet.DTO.mainPage;

import com.alibaba.fastjson.JSONObject;
import com.banlv.model.CoordinateRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.bean.dto.CoordinateInfo;
import com.util.bean.dto.CoordinateInfoDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//保存经度、纬度、备注、代理商id
@WebServlet("/savecoordinaterecord")
public class SaveCoordinateRecord extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<>();

        String agent_id = request.getParameter("agent_id");
        String coordinateInfoList = request.getParameter("coordinateInfoList");
        System.out.println(coordinateInfoList);
        List<CoordinateInfo> CoordinateInfos = JSONObject.parseArray(coordinateInfoList,CoordinateInfo.class);

        //获取经度
        double scenicSpot_longitude = Double.parseDouble(request.getParameter("scenicSpot_longitude"));
        //获取纬度
        double scenicSpot_latitude = Double.parseDouble(request.getParameter("scenicSpot_latitude"));
        //获取备注
        String remark = request.getParameter("remark");
        //获取代理商id
        long agentId = Long.valueOf(request.getParameter("agent_id"));

        if(scenicSpot_longitude != 0 && scenicSpot_latitude != 0 && agentId != 0) {
            CoordinateInfoDto coordinateInfoDto = new CoordinateInfoDto();
            coordinateInfoDto.setScenicSpot_longitude(scenicSpot_longitude);
            coordinateInfoDto.setScenicSpot_latitude(scenicSpot_latitude);
            coordinateInfoDto.setRemark(remark);
            CoordinateRecord.getCoordinateRecordModel().setRecord(agentId, coordinateInfoDto);
            map.put("msg", true);
        } else {
            map.put("msg", false);
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
