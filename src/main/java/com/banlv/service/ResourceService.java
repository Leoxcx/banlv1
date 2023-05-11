package com.banlv.service;

import com.banlv.bean.Resource;
import com.util.bean.PageBean;

import java.util.List;

public interface ResourceService {
    /**
     * 查询所有
     * @return
     */
    List<Resource> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    PageBean<Resource> findAllByPage(int currentPage, int rows);

    /**
     * 通过页面查询所有(空参)
     * @return
     */
    PageBean<Resource> findAllByPage();

    /**
     * 查询
     * @return
     */
    PageBean<Resource> searchAllByPage(int currentPage, int rows,Resource resource);

    /**
     * 查询所有
     * @return
     */
    List<Resource> searchAll(Resource resource);

    /**
     * 新增
     * @return
     */
    int addResource(Resource resource);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByResourceId(int resourceId);

    /**
     * 更新
     * @return
     */
    int updateResource(Resource resource);
}
