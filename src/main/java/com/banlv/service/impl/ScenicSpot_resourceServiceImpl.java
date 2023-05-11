package com.banlv.service.impl;

import com.banlv.bean.ScenicSpot_resource;
import com.banlv.dao.IScenicSpot_resourceDao;
import com.banlv.service.ScenicSpot_resourceService;
import com.util.bean.PageBean;
import com.util.utils.GetSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScenicSpot_resourceServiceImpl implements ScenicSpot_resourceService {
    private static IScenicSpot_resourceDao mapper;

    static {
        mapper = GetSqlSession.safeSqlSession().getMapper(IScenicSpot_resourceDao.class);
    }
    @Override
    public List<ScenicSpot_resource> findAll() {
        return mapper.findAll();
    }

    @Override
    public PageBean<ScenicSpot_resource> findAllByPage(int currentPage, int rows) {
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
        List<ScenicSpot_resource> scenicSpot_resourceList = mapper.findAllByPage(pageMap);
        return  new PageBean<>(totalCount,totalPage,scenicSpot_resourceList,currentPage,rows);
    }


    @Override
    public PageBean<ScenicSpot_resource> findAllByPage() {
        return findAllByPage(0,5);
    }

    @Override
    public PageBean<ScenicSpot_resource> searchAllByPage(int currentPage, int rows, ScenicSpot_resource scenicSpot_resource) {
//        总记录数
        int totalCount = mapper.findSearchTotalCount(scenicSpot_resource);
        int totalPage = (totalCount % rows)== 0 ? (totalCount / rows): (totalCount / rows) + 1;
        if (currentPage > totalPage) currentPage = totalPage;
        if (currentPage<=0) currentPage = 1;

//        包装
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("start",(currentPage-1)*rows);
        pageMap.put("rows",rows);
//        根据页数查询
        List<ScenicSpot_resource> scenicSpot_resourceList = mapper.searchAllByPage(pageMap,scenicSpot_resource);
        return  new PageBean<>(totalCount,totalPage,scenicSpot_resourceList,currentPage,rows);
    }

    @Override
    public List<ScenicSpot_resource> searchAll(ScenicSpot_resource scenicSpot_resource) {
        return mapper.searchAll(scenicSpot_resource);
    }

    @Override
    public List<ScenicSpot_resource> searchBySpotId(ScenicSpot_resource scenicSpot_resource) {
        return mapper.searchBySpotId(scenicSpot_resource);
    }

    @Override
    public int addScenicSpot_resource(ScenicSpot_resource scenicSpot_resource) {
        int i = mapper.addScenicSpot_resource(scenicSpot_resource);
        cleanSqlSession();
        return i;
    }

    @Override
    public int deleteByScenicSpot_resourceId(int scenicSpot_resourceId) {
        int i = mapper.deleteByScenicSpot_resourceId(scenicSpot_resourceId);
        cleanSqlSession();
        return i;
    }

    @Override
    public int updateScenicSpot_resource(ScenicSpot_resource scenicSpot_resource) {
        int i = mapper.updateScenicSpot_resource(scenicSpot_resource);
        cleanSqlSession();
        return i;
    }

    public void cleanSqlSession (){
        GetSqlSession.cleanSqlSession();
    }

}
