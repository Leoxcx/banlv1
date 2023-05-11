package com.banlv.service.impl;

import com.banlv.bean.UserArrive;
import com.banlv.dao.IUserArriveDao;
import com.banlv.service.UserArriveService;
import com.util.bean.PageBean;
import com.util.utils.GetSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserArriveServiceImpl implements UserArriveService {
    private static IUserArriveDao mapper;

    static {
        mapper = GetSqlSession.safeSqlSession().getMapper(IUserArriveDao.class);
    }
    @Override
    public List<UserArrive> findAll() {
        return mapper.findAll();
    }

    @Override
    public PageBean<UserArrive> findAllByPage(int currentPage, int rows) {
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
        List<UserArrive> userArriveList = mapper.findAllByPage(pageMap);
        return  new PageBean<>(totalCount,totalPage,userArriveList,currentPage,rows);
    }


    @Override
    public PageBean<UserArrive> findAllByPage() {
        return findAllByPage(0,5);
    }

    @Override
    public PageBean<UserArrive> searchAllByPage(int currentPage, int rows, UserArrive userArrive) {
//        总记录数
        int totalCount = mapper.findSearchTotalCount(userArrive);
        int totalPage = (totalCount % rows)== 0 ? (totalCount / rows): (totalCount / rows) + 1;
        if (currentPage > totalPage) currentPage = totalPage;
        if (currentPage<=0) currentPage = 1;

//        包装
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("start",(currentPage-1)*rows);
        pageMap.put("rows",rows);
//        根据页数查询
        List<UserArrive> userArriveList = mapper.searchAllByPage(pageMap,userArrive);
        return  new PageBean<>(totalCount,totalPage,userArriveList,currentPage,rows);
    }

    @Override
    public List<UserArrive> searchAll(UserArrive userArrive) {
        return mapper.searchAll(userArrive);
    }

    @Override
    public int addUserArrive(UserArrive userArrive) {
        int i = mapper.addUserArrive(userArrive);
        cleanSqlSession();
        return i;
    }

    @Override
    public int deleteByUserArriveId(int userArriveId) {
        int i = mapper.deleteByUserArriveId(userArriveId);
        cleanSqlSession();
        return i;
    }

    @Override
    public int updateUserArrive(UserArrive userArrive) {
        int i = mapper.updateUserArrive(userArrive);
        cleanSqlSession();
        return i;
    }

    public void cleanSqlSession (){
        GetSqlSession.cleanSqlSession();
    }

}
