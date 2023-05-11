package com.banlv.dao;

import com.banlv.bean.User_resource;

import java.util.List;
import java.util.Map;

public interface IUser_resourceDao {
    /**
     * 查询所有
     * @return
     */
    List<User_resource> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    List<User_resource> findAllByPage(Map<String, Integer> pageMap);

    /**
     * 查询
     * @return
     */
    List<User_resource> searchAllByPage(Map<String, Integer> pageMap, User_resource user_resource);

    /**
     * 查询
     * @return
     */
    List<User_resource> searchAll(User_resource user_resource);

    /**
     * 查询总记录数
     * @return
     */
    int findTotalCount();

    /**
     * 查询总记录数
     * @return
     */
    int findSearchTotalCount(User_resource user_resource);

    /**
     * 新增
     * @return
     */
    int addUser_resource(User_resource user_resource);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByUser_resourceId(int user_resource_id);

    /**
     * 更新
     * @return
     */
    int updateUser_resource(User_resource user_resource);
}
