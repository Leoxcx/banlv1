package com.banlv.web.servlet.DTO.mainPage;

import com.banlv.model.CoordinateRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.bean.dto.CoordinateInfoDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/agenttocoordinaterecord")
public class AgentToCoordinateRecord extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<>();

        long agentId = Long.parseLong(request.getParameter("agent_id"));

        if(agentId == 0) {
            map.put("msg", false);
            map.put("coordinateInfoDtoList", null);
        }else {
            List<CoordinateInfoDto> coordinateInfoDtoList = CoordinateRecord.getCoordinateRecordModel().getRecord(agentId);
            if(coordinateInfoDtoList == null) {
                map.put("msg", false);
                map.put("coordinateInfoDtoList", null);
            } else{
                map.put("msg", true);
                map.put("coordinateInfoDtoList", coordinateInfoDtoList);
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}