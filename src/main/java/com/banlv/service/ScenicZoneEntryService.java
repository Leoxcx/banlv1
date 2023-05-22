package com.banlv.service;

import com.banlv.bean.ScenicZoneEntry;
import com.util.bean.PageBean;

import java.util.List;

public interface ScenicZoneEntryService {
    /**
     * 查询所有
     * @return
     */
    List<ScenicZoneEntry> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    PageBean<ScenicZoneEntry> findAllByPage(int currentPage, int rows);

    /**
     * 通过页面查询所有(空参)
     * @return
     */
    PageBean<ScenicZoneEntry> findAllByPage();

    /**
     * 查询
     * @return
     */
    PageBean<ScenicZoneEntry> searchAllByPage(int currentPage, int rows,ScenicZoneEntry scenicZoneEntry);

    /**
     * 查询所有
     * @return
     */
    List<ScenicZoneEntry> searchAll(ScenicZoneEntry scenicZoneEntry);

    /**
     * 新增
     * @return
     */
    int addScenicZoneEntry(ScenicZoneEntry scenicZoneEntry);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByScenicZoneEntryId(int scenicZoneEntryId);

    /**
     * 更新
     * @return
     */
    int updateScenicZoneEntry(ScenicZoneEntry scenicZoneEntry);
}
