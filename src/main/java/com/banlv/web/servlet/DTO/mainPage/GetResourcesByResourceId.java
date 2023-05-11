package com.banlv.web.servlet.DTO.mainPage;

import com.banlv.bean.*;
import com.banlv.service.ResourceService;
import com.banlv.service.ScenicSpot_resourceService;
import com.banlv.service.UserService;
import com.banlv.service.impl.ResourceServiceImpl;
import com.banlv.service.impl.ScenicSpot_resourceServiceImpl;
import com.banlv.service.impl.UserPlayServiceImpl;
import com.banlv.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/getresourcesbyresourceid")
public class GetResourcesByResourceId extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        Map<String,Object> map = new HashMap<>();


        //获取resource_id
        Integer resource_id = Integer.valueOf(request.getParameter("resource_id"));

        ScenicSpot_resourceService scenicSpot_resourceService = new ScenicSpot_resourceServiceImpl();
        ResourceService resourceService = new ResourceServiceImpl();
        ScenicSpot_resource scenicSpot_resource = new ScenicSpot_resource();
        scenicSpot_resource.setResource_id(resource_id);
        List<ScenicSpot_resource> ScenicSpot_resources = scenicSpot_resourceService.searchAll(scenicSpot_resource);
        if (ScenicSpot_resources.isEmpty()){
            map.put("msg", false);
            map.put("resources", 1);
        }else {
            ScenicSpot_resource searchScenicSpot_resource = new ScenicSpot_resource();
            searchScenicSpot_resource.setScenicSpot_id(ScenicSpot_resources.get(0).getScenicSpot_id());
            searchScenicSpot_resource.setScenicSpot_resource_use(1);
            List<ScenicSpot_resource> scenicSpot_resources = scenicSpot_resourceService.searchAll(searchScenicSpot_resource);
            if(scenicSpot_resources.isEmpty()){
                map.put("msg", false);
                map.put("resources", 2);
            }
            List<Resource> resources = new ArrayList<>();
            for (ScenicSpot_resource scenicSpotResource : scenicSpot_resources) {
                Resource searchresource = new Resource();
                searchresource.setResource_id(scenicSpotResource.getResource_id());
                List<Resource> resourceList = resourceService.searchAll(searchresource);
                if(!resourceList.isEmpty()) resources.add(resourceList.get(0));
            }
            if(resources.isEmpty()){
                map.put("msg", false);
                map.put("resources", 3);
            }else {
                map.put("msg", true);
                map.put("resources", resources);
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}