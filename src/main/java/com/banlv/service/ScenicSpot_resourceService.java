package com.banlv.service;

import com.banlv.bean.ScenicSpot_resource;
import com.util.bean.PageBean;

import java.util.List;

public interface ScenicSpot_resourceService {
    /**
     * 查询所有
     * @return
     */
    List<ScenicSpot_resource> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    PageBean<ScenicSpot_resource> findAllByPage(int currentPage, int rows);

    /**
     * 通过页面查询所有(空参)
     * @return
     */
    PageBean<ScenicSpot_resource> findAllByPage();

    /**
     * 查询
     * @return
     */
    PageBean<ScenicSpot_resource> searchAllByPage(int currentPage, int rows,ScenicSpot_resource scenicSpot_resource);

    /**
     * 查询所有
     * @return
     */
    List<ScenicSpot_resource> searchAll(ScenicSpot_resource scenicSpot_resource);

    /**
     * 新增
     * @return
     */
    int addScenicSpot_resource(ScenicSpot_resource scenicSpot_resource);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByScenicSpot_resourceId(int scenicSpot_resourceId);

    /**
     * 更新
     * @return
     */
    int updateScenicSpot_resource(ScenicSpot_resource scenicSpot_resource);
}
