package com.banlv.service.impl;

import com.banlv.bean.UserPlay;
import com.banlv.dao.IUserPlayDao;
import com.banlv.service.UserPlayService;
import com.util.bean.PageBean;
import com.util.utils.GetSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserPlayServiceImpl implements UserPlayService {
    private static IUserPlayDao mapper;

    static {
        mapper = GetSqlSession.safeSqlSession().getMapper(IUserPlayDao.class);
    }
    @Override
    public List<UserPlay> findAll() {
        return mapper.findAll();
    }

    @Override
    public PageBean<UserPlay> findAllByPage(int currentPage, int rows) {
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
        List<UserPlay> userPlayList = mapper.findAllByPage(pageMap);
        return  new PageBean<>(totalCount,totalPage,userPlayList,currentPage,rows);
    }


    @Override
    public PageBean<UserPlay> findAllByPage() {
        return findAllByPage(0,5);
    }

    @Override
    public PageBean<UserPlay> searchAllByPage(int currentPage, int rows, UserPlay userPlay) {
//        总记录数
        int totalCount = mapper.findSearchTotalCount(userPlay);
        int totalPage = (totalCount % rows)== 0 ? (totalCount / rows): (totalCount / rows) + 1;
        if (currentPage > totalPage) currentPage = totalPage;
        if (currentPage<=0) currentPage = 1;

//        包装
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("start",(currentPage-1)*rows);
        pageMap.put("rows",rows);
//        根据页数查询
        List<UserPlay> userPlayList = mapper.searchAllByPage(pageMap,userPlay);
        return  new PageBean<>(totalCount,totalPage,userPlayList,currentPage,rows);
    }

    @Override
    public List<UserPlay> searchAll(UserPlay userPlay) {
        return mapper.searchAll(userPlay);
    }

    @Override
    public int addUserPlay(UserPlay userPlay) {
        int i = mapper.addUserPlay(userPlay);
        cleanSqlSession();
        return i;
    }

    @Override
    public int deleteByUserPlayId(int userPlayId) {
        int i = mapper.deleteByUserPlayId(userPlayId);
        cleanSqlSession();
        return i;
    }

    @Override
    public int updateUserPlay(UserPlay userPlay) {
        int i = mapper.updateUserPlay(userPlay);
        cleanSqlSession();
        return i;
    }

    public void cleanSqlSession (){
        GetSqlSession.cleanSqlSession();
    }

}
