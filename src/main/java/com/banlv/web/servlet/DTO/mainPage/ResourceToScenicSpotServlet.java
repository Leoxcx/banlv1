package com.banlv.web.servlet.DTO.mainPage;

import com.banlv.bean.ScenicSpot;
import com.banlv.bean.ScenicSpot_resource;
import com.banlv.service.ScenicSpot_resourceService;
import com.banlv.service.impl.ScenicSpot_resourceServiceImpl;
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
        if(StringUtils.isEmpty(resourceId)) {
            map.put("msg", false);
            map.put("scenicSpot_resources", null);
        }else {
            ScenicSpot_resource scenicSpot_resource = new ScenicSpot_resource();
            scenicSpot_resource.setScenicSpot_id(Long.parseLong(resourceId));
            System.out.println(scenicSpot_resource.getResource_id());

            ScenicSpot_resourceService spotResource = new ScenicSpot_resourceServiceImpl();
            List<ScenicSpot_resource> scenicSpot_resources = spotResource.searchAll(scenicSpot_resource);

            if (scenicSpot_resources.isEmpty()) {
                map.put("msg", false);
                map.put("scenicSpot_resources", null);
            } else {
                if (1 == scenicSpot_resources.get(0).getScenicSpot_resource_use()) {
                    long scenicSpot_id = scenicSpot_resources.get(0).getScenicSpot_id();
                    scenicSpot_resource = new ScenicSpot_resource();
                    scenicSpot_resource.setScenicSpot_id(scenicSpot_id);
                    List<ScenicSpot_resource> scenicSpot = spotResource.searchAll(scenicSpot_resource);
                    map.put("msg", true);
                    map.put("scenicSpot", scenicSpot);
                }
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