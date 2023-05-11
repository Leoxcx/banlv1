package com.banlv.dao;

import com.banlv.bean.ScenicManage;

import java.util.List;
import java.util.Map;

public interface IScenicManageDao {
    /**
     * 查询所有
     * @return
     */
    List<ScenicManage> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    List<ScenicManage> findAllByPage(Map<String, Integer> pageMap);

    /**
     * 查询
     * @return
     */
    List<ScenicManage> searchAllByPage(Map<String, Integer> pageMap, ScenicManage scenicManage);

    /**
     * 查询
     * @return
     */
    List<ScenicManage> searchAll(ScenicManage scenicManage);

    /**
     * 查询总记录数
     * @return
     */
    int findTotalCount();

    /**
     * 查询总记录数
     * @return
     */
    int findSearchTotalCount(ScenicManage scenicManage);

    /**
     * 新增
     * @return
     */
    int addScenicManage(ScenicManage scenicManage);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByScenicManageId(int scenicManage_id);

    /**
     * 更新
     * @return
     */
    int updateScenicManage(ScenicManage scenicManage);
}
