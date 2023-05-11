package com.banlv.dao;

import com.banlv.bean.ScenicSpot_resource;

import java.util.List;
import java.util.Map;

public interface IScenicSpot_resourceDao {
    /**
     * 查询所有
     * @return
     */
    List<ScenicSpot_resource> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    List<ScenicSpot_resource> findAllByPage(Map<String, Integer> pageMap);

    /**
     * 查询
     * @return
     */
    List<ScenicSpot_resource> searchAllByPage(Map<String, Integer> pageMap, ScenicSpot_resource scenicSpot_resource);

    /**
     * 查询
     * @return
     */
    List<ScenicSpot_resource> searchAll(ScenicSpot_resource scenicSpot_resource);
    /**
     * 通过spotId查询
     * @return
     */
    List<ScenicSpot_resource> searchBySpotId(ScenicSpot_resource scenicSpot_resource);

    /**
     * 查询总记录数
     * @return
     */
    int findTotalCount();

    /**
     * 查询总记录数
     * @return
     */
    int findSearchTotalCount(ScenicSpot_resource scenicSpot_resource);

    /**
     * 新增
     * @return
     */
    int addScenicSpot_resource(ScenicSpot_resource scenicSpot_resource);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByScenicSpot_resourceId(int scenicSpot_resource_id);

    /**
     * 更新
     * @return
     */
    int updateScenicSpot_resource(ScenicSpot_resource scenicSpot_resource);
}
