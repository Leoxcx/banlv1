package com.banlv.dao;

import com.banlv.bean.ScenicZone_scenicZoneEntry;

import java.util.List;
import java.util.Map;

public interface IScenicZone_scenicZoneEntryDao {
    /**
     * 查询所有
     * @return
     */
    List<ScenicZone_scenicZoneEntry> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    List<ScenicZone_scenicZoneEntry> findAllByPage(Map<String, Integer> pageMap);

    /**
     * 查询
     * @return
     */
    List<ScenicZone_scenicZoneEntry> searchAllByPage(Map<String, Integer> pageMap, ScenicZone_scenicZoneEntry scenicZone_scenicZoneEntry);

    /**
     * 查询
     * @return
     */
    List<ScenicZone_scenicZoneEntry> searchAll(ScenicZone_scenicZoneEntry scenicZone_scenicZoneEntry);

    /**
     * 查询总记录数
     * @return
     */
    int findTotalCount();

    /**
     * 查询总记录数
     * @return
     */
    int findSearchTotalCount(ScenicZone_scenicZoneEntry scenicZone_scenicZoneEntry);

    /**
     * 新增
     * @return
     */
    int addScenicZone_scenicZoneEntry(ScenicZone_scenicZoneEntry scenicZone_scenicZoneEntry);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByScenicZone_scenicZoneEntryId(int scenicZone_scenicZoneEntry_id);

    /**
     * 更新
     * @return
     */
    int updateScenicZone_scenicZoneEntry(ScenicZone_scenicZoneEntry scenicZone_scenicZoneEntry);
}
