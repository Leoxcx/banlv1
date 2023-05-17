package com.banlv.independent.myService.myImpl;

import com.banlv.bean.City;
import com.banlv.dao.ICityDao;
import com.banlv.independent.myDao.MyCityDao;
import com.banlv.independent.myService.MyCityService;
import com.banlv.service.CityService;
import com.util.bean.PageBean;
import com.util.utils.GetSqlSession;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyCityServiceImpl implements MyCityService {
    private static MyCityDao mapper;

    static {
        mapper = GetSqlSession.safeSqlSession().getMapper(MyCityDao.class);
    }

    @Override
    public List<City> findAll() {
        return mapper.findAll();
    }

    @Override
    public City searchAllByCityName(String cityName) {
        return mapper.searchAllByCityName(cityName);
    }

    @Override
    public int findTotalCount() {
        return mapper.findTotalCount();
    }

}