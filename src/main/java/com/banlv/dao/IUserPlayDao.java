package com.banlv.dao;

import com.banlv.bean.UserPlay;

import java.util.List;
import java.util.Map;

public interface IUserPlayDao {
    /**
     * 查询所有
     * @return
     */
    List<UserPlay> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    List<UserPlay> findAllByPage(Map<String, Integer> pageMap);

    /**
     * 查询
     * @return
     */
    List<UserPlay> searchAllByPage(Map<String, Integer> pageMap, UserPlay userPlay);

    /**
     * 查询
     * @return
     */
    List<UserPlay> searchAll(UserPlay userPlay);

    /**
     * 查询总记录数
     * @return
     */
    int findTotalCount();

    /**
     * 查询总记录数
     * @return
     */
    int findSearchTotalCount(UserPlay userPlay);

    /**
     * 新增
     * @return
     */
    int addUserPlay(UserPlay userPlay);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByUserPlayId(int userPlay_id);

    /**
     * 更新
     * @return
     */
    int updateUserPlay(UserPlay userPlay);
}
