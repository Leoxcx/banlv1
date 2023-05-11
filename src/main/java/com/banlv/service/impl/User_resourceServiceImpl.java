package com.banlv.service.impl;

import com.banlv.bean.User_resource;
import com.banlv.dao.IUser_resourceDao;
import com.banlv.service.User_resourceService;
import com.util.bean.PageBean;
import com.util.utils.GetSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User_resourceServiceImpl implements User_resourceService {
    private static IUser_resourceDao mapper;

    static {
        mapper = GetSqlSession.safeSqlSession().getMapper(IUser_resourceDao.class);
    }
    @Override
    public List<User_resource> findAll() {
        return mapper.findAll();
    }

    @Override
    public PageBean<User_resource> findAllByPage(int currentPage, int rows) {
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
        List<User_resource> user_resourceList = mapper.findAllByPage(pageMap);
        return  new PageBean<>(totalCount,totalPage,user_resourceList,currentPage,rows);
    }


    @Override
    public PageBean<User_resource> findAllByPage() {
        return findAllByPage(0,5);
    }

    @Override
    public PageBean<User_resource> searchAllByPage(int currentPage, int rows, User_resource user_resource) {
//        总记录数
        int totalCount = mapper.findSearchTotalCount(user_resource);
        int totalPage = (totalCount % rows)== 0 ? (totalCount / rows): (totalCount / rows) + 1;
        if (currentPage > totalPage) currentPage = totalPage;
        if (currentPage<=0) currentPage = 1;

//        包装
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("start",(currentPage-1)*rows);
        pageMap.put("rows",rows);
//        根据页数查询
        List<User_resource> user_resourceList = mapper.searchAllByPage(pageMap,user_resource);
        return  new PageBean<>(totalCount,totalPage,user_resourceList,currentPage,rows);
    }

    @Override
    public List<User_resource> searchAll(User_resource user_resource) {
        return mapper.searchAll(user_resource);
    }

    @Override
    public int addUser_resource(User_resource user_resource) {
        int i = mapper.addUser_resource(user_resource);
        cleanSqlSession();
        return i;
    }

    @Override
    public int deleteByUser_resourceId(int user_resourceId) {
        int i = mapper.deleteByUser_resourceId(user_resourceId);
        cleanSqlSession();
        return i;
    }

    @Override
    public int updateUser_resource(User_resource user_resource) {
        int i = mapper.updateUser_resource(user_resource);
        cleanSqlSession();
        return i;
    }

    public void cleanSqlSession (){
        GetSqlSession.cleanSqlSession();
    }

}
