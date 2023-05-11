package com.banlv.service.impl;

import com.banlv.bean.Agent;
import com.banlv.dao.IAgentDao;
import com.banlv.service.AgentService;
import com.util.bean.PageBean;
import com.util.utils.GetSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgentServiceImpl implements AgentService {
    private static IAgentDao mapper;

    static {
        mapper = GetSqlSession.safeSqlSession().getMapper(IAgentDao.class);
    }
    @Override
    public List<Agent> findAll() {
        return mapper.findAll();
    }

    @Override
    public PageBean<Agent> findAllByPage(int currentPage, int rows) {
//        总记录数
        int totalCount = mapper.findTotalCount();
        int totalPage = (totalCount % rows)== 0 ? (totalCount / rows): (totalCount / rows) + 1;
        if (currentPage>totalPage) currentPage = totalPage;
        if (currentPage<=0) currentPage = 1;
//        包装
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("start",(currentPage-1)*rows);
        pageMap.put("rows",rows);
//        根据页数查询
        List<Agent> agentList = mapper.findAllByPage(pageMap);
        return  new PageBean<>(totalCount,totalPage,agentList,currentPage,rows);
    }


    @Override
    public PageBean<Agent> findAllByPage() {
        return findAllByPage(0,5);
    }

    @Override
    public PageBean<Agent> searchAllByPage(int currentPage, int rows, Agent agent) {
//        总记录数
        int totalCount = mapper.findSearchTotalCount(agent);
        int totalPage = (totalCount % rows)== 0 ? (totalCount / rows): (totalCount / rows) + 1;
        if (currentPage > totalPage) currentPage = totalPage;
        if (currentPage<=0) currentPage = 1;

//        包装
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("start",(currentPage-1)*rows);
        pageMap.put("rows",rows);
//        根据页数查询
        List<Agent> agentList = mapper.searchAllByPage(pageMap,agent);
        return  new PageBean<>(totalCount,totalPage,agentList,currentPage,rows);
    }

    @Override
    public List<Agent> searchAll(Agent agent) {
        return mapper.searchAll(agent);
    }

    @Override
    public int addAgent(Agent agent) {
        int i = mapper.addAgent(agent);
        cleanSqlSession();
        return i;
    }

    @Override
    public int deleteByAgentId(int agentId) {
        int i = mapper.deleteByAgentId(agentId);
        cleanSqlSession();
        return i;
    }

    @Override
    public int updateAgent(Agent agent) {
        int i = mapper.updateAgent(agent);
        cleanSqlSession();
        return i;
    }

    public void cleanSqlSession (){
        GetSqlSession.cleanSqlSession();
    }

}
