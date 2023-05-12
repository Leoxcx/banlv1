package com.banlv.independent.myService;

import com.banlv.bean.City;
import com.util.bean.PageBean;

import java.util.List;


public interface MyCityService {
    /**
     * 查询所有
     *
     * @return
     */
    List<City> findAll();

    /**
     * 通过页面查询所有
     *
     * @return
     */
//    PageBean<City> searchAllByCityName(String city_name ,int currentPage, int rows);
    City searchAllByCityName(String city_name);

}