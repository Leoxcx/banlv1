package com.banlv.dao;

import com.banlv.bean.ScenicZone_scenicSpot;

import java.util.List;
import java.util.Map;

public interface IScenicZone_scenicSpotDao {
    /**
     * 查询所有
     * @return
     */
    List<ScenicZone_scenicSpot> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    List<ScenicZone_scenicSpot> findAllByPage(Map<String, Integer> pageMap);

    /**
     * 查询
     * @return
     */
    List<ScenicZone_scenicSpot> searchAllByPage(Map<String, Integer> pageMap, ScenicZone_scenicSpot scenicZone_scenicSpot);

    /**
     * 查询
     * @return
     */
    List<ScenicZone_scenicSpot> searchAll(ScenicZone_scenicSpot scenicZone_scenicSpot);

    /**
     * 查询
     * @return
     */
    List<ScenicZone_scenicSpot> searchBySpotId(ScenicZone_scenicSpot scenicZone_scenicSpot);

    /**
     * 查询总记录数
     * @return
     */
    int findTotalCount();

    /**
     * 查询总记录数
     * @return
     */
    int findSearchTotalCount(ScenicZone_scenicSpot scenicZone_scenicSpot);

    /**
     * 新增
     * @return
     */
    int addScenicZone_scenicSpot(ScenicZone_scenicSpot scenicZone_scenicSpot);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByScenicZone_scenicSpotId(int scenicZone_scenicSpot_id);

    /**
     * 更新
     * @return
     */
    int updateScenicZone_scenicSpot(ScenicZone_scenicSpot scenicZone_scenicSpot);
}
