package com.banlv.service;

import com.banlv.bean.Agent;
import com.util.bean.PageBean;

import java.util.List;

public interface AgentService {
    /**
     * 查询所有
     * @return
     */
    List<Agent> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    PageBean<Agent> findAllByPage(int currentPage, int rows);

    /**
     * 通过页面查询所有(空参)
     * @return
     */
    PageBean<Agent> findAllByPage();

    /**
     * 查询
     * @return
     */
    PageBean<Agent> searchAllByPage(int currentPage, int rows,Agent agent);

    /**
     * 查询所有
     * @return
     */
    List<Agent> searchAll(Agent agent);

    /**
     * 新增
     * @return
     */
    int addAgent(Agent agent);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByAgentId(int agentId);

    /**
     * 更新
     * @return
     */
    int updateAgent(Agent agent);
}
