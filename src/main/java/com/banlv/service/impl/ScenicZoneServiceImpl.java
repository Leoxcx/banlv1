package com.banlv.service.impl;

import com.banlv.bean.ScenicZone;
import com.banlv.dao.IScenicZoneDao;
import com.banlv.service.ScenicZoneService;
import com.util.bean.PageBean;
import com.util.utils.GetSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScenicZoneServiceImpl implements ScenicZoneService {
    private static IScenicZoneDao mapper;

    static {
        mapper = GetSqlSession.safeSqlSession().getMapper(IScenicZoneDao.class);
    }
    @Override
    public List<ScenicZone> findAll() {
        return mapper.findAll();
    }

    @Override
    public PageBean<ScenicZone> findAllByPage(int currentPage, int rows) {
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
        List<ScenicZone> scenicZoneList = mapper.findAllByPage(pageMap);
        return  new PageBean<>(totalCount,totalPage,scenicZoneList,currentPage,rows);
    }


    @Override
    public PageBean<ScenicZone> findAllByPage() {
        return findAllByPage(0,5);
    }

    @Override
    public PageBean<ScenicZone> searchAllByPage(int currentPage, int rows, ScenicZone scenicZone) {
//        总记录数
        int totalCount = mapper.findSearchTotalCount(scenicZone);
        int totalPage = (totalCount % rows)== 0 ? (totalCount / rows): (totalCount / rows) + 1;
        if (currentPage > totalPage) currentPage = totalPage;
        if (currentPage<=0) currentPage = 1;

//        包装
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("start",(currentPage-1)*rows);
        pageMap.put("rows",rows);
//        根据页数查询
        List<ScenicZone> scenicZoneList = mapper.searchAllByPage(pageMap,scenicZone);
        return  new PageBean<>(totalCount,totalPage,scenicZoneList,currentPage,rows);
    }

    @Override
    public List<ScenicZone> searchAll(ScenicZone scenicZone) {
        return mapper.searchAll(scenicZone);
    }

    @Override
    public int addScenicZone(ScenicZone scenicZone) {
        int i = mapper.addScenicZone(scenicZone);
        cleanSqlSession();
        return i;
    }

    @Override
    public int deleteByScenicZoneId(int scenicZoneId) {
        int i = mapper.deleteByScenicZoneId(scenicZoneId);
        cleanSqlSession();
        return i;
    }

    @Override
    public int updateScenicZone(ScenicZone scenicZone) {
        int i = mapper.updateScenicZone(scenicZone);
        cleanSqlSession();
        return i;
    }

    public void cleanSqlSession (){
        GetSqlSession.cleanSqlSession();
    }

}
