package com.banlv.service;

import com.banlv.bean.UserArrive;
import com.util.bean.PageBean;

import java.util.List;

public interface UserArriveService {
    /**
     * 查询所有
     * @return
     */
    List<UserArrive> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    PageBean<UserArrive> findAllByPage(int currentPage, int rows);

    /**
     * 通过页面查询所有(空参)
     * @return
     */
    PageBean<UserArrive> findAllByPage();

    /**
     * 查询
     * @return
     */
    PageBean<UserArrive> searchAllByPage(int currentPage, int rows,UserArrive userArrive);

    /**
     * 查询所有
     * @return
     */
    List<UserArrive> searchAll(UserArrive userArrive);

    /**
     * 新增
     * @return
     */
    int addUserArrive(UserArrive userArrive);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByUserArriveId(int userArriveId);

    /**
     * 更新
     * @return
     */
    int updateUserArrive(UserArrive userArrive);
}
