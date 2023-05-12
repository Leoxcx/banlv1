package com.banlv.independent.myService.myImpl;

import com.banlv.bean.City;
import com.banlv.bean.ScenicZone;
import com.banlv.independent.myDao.MyCityDao;
import com.banlv.independent.myDao.MyScenicZoneDao;
import com.banlv.independent.myService.MyCityService;
import com.banlv.independent.myService.MyScenicZoneService;
import com.util.bean.PageBean;
import com.util.utils.GetSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyScenicZoneServiceImpl implements MyScenicZoneService {
    private static MyScenicZoneDao mapper;

    static {
        mapper = GetSqlSession.safeSqlSession().getMapper(MyScenicZoneDao.class);
    }


    @Override
    public Integer SearchTotalCountByCityId(int city_id) {
        return mapper.SearchTotalCountByCityId(city_id);
    }

    @Override
    public List<ScenicZone> FuzzySearchByScenicZoneName(ScenicZone scenicZone) {
        return mapper.FuzzySearchByScenicZoneName(scenicZone);
    }
}