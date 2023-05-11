package com.util.web.servlet.attribute;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.bean.Attribute;
import com.util.service.AttributeService;
import com.util.service.impl.AttributeServiceImpl;
import com.util.utils.MyTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/attributeupdateservlet")
public class AttributeUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        Map<String, String[]> parameterMap = request.getParameterMap();

        Attribute attribute = new Attribute();
        Attribute attributefromWeb = new MyTool<Attribute>(Attribute.class).getObjectFromWeb(attribute, parameterMap);
        System.out.println(attributefromWeb);

        AttributeService attributeService = new AttributeServiceImpl();
        int i = attributeService.updateAttribute(attributefromWeb);

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
