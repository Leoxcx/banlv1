package com.banlv.dao;

import com.banlv.bean.ScenicZoneType;

import java.util.List;
import java.util.Map;

public interface IScenicZoneTypeDao {
    /**
     * 查询所有
     * @return
     */
    List<ScenicZoneType> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    List<ScenicZoneType> findAllByPage(Map<String, Integer> pageMap);

    /**
     * 查询
     * @return
     */
    List<ScenicZoneType> searchAllByPage(Map<String, Integer> pageMap, ScenicZoneType scenicZoneType);

    /**
     * 查询
     * @return
     */
    List<ScenicZoneType> searchAll(ScenicZoneType scenicZoneType);

    /**
     * 查询总记录数
     * @return
     */
    int findTotalCount();

    /**
     * 查询总记录数
     * @return
     */
    int findSearchTotalCount(ScenicZoneType scenicZoneType);

    /**
     * 新增
     * @return
     */
    int addScenicZoneType(ScenicZoneType scenicZoneType);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByScenicZoneTypeId(int scenicZoneType_id);

    /**
     * 更新
     * @return
     */
    int updateScenicZoneType(ScenicZoneType scenicZoneType);
}
