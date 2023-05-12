package com.banlv.web.servlet.DTO.mainPage;

import com.banlv.bean.City;
import com.banlv.service.impl.CityServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.bean.PageBean;
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

import static com.util.bean.transTool.TransTool.cityNameToCityInfo;


//获取热门城市信息
@WebServlet("/getpopularcities")
public class GetPopularCities extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<>();

        map.put("msg", false);
        map.put("cities", null);

        CityServiceImpl cityService = new CityServiceImpl();
        PageBean<City> allByPage = cityService.findAllByPage(1,5);
        List<City> cities = allByPage.getList();
        if(!cities.isEmpty()) {
            map.put("msg", true);
            map.put("cities", cities);
        }


        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
