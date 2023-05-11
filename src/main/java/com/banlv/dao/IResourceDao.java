package com.banlv.dao;

import com.banlv.bean.Resource;

import java.util.List;
import java.util.Map;

public interface IResourceDao {
    /**
     * 查询所有
     * @return
     */
    List<Resource> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    List<Resource> findAllByPage(Map<String, Integer> pageMap);

    /**
     * 查询
     * @return
     */
    List<Resource> searchAllByPage(Map<String, Integer> pageMap, Resource resource);

    /**
     * 查询
     * @return
     */
    List<Resource> searchAll(Resource resource);

    /**
     * 查询总记录数
     * @return
     */
    int findTotalCount();

    /**
     * 查询总记录数
     * @return
     */
    int findSearchTotalCount(Resource resource);

    /**
     * 新增
     * @return
     */
    int addResource(Resource resource);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByResourceId(int resource_id);

    /**
     * 更新
     * @return
     */
    int updateResource(Resource resource);
}
