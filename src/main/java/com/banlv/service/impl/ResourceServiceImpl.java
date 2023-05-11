package com.banlv.service.impl;

import com.banlv.bean.Resource;
import com.banlv.dao.IResourceDao;
import com.banlv.service.ResourceService;
import com.util.bean.PageBean;
import com.util.utils.GetSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceServiceImpl implements ResourceService {
    private static IResourceDao mapper;

    static {
        mapper = GetSqlSession.safeSqlSession().getMapper(IResourceDao.class);
    }
    @Override
    public List<Resource> findAll() {
        return mapper.findAll();
    }

    @Override
    public PageBean<Resource> findAllByPage(int currentPage, int rows) {
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
        List<Resource> resourceList = mapper.findAllByPage(pageMap);
        return  new PageBean<>(totalCount,totalPage,resourceList,currentPage,rows);
    }


    @Override
    public PageBean<Resource> findAllByPage() {
        return findAllByPage(0,5);
    }

    @Override
    public PageBean<Resource> searchAllByPage(int currentPage, int rows, Resource resource) {
//        总记录数
        int totalCount = mapper.findSearchTotalCount(resource);
        int totalPage = (totalCount % rows)== 0 ? (totalCount / rows): (totalCount / rows) + 1;
        if (currentPage > totalPage) currentPage = totalPage;
        if (currentPage<=0) currentPage = 1;

//        包装
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("start",(currentPage-1)*rows);
        pageMap.put("rows",rows);
//        根据页数查询
        List<Resource> resourceList = mapper.searchAllByPage(pageMap,resource);
        return  new PageBean<>(totalCount,totalPage,resourceList,currentPage,rows);
    }

    @Override
    public List<Resource> searchAll(Resource resource) {
        return mapper.searchAll(resource);
    }

    @Override
    public int addResource(Resource resource) {
        int i = mapper.addResource(resource);
        cleanSqlSession();
        return i;
    }

    @Override
    public int deleteByResourceId(int resourceId) {
        int i = mapper.deleteByResourceId(resourceId);
        cleanSqlSession();
        return i;
    }

    @Override
    public int updateResource(Resource resource) {
        int i = mapper.updateResource(resource);
        cleanSqlSession();
        return i;
    }

    public void cleanSqlSession (){
        GetSqlSession.cleanSqlSession();
    }

}
