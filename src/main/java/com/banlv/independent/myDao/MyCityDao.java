package com.banlv.independent.myDao;

import com.banlv.bean.City;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyCityDao {
    /**
     * 查询所有
     *
     * @return
     */
    List<City> findAll();

    /**
     * 查询总记录数
     * @return
     */
    int findTotalCount();

    City searchAllByCityName(@Param(value="cityName")  String cityName);
}
