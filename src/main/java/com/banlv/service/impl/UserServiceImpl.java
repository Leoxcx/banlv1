package com.banlv.service.impl;

import com.banlv.bean.User;
import com.banlv.dao.IUserDao;
import com.banlv.service.UserService;
import com.util.bean.PageBean;
import com.util.utils.GetSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private static IUserDao mapper;

    static {
        mapper = GetSqlSession.safeSqlSession().getMapper(IUserDao.class);
    }
    @Override
    public List<User> findAll() {
        return mapper.findAll();
    }

    @Override
    public PageBean<User> findAllByPage(int currentPage, int rows) {
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
        List<User> userList = mapper.findAllByPage(pageMap);
        return  new PageBean<>(totalCount,totalPage,userList,currentPage,rows);
    }


    @Override
    public PageBean<User> findAllByPage() {
        return findAllByPage(0,5);
    }

    @Override
    public PageBean<User> searchAllByPage(int currentPage, int rows, User user) {
//        总记录数
        int totalCount = mapper.findSearchTotalCount(user);
        int totalPage = (totalCount % rows)== 0 ? (totalCount / rows): (totalCount / rows) + 1;
        if (currentPage > totalPage) currentPage = totalPage;
        if (currentPage<=0) currentPage = 1;

//        包装
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("start",(currentPage-1)*rows);
        pageMap.put("rows",rows);
//        根据页数查询
        List<User> userList = mapper.searchAllByPage(pageMap,user);
        return  new PageBean<>(totalCount,totalPage,userList,currentPage,rows);
    }

    @Override
    public List<User> searchAll(User user) {
        return mapper.searchAll(user);
    }

    @Override
    public int addUser(User user) {
        int i = mapper.addUser(user);
        cleanSqlSession();
        return i;
    }

    @Override
    public int deleteByUserId(int userId) {
        int i = mapper.deleteByUserId(userId);
        cleanSqlSession();
        return i;
    }

    @Override
    public int updateUser(User user) {
        int i = mapper.updateUser(user);
        cleanSqlSession();
        return i;
    }

    public void cleanSqlSession (){
        GetSqlSession.cleanSqlSession();
    }

}
