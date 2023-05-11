package com.banlv.service.impl;

import com.banlv.bean.City;
import com.banlv.dao.ICityDao;
import com.banlv.service.CityService;
import com.util.bean.PageBean;
import com.util.utils.GetSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityServiceImpl implements CityService {
    private static ICityDao mapper;

    static {
        mapper = GetSqlSession.safeSqlSession().getMapper(ICityDao.class);
    }
    @Override
    public List<City> findAll() {
        return mapper.findAll();
    }

    @Override
    public PageBean<City> findAllByPage(int currentPage, int rows) {
//        总记录数
        int totalCount = mapper.findTotalCount();
        int totalPage = (totalCount % rows)== 0 ? (totalCount / rows): (totalCount / rows) + 1;
        if (currentPage>totalPage) currentPage = totalPage;
        if (currentPage<=0) currentPage = 1;
//        包装
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("start",(currentPage-1)*rows);
        pageMap.put("rows",rows);
//        根据页数查询
        List<City> cityList = mapper.findAllByPage(pageMap);
        return  new PageBean<>(totalCount,totalPage,cityList,currentPage,rows);
    }


    @Override
    public PageBean<City> findAllByPage() {
        return findAllByPage(0,5);
    }

    @Override
    public PageBean<City> searchAllByPage(int currentPage, int rows, City city) {
//        总记录数
        int totalCount = mapper.findSearchTotalCount(city);
        int totalPage = (totalCount % rows)== 0 ? (totalCount / rows): (totalCount / rows) + 1;
        if (currentPage > totalPage) currentPage = totalPage;
        if (currentPage<=0) currentPage = 1;

//        包装
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("start",(currentPage-1)*rows);
        pageMap.put("rows",rows);
//        根据页数查询
        List<City> cityList = mapper.searchAllByPage(pageMap,city);
        return  new PageBean<>(totalCount,totalPage,cityList,currentPage,rows);
    }

    @Override
    public List<City> searchAll(City city) {
        return mapper.searchAll(city);
    }

    @Override
    public int addCity(City city) {
        int i = mapper.addCity(city);
        cleanSqlSession();
        return i;
    }

    @Override
    public int deleteByCityId(int cityId) {
        int i = mapper.deleteByCityId(cityId);
        cleanSqlSession();
        return i;
    }

    @Override
    public int updateCity(City city) {
        int i = mapper.updateCity(city);
        cleanSqlSession();
        return i;
    }

    public void cleanSqlSession (){
        GetSqlSession.cleanSqlSession();
    }

}
