package com.banlv.dao;

import com.banlv.bean.ScenicZoneEntry;

import java.util.List;
import java.util.Map;

public interface IScenicZoneEntryDao {
    /**
     * 查询所有
     * @return
     */
    List<ScenicZoneEntry> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    List<ScenicZoneEntry> findAllByPage(Map<String, Integer> pageMap);

    /**
     * 查询
     * @return
     */
    List<ScenicZoneEntry> searchAllByPage(Map<String, Integer> pageMap, ScenicZoneEntry scenicZoneEntry);

    /**
     * 查询
     * @return
     */
    List<ScenicZoneEntry> searchAll(ScenicZoneEntry scenicZoneEntry);

    /**
     * 查询总记录数
     * @return
     */
    int findTotalCount();

    /**
     * 查询总记录数
     * @return
     */
    int findSearchTotalCount(ScenicZoneEntry scenicZoneEntry);

    /**
     * 新增
     * @return
     */
    int addScenicZoneEntry(ScenicZoneEntry scenicZoneEntry);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByScenicZoneEntryId(int scenicZoneEntry_id);

    /**
     * 更新
     * @return
     */
    int updateScenicZoneEntry(ScenicZoneEntry scenicZoneEntry);
}
