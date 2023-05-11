package com.banlv.service;

import com.banlv.bean.User_resource;
import com.util.bean.PageBean;

import java.util.List;

public interface User_resourceService {
    /**
     * 查询所有
     * @return
     */
    List<User_resource> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    PageBean<User_resource> findAllByPage(int currentPage, int rows);

    /**
     * 通过页面查询所有(空参)
     * @return
     */
    PageBean<User_resource> findAllByPage();

    /**
     * 查询
     * @return
     */
    PageBean<User_resource> searchAllByPage(int currentPage, int rows,User_resource user_resource);

    /**
     * 查询所有
     * @return
     */
    List<User_resource> searchAll(User_resource user_resource);

    /**
     * 新增
     * @return
     */
    int addUser_resource(User_resource user_resource);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByUser_resourceId(int user_resourceId);

    /**
     * 更新
     * @return
     */
    int updateUser_resource(User_resource user_resource);
}
