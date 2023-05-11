package com.banlv.service;

import com.banlv.bean.ScenicZone;
import com.util.bean.PageBean;

import java.util.List;

public interface ScenicZoneService {
    /**
     * 查询所有
     * @return
     */
    List<ScenicZone> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    PageBean<ScenicZone> findAllByPage(int currentPage, int rows);

    /**
     * 通过页面查询所有(空参)
     * @return
     */
    PageBean<ScenicZone> findAllByPage();

    /**
     * 查询
     * @return
     */
    PageBean<ScenicZone> searchAllByPage(int currentPage, int rows,ScenicZone scenicZone);

    /**
     * 查询所有
     * @return
     */
    List<ScenicZone> searchAll(ScenicZone scenicZone);

    /**
     * 新增
     * @return
     */
    int addScenicZone(ScenicZone scenicZone);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByScenicZoneId(int scenicZoneId);

    /**
     * 更新
     * @return
     */
    int updateScenicZone(ScenicZone scenicZone);
}
