package com.banlv.independent.myDao;

import com.banlv.bean.City;
import com.banlv.bean.ScenicZone;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MyScenicZoneDao {

    /**
     * 通过city_id查询所有该城市的景区数
     *
     * @return
     */
    Integer searchTotalCountByCityId(@Param(value="city_id") int city_id);

    /**
     * 通过city_id 和 scenicZone_name 模糊查询所有该城市的景区
     *
     * @return
     */
    List<ScenicZone> fuzzySearchByScenicZoneName( ScenicZone scenicZone);


}
