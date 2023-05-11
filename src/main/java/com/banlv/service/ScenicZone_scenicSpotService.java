package com.banlv.service;

import com.banlv.bean.ScenicZone_scenicSpot;
import com.util.bean.PageBean;

import java.util.List;

public interface ScenicZone_scenicSpotService {
    /**
     * 查询所有
     * @return
     */
    List<ScenicZone_scenicSpot> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    PageBean<ScenicZone_scenicSpot> findAllByPage(int currentPage, int rows);

    /**
     * 通过页面查询所有(空参)
     * @return
     */
    PageBean<ScenicZone_scenicSpot> findAllByPage();

    /**
     * 查询
     * @return
     */
    PageBean<ScenicZone_scenicSpot> searchAllByPage(int currentPage, int rows,ScenicZone_scenicSpot scenicZone_scenicSpot);

    /**
     * 查询所有
     * @return
     */
    List<ScenicZone_scenicSpot> searchAll(ScenicZone_scenicSpot scenicZone_scenicSpot);

    /**
     * 通过spotId查询所有
     * @return
     */
    List<ScenicZone_scenicSpot> searchBySpotId(ScenicZone_scenicSpot scenicZone_scenicSpot);
    /**
     * 新增
     * @return
     */
    int addScenicZone_scenicSpot(ScenicZone_scenicSpot scenicZone_scenicSpot);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByScenicZone_scenicSpotId(int scenicZone_scenicSpotId);

    /**
     * 更新
     * @return
     */
    int updateScenicZone_scenicSpot(ScenicZone_scenicSpot scenicZone_scenicSpot);
}
