package com.banlv.service;

import com.banlv.bean.User;
import com.util.bean.PageBean;

import java.util.List;

public interface UserService {
    /**
     * 查询所有
     * @return
     */
    List<User> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    PageBean<User> findAllByPage(int currentPage, int rows);

    /**
     * 通过页面查询所有(空参)
     * @return
     */
    PageBean<User> findAllByPage();

    /**
     * 查询
     * @return
     */
    PageBean<User> searchAllByPage(int currentPage, int rows,User user);

    /**
     * 查询所有
     * @return
     */
    List<User> searchAll(User user);

    /**
     * 新增
     * @return
     */
    int addUser(User user);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByUserId(int userId);

    /**
     * 更新
     * @return
     */
    int updateUser(User user);
}
