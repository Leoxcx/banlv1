package com.banlv.dao;

import com.banlv.bean.Agent;

import java.util.List;
import java.util.Map;

public interface IAgentDao {
    /**
     * 查询所有
     * @return
     */
    List<Agent> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    List<Agent> findAllByPage(Map<String, Integer> pageMap);

    /**
     * 查询
     * @return
     */
    List<Agent> searchAllByPage(Map<String, Integer> pageMap, Agent agent);

    /**
     * 查询
     * @return
     */
    List<Agent> searchAll(Agent agent);

    /**
     * 查询总记录数
     * @return
     */
    int findTotalCount();

    /**
     * 查询总记录数
     * @return
     */
    int findSearchTotalCount(Agent agent);

    /**
     * 新增
     * @return
     */
    int addAgent(Agent agent);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByAgentId(int agent_id);

    /**
     * 更新
     * @return
     */
    int updateAgent(Agent agent);
}
