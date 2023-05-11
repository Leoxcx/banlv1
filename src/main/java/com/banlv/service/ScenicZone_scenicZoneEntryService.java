package com.banlv.service;

import com.banlv.bean.ScenicZone_scenicZoneEntry;
import com.util.bean.PageBean;

import java.util.List;

public interface ScenicZone_scenicZoneEntryService {
    /**
     * 查询所有
     * @return
     */
    List<ScenicZone_scenicZoneEntry> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    PageBean<ScenicZone_scenicZoneEntry> findAllByPage(int currentPage, int rows);

    /**
     * 通过页面查询所有(空参)
     * @return
     */
    PageBean<ScenicZone_scenicZoneEntry> findAllByPage();

    /**
     * 查询
     * @return
     */
    PageBean<ScenicZone_scenicZoneEntry> searchAllByPage(int currentPage, int rows,ScenicZone_scenicZoneEntry scenicZone_scenicZoneEntry);

    /**
     * 查询所有
     * @return
     */
    List<ScenicZone_scenicZoneEntry> searchAll(ScenicZone_scenicZoneEntry scenicZone_scenicZoneEntry);

    /**
     * 新增
     * @return
     */
    int addScenicZone_scenicZoneEntry(ScenicZone_scenicZoneEntry scenicZone_scenicZoneEntry);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByScenicZone_scenicZoneEntryId(int scenicZone_scenicZoneEntryId);

    /**
     * 更新
     * @return
     */
    int updateScenicZone_scenicZoneEntry(ScenicZone_scenicZoneEntry scenicZone_scenicZoneEntry);
}
