package com.banlv.dao;

import com.banlv.bean.UserArrive;

import java.util.List;
import java.util.Map;

public interface IUserArriveDao {
    /**
     * 查询所有
     * @return
     */
    List<UserArrive> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    List<UserArrive> findAllByPage(Map<String, Integer> pageMap);

    /**
     * 查询
     * @return
     */
    List<UserArrive> searchAllByPage(Map<String, Integer> pageMap, UserArrive userArrive);

    /**
     * 查询
     * @return
     */
    List<UserArrive> searchAll(UserArrive userArrive);

    /**
     * 查询总记录数
     * @return
     */
    int findTotalCount();

    /**
     * 查询总记录数
     * @return
     */
    int findSearchTotalCount(UserArrive userArrive);

    /**
     * 新增
     * @return
     */
    int addUserArrive(UserArrive userArrive);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByUserArriveId(int userArrive_id);

    /**
     * 更新
     * @return
     */
    int updateUserArrive(UserArrive userArrive);
}
