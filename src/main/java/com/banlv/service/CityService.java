package com.banlv.service;

import com.banlv.bean.City;
import com.util.bean.PageBean;

import java.util.List;

public interface CityService {
    /**
     * 查询所有
     * @return
     */
    List<City> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    PageBean<City> findAllByPage(int currentPage, int rows);

    /**
     * 通过页面查询所有(空参)
     * @return
     */
    PageBean<City> findAllByPage();

    /**
     * 查询
     * @return
     */
    PageBean<City> searchAllByPage(int currentPage, int rows,City city);

    /**
     * 查询所有
     * @return
     */
    List<City> searchAll(City city);

    /**
     * 新增
     * @return
     */
    int addCity(City city);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByCityId(int cityId);

    /**
     * 更新
     * @return
     */
    int updateCity(City city);
}
