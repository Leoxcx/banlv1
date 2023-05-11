package com.util.web.servlet.table;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.bean.Attribute;
import com.util.service.AttributeService;
import com.util.service.impl.AttributeServiceImpl;

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

@WebServlet("/tabletrservlet")
public class TableTrServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        String tableName = request.getParameter("tableName");
        boolean tableExist = false;

        List<Attribute> attributeList = new ArrayList<>();
        if (tableName != null){
            AttributeService attributeService = new AttributeServiceImpl();
            attributeList = attributeService.findByAttrTable(tableName);
            if (attributeList != null) tableExist = true;
        }

        Map<String,Object> map = new HashMap<>();

        map.put("exist",tableExist);
        map.put("attributeList",attributeList);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
