package com.banlv.service.impl;

import com.banlv.bean.ScenicManage;
import com.banlv.dao.IScenicManageDao;
import com.banlv.service.ScenicManageService;
import com.util.bean.PageBean;
import com.util.utils.GetSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScenicManageServiceImpl implements ScenicManageService {
    private static IScenicManageDao mapper;

    static {
        mapper = GetSqlSession.safeSqlSession().getMapper(IScenicManageDao.class);
    }
    @Override
    public List<ScenicManage> findAll() {
        return mapper.findAll();
    }

    @Override
    public PageBean<ScenicManage> findAllByPage(int currentPage, int rows) {
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
        List<ScenicManage> scenicManageList = mapper.findAllByPage(pageMap);
        return  new PageBean<>(totalCount,totalPage,scenicManageList,currentPage,rows);
    }


    @Override
    public PageBean<ScenicManage> findAllByPage() {
        return findAllByPage(0,5);
    }

    @Override
    public PageBean<ScenicManage> searchAllByPage(int currentPage, int rows, ScenicManage scenicManage) {
//        总记录数
        int totalCount = mapper.findSearchTotalCount(scenicManage);
        int totalPage = (totalCount % rows)== 0 ? (totalCount / rows): (totalCount / rows) + 1;
        if (currentPage > totalPage) currentPage = totalPage;
        if (currentPage<=0) currentPage = 1;

//        包装
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("start",(currentPage-1)*rows);
        pageMap.put("rows",rows);
//        根据页数查询
        List<ScenicManage> scenicManageList = mapper.searchAllByPage(pageMap,scenicManage);
        return  new PageBean<>(totalCount,totalPage,scenicManageList,currentPage,rows);
    }

    @Override
    public List<ScenicManage> searchAll(ScenicManage scenicManage) {
        return mapper.searchAll(scenicManage);
    }

    @Override
    public int addScenicManage(ScenicManage scenicManage) {
        int i = mapper.addScenicManage(scenicManage);
        cleanSqlSession();
        return i;
    }

    @Override
    public int deleteByScenicManageId(int scenicManageId) {
        int i = mapper.deleteByScenicManageId(scenicManageId);
        cleanSqlSession();
        return i;
    }

    @Override
    public int updateScenicManage(ScenicManage scenicManage) {
        int i = mapper.updateScenicManage(scenicManage);
        cleanSqlSession();
        return i;
    }

    public void cleanSqlSession (){
        GetSqlSession.cleanSqlSession();
    }

}
