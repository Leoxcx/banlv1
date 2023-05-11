package com.banlv.web.servlet.DTO.agentPage;

import com.alibaba.fastjson.JSONObject;
import com.banlv.model.CoordinateRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.bean.dto.CoordinateInfo;
import com.util.bean.dto.CoordinateInfoDto;
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

//保存经度、纬度、备注、代理商id
@WebServlet("/savecoordinaterecord")
public class SaveCoordinateRecord extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<>();

        String coordinateInfoList = request.getParameter("coordinateInfoList");
        String agent_id = request.getParameter("agent_id");
        System.out.println(coordinateInfoList);
        if(StringUtils.isEmpty(coordinateInfoList) && StringUtils.isEmpty(agent_id)) {
            map.put("msg", false);
        } else {
            List<CoordinateInfo> coordinateInfos = JSONObject.parseArray(coordinateInfoList,CoordinateInfo.class);

            if(coordinateInfos.isEmpty()) {
                map.put("msg", false);
            } else {
                //获取代理商id
                long agentId = Long.valueOf(agent_id);
                for (CoordinateInfo ci: coordinateInfos) {
                    //获取经度
                    double scenicSpot_longitude = ci.getLng();
                    //获取纬度
                    double scenicSpot_latitude = ci.getLat();
                    //获取备注
                    String remark = ci.getRemark();

                    if (scenicSpot_longitude != 0 && scenicSpot_latitude != 0 && agentId != 0) {
                        CoordinateInfoDto coordinateInfoDto = new CoordinateInfoDto();
                        coordinateInfoDto.setScenicSpot_longitude(scenicSpot_longitude);
                        coordinateInfoDto.setScenicSpot_latitude(scenicSpot_latitude);
                        coordinateInfoDto.setRemark(remark);
                        CoordinateRecord.getCoordinateRecordModel().setRecord(agentId, coordinateInfoDto);
                        map.put("msg", true);
                    } else {
                        map.put("msg", false);
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
