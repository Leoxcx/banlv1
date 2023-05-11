package com.banlv.web.servlet.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.utils.MyTool;
import com.banlv.bean.Resource;
import com.banlv.service.ResourceService;
import com.banlv.service.impl.ResourceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/resourcetotalsearchservlet")
public class ResourceTotalSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        Map<String, String[]> parameterMap = request.getParameterMap();

        Resource resource = new Resource();
        Resource resourcefromWeb = new MyTool<Resource>(Resource.class).getObjectFromWeb(resource, parameterMap);

        ResourceService resourceService = new ResourceServiceImpl();
        List<Resource> resources = resourceService.searchAll(resourcefromWeb);

        Map<String,Object> map = new HashMap<>();
        map.put("resources",resources);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
