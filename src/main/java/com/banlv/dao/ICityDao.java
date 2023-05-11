package com.banlv.dao;

import com.banlv.bean.City;

import java.util.List;
import java.util.Map;

public interface ICityDao {
    /**
     * 查询所有
     * @return
     */
    List<City> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    List<City> findAllByPage(Map<String, Integer> pageMap);

    /**
     * 查询
     * @return
     */
    List<City> searchAllByPage(Map<String, Integer> pageMap, City city);

    /**
     * 查询
     * @return
     */
    List<City> searchAll(City city);

    /**
     * 查询总记录数
     * @return
     */
    int findTotalCount();

    /**
     * 查询总记录数
     * @return
     */
    int findSearchTotalCount(City city);

    /**
     * 新增
     * @return
     */
    int addCity(City city);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByCityId(int city_id);

    /**
     * 更新
     * @return
     */
    int updateCity(City city);
}
