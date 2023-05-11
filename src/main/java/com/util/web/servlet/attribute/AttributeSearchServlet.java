package com.util.web.servlet.attribute;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.bean.Attribute;
import com.util.bean.PageBean;
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

@WebServlet("/attributesearchservlet")
public class AttributeSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int rows = Integer.parseInt(request.getParameter("rows"));

        Map<String, String[]> parameterMap = request.getParameterMap();

        Attribute attribute = new Attribute();
        Attribute attributefromWeb = new MyTool<Attribute>(Attribute.class).getObjectFromWeb(attribute, parameterMap);

        AttributeService attributeService = new AttributeServiceImpl();
        PageBean<Attribute> allByPage = attributeService.searchAllByPage(currentPage,rows,attributefromWeb);

        Map<String,Object> map = new HashMap<>();
        map.put("page",allByPage);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
