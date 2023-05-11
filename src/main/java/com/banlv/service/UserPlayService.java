package com.banlv.service;

import com.banlv.bean.UserPlay;
import com.util.bean.PageBean;

import java.util.List;

public interface UserPlayService {
    /**
     * 查询所有
     * @return
     */
    List<UserPlay> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    PageBean<UserPlay> findAllByPage(int currentPage, int rows);

    /**
     * 通过页面查询所有(空参)
     * @return
     */
    PageBean<UserPlay> findAllByPage();

    /**
     * 查询
     * @return
     */
    PageBean<UserPlay> searchAllByPage(int currentPage, int rows,UserPlay userPlay);

    /**
     * 查询所有
     * @return
     */
    List<UserPlay> searchAll(UserPlay userPlay);

    /**
     * 新增
     * @return
     */
    int addUserPlay(UserPlay userPlay);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByUserPlayId(int userPlayId);

    /**
     * 更新
     * @return
     */
    int updateUserPlay(UserPlay userPlay);
}
