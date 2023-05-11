package com.banlv.dao;

import com.banlv.bean.ScenicSpot;

import java.util.List;
import java.util.Map;

public interface IScenicSpotDao {
    /**
     * 查询所有
     * @return
     */
    List<ScenicSpot> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    List<ScenicSpot> findAllByPage(Map<String, Integer> pageMap);

    /**
     * 查询
     * @return
     */
    List<ScenicSpot> searchAllByPage(Map<String, Integer> pageMap, ScenicSpot scenicSpot);

    /**
     * 查询
     * @return
     */
    List<ScenicSpot> searchAll(ScenicSpot scenicSpot);

    /**
     * 查询总记录数
     * @return
     */
    int findTotalCount();

    /**
     * 查询总记录数
     * @return
     */
    int findSearchTotalCount(ScenicSpot scenicSpot);

    /**
     * 新增
     * @return
     */
    int addScenicSpot(ScenicSpot scenicSpot);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByScenicSpotId(int scenicSpot_id);

    /**
     * 更新
     * @return
     */
    int updateScenicSpot(ScenicSpot scenicSpot);
}
