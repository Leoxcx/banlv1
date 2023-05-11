package com.banlv.web.servlet.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.banlv.bean.Resource;
import com.banlv.service.ResourceService;
import com.banlv.service.impl.ResourceServiceImpl;
import com.util.utils.MyTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/resourceupdateservlet")
public class ResourceUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        Map<String, String[]> parameterMap = request.getParameterMap();

        Resource resource= new Resource();
        Resource resourcefromWeb = new MyTool<Resource>(Resource.class).getObjectFromWeb(resource, parameterMap);

        ResourceService resourceService = new ResourceServiceImpl();
        int i = resourceService.updateResource(resourcefromWeb);

        Map<String,Object> map = new HashMap<>();
        if (i == 1) map.put("message",true);
        else map.put("message",false);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
