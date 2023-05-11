package com.banlv.web.servlet.city;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.banlv.bean.City;
import com.banlv.service.CityService;
import com.banlv.service.impl.CityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/citytotallistservlet")
public class CityTotalListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        CityService cityService = new CityServiceImpl();
        List<City> citys = cityService.findAll();

        Map<String,Object> map = new HashMap<>();
        map.put("citys",citys);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
