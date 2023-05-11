package com.util.web.servlet.attribute;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.bean.Attribute;
import com.util.bean.PageBean;
import com.util.service.AttributeService;
import com.util.service.impl.AttributeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/attributelistservlet")
public class AttributeListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int rows = Integer.parseInt(request.getParameter("rows"));

        System.out.println(currentPage);
        System.out.println(rows);

        AttributeService attributeService = new AttributeServiceImpl();
        PageBean<Attribute> allByPage = attributeService.findAllByPage(currentPage,rows);

        Map<String,Object> map = new HashMap<>();
        map.put("page",allByPage);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
