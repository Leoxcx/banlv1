package com.banlv.web.servlet.DTO.mainPage;

import com.banlv.bean.ScenicSpot;
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

import static com.util.utils.transTool.TransTool.resourceToScenicSpot;

//资源resource_id获取景点信息
@WebServlet("/resourcetoscenicspotservlet")
public class ResourceToScenicSpotServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        Map<String,Object> map = new HashMap<>();

        //前端获取resourceId

        String resourceId = request.getParameter("resource_id");


        //景点资源中间表

        if (StringUtils.isEmpty(resourceId)) {
            map.put("msg", false);
            map.put("scenicSpot", null);
        } else {
            List<ScenicSpot> scenicSpots = resourceToScenicSpot(Long.parseLong(resourceId));

            if (scenicSpots.isEmpty()) {
                map.put("msg", false);
                map.put("scenicSpot", null);
            } else {
                map.put("msg", true);
                map.put("scenicSpot", scenicSpots);
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

//    public static List<ScenicSpot> resourceToSpot(long resourceId) {
//
//    }
}