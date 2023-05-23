package com.banlv.web.servlet.DTO.mainPage;

import com.banlv.bean.City;
import com.banlv.bean.Resource;
import com.banlv.bean.ScenicSpot;
import com.banlv.bean.ScenicSpot_resource;
import com.banlv.service.ResourceService;
import com.banlv.service.ScenicSpot_resourceService;
import com.banlv.service.impl.CityServiceImpl;
import com.banlv.service.impl.ResourceServiceImpl;
import com.banlv.service.impl.ScenicSpot_resourceServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.bean.PageBean;
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

import static com.util.bean.transTool.TransTool.cityNameToCityInfo;


//通过景点id获取对应景点资源列表
@WebServlet("/getresourcesbyscenicspotid")
public class GetResourcesByScenicSpotId extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<>();

        String scenicSpot_id = request.getParameter("scenicSpot_id");

        List<Resource> resourceList = new ArrayList<>();

        if(scenicSpot_id.equals("")){
            map.put("msg", false);
            map.put("resources", null);
        }else {
            // 查找景点资源中间表
            ScenicSpot_resourceService scenicSpot_resourceService = new ScenicSpot_resourceServiceImpl();
            ScenicSpot_resource searchscenicSpot_resource = new ScenicSpot_resource();
            searchscenicSpot_resource.setScenicSpot_id(Long.valueOf(scenicSpot_id));
            List<ScenicSpot_resource> scenicSpot_resources = scenicSpot_resourceService.searchAll(searchscenicSpot_resource);
            if (scenicSpot_resources.size() == 0){
                map.put("msg", false);
                map.put("resources", null);
            }else {
                // 通过中间表获取资源列表
                ResourceService resourceService = new ResourceServiceImpl();

                for (ScenicSpot_resource scenicSpot_resource : scenicSpot_resources) {
                    Resource searchresource = new Resource();
                    searchresource.setResource_id(scenicSpot_resource.getResource_id());
                    List<Resource> resources = resourceService.searchAll(searchresource);
                    if (resources.size() == 0) continue;
                    else resourceList.add(resources.get(0));
                }
            }
        }
        if (resourceList.size() == 0){
            map.put("msg", false);
            map.put("resources", resourceList);
        }else {
            map.put("msg", true);
            map.put("resources", resourceList);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
