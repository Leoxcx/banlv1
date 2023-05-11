package com.banlv.service;

import com.banlv.bean.ScenicSpot;
import com.util.bean.PageBean;

import java.util.List;

public interface ScenicSpotService {
    /**
     * 查询所有
     * @return
     */
    List<ScenicSpot> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    PageBean<ScenicSpot> findAllByPage(int currentPage, int rows);

    /**
     * 通过页面查询所有(空参)
     * @return
     */
    PageBean<ScenicSpot> findAllByPage();

    /**
     * 查询
     * @return
     */
    PageBean<ScenicSpot> searchAllByPage(int currentPage, int rows,ScenicSpot scenicSpot);

    /**
     * 查询所有
     * @return
     */
    List<ScenicSpot> searchAll(ScenicSpot scenicSpot);

    /**
     * 新增
     * @return
     */
    int addScenicSpot(ScenicSpot scenicSpot);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByScenicSpotId(int scenicSpotId);

    /**
     * 更新
     * @return
     */
    int updateScenicSpot(ScenicSpot scenicSpot);
}
