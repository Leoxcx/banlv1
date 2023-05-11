package com.banlv.web.servlet.DTO.agentPage;

import com.banlv.model.CoordinateRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//代理商删除定位信息
@WebServlet("/removecoordinaterecord")
public class RemoveCoordinateRecord extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<>();

        String index1 = request.getParameter("index");
        String agent_id = request.getParameter("agent_id");

        if(StringUtils.isEmpty(index1) && StringUtils.isEmpty(agent_id)) {
            map.put("msg", false);
        } else {
            int index = Integer.parseInt(index1);
            long agentId = Long.valueOf(agent_id);

            if(agentId == 0) {
                map.put("msg", false);
            } else {
                //删除元素
                if(CoordinateRecord.getCoordinateRecordModel().removeRecord(index, agentId)) {
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