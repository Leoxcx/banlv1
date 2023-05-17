package com.banlv.web.servlet.DTO.scenicZonePage;

import com.banlv.bean.City;
import com.banlv.independent.myService.MyCityService;
import com.banlv.independent.myService.myImpl.MyCityServiceImpl;
import com.banlv.independent.myService.myImpl.MyScenicZoneServiceImpl;
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

import static com.util.bean.transTool.TransTool.*;



@WebServlet("/fuzzyquerycityinfo")
public class FuzzyQueryCityInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<>();

        String city_name = request.getParameter("city_name");

        map.put("msg", false);
        map.put("cities", null);
        map.put("count", 0);

        City city1 = new City();

        if(StringUtils.isNotEmpty(city_name)) {
            //直接查询城市名是否存在于数据库中

            city1.setCity_name(city_name);

            List<City> cityList1 = cityNameToCityInfo(city1);
            if(cityList1.isEmpty()){
                //不存在则调用模糊查询
                MyCityService myCityService = new MyCityServiceImpl();
                //模糊查询城市名，返回1个城市信息
                city1 = myCityService.searchAllByCityName(city_name);
                if(city1 != null) {
                    //通过城市编号 city_id 获取当前城市包含的景区数
                    Integer city_id = city1.getCity_id();
                    MyScenicZoneServiceImpl myScenicZoneService = new MyScenicZoneServiceImpl();
                    int count = myScenicZoneService.SearchTotalCountByCityId(city_id);
                    map.put("msg", true);
                    map.put("city", city1);
                    map.put("count", count);
                }

            } else {
                //通过城市编号 city_id 获取当前城市包含的景区数
                Integer city_id = cityList1.get(0).getCity_id();
                MyScenicZoneServiceImpl myScenicZoneService = new MyScenicZoneServiceImpl();
                int count = myScenicZoneService.SearchTotalCountByCityId(city_id);
                map.put("msg", true);
                map.put("city", city1);
                map.put("count", count);
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}