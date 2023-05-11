package com.banlv.dao;

import com.banlv.bean.ScenicZone;

import java.util.List;
import java.util.Map;

public interface IScenicZoneDao {
    /**
     * 查询所有
     * @return
     */
    List<ScenicZone> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    List<ScenicZone> findAllByPage(Map<String, Integer> pageMap);

    /**
     * 查询
     * @return
     */
    List<ScenicZone> searchAllByPage(Map<String, Integer> pageMap, ScenicZone scenicZone);

    /**
     * 查询
     * @return
     */
    List<ScenicZone> searchAll(ScenicZone scenicZone);

    /**
     * 查询总记录数
     * @return
     */
    int findTotalCount();

    /**
     * 查询总记录数
     * @return
     */
    int findSearchTotalCount(ScenicZone scenicZone);

    /**
     * 新增
     * @return
     */
    int addScenicZone(ScenicZone scenicZone);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByScenicZoneId(int scenicZone_id);

    /**
     * 更新
     * @return
     */
    int updateScenicZone(ScenicZone scenicZone);
}
