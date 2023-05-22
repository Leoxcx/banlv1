package com.banlv.independent.myService;

import com.banlv.bean.City;
import com.util.bean.PageBean;
import org.apache.ibatis.annotations.Param;

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
    City searchAllByCityName(String cityName);

    /**
     * 查询总记录数
     * @return
     */
    int findTotalCount();

    /**
     * 通过city_name查询city_id
     * @return
     */
    int searchIdByCityName(String cityName);

}