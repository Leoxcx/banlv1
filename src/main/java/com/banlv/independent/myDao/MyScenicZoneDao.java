package com.banlv.independent.myDao;

import com.banlv.bean.City;
import com.banlv.bean.ScenicZone;

import java.util.List;
import java.util.Map;

public interface MyScenicZoneDao {

    /**
     * 通过city_id查询所有该城市的景区数
     *
     * @return
     */
    Integer SearchTotalCountByCityId(int city_id);

    /**
     * 通过city_id 和 scenicZone_name 模糊查询所有该城市的景区
     *
     * @return
     */
    List<ScenicZone> FuzzySearchByScenicZoneName(ScenicZone scenicZone);


}
