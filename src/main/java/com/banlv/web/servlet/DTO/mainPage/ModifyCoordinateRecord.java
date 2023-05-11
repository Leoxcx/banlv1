package com.banlv.web.servlet.DTO.mainPage;

import com.banlv.model.CoordinateRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.bean.dto.CoordinateInfoDto;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//代理商修改定位信息
@WebServlet("/modifycoordinaterecord")
public class ModifyCoordinateRecord extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<>();

        String scenicSpot_longitude1 = request.getParameter("scenicSpot_longitude");
        String scenicSpot_latitude1 = request.getParameter("scenicSpot_latitude");
        String agent_id = request.getParameter("agent_id");
        String index1 = request.getParameter("index");
        if(StringUtils.isEmpty(request.getParameter(scenicSpot_longitude1)) && StringUtils.isEmpty(scenicSpot_latitude1) && StringUtils.isEmpty(agent_id) && StringUtils.isEmpty(index1)) {
            map.put("msg", false);
        } else {
            int index = Integer.parseInt(index1);
            long agentId = Long.valueOf(agent_id);

            //获取经度
            double scenicSpot_longitude = Double.parseDouble(scenicSpot_longitude1);
            //获取纬度
            double scenicSpot_latitude = Double.parseDouble(scenicSpot_latitude1);
            //获取备注
            String remark = request.getParameter("remark");

            if (scenicSpot_longitude == 0 && scenicSpot_latitude == 0 && agentId == 0) {
                map.put("msg", false);
            } else {
                CoordinateInfoDto coordinateInfoDto = new CoordinateInfoDto();
                coordinateInfoDto.setScenicSpot_latitude(scenicSpot_latitude);
                coordinateInfoDto.setScenicSpot_longitude(scenicSpot_longitude);
                coordinateInfoDto.setRemark(remark);
                if (CoordinateRecord.getCoordinateRecordModel().modifyRecord(index, agentId, coordinateInfoDto)) {
                    map.put("msg", true);
                } else {
                    map.put("msg", false);
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
