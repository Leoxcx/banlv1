package com.banlv.service;

import com.banlv.bean.ScenicZoneType;
import com.util.bean.PageBean;

import java.util.List;

public interface ScenicZoneTypeService {
    /**
     * 查询所有
     * @return
     */
    List<ScenicZoneType> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    PageBean<ScenicZoneType> findAllByPage(int currentPage, int rows);

    /**
     * 通过页面查询所有(空参)
     * @return
     */
    PageBean<ScenicZoneType> findAllByPage();

    /**
     * 查询
     * @return
     */
    PageBean<ScenicZoneType> searchAllByPage(int currentPage, int rows,ScenicZoneType scenicZoneType);

    /**
     * 查询所有
     * @return
     */
    List<ScenicZoneType> searchAll(ScenicZoneType scenicZoneType);

    /**
     * 新增
     * @return
     */
    int addScenicZoneType(ScenicZoneType scenicZoneType);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByScenicZoneTypeId(int scenicZoneTypeId);

    /**
     * 更新
     * @return
     */
    int updateScenicZoneType(ScenicZoneType scenicZoneType);
}
