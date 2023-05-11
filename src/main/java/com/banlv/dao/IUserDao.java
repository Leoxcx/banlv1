package com.banlv.dao;

import com.banlv.bean.User;

import java.util.List;
import java.util.Map;

public interface IUserDao {
    /**
     * 查询所有
     * @return
     */
    List<User> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    List<User> findAllByPage(Map<String, Integer> pageMap);

    /**
     * 查询
     * @return
     */
    List<User> searchAllByPage(Map<String, Integer> pageMap, User user);

    /**
     * 查询
     * @return
     */
    List<User> searchAll(User user);

    /**
     * 查询总记录数
     * @return
     */
    int findTotalCount();

    /**
     * 查询总记录数
     * @return
     */
    int findSearchTotalCount(User user);

    /**
     * 新增
     * @return
     */
    int addUser(User user);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByUserId(int user_id);

    /**
     * 更新
     * @return
     */
    int updateUser(User user);
}
