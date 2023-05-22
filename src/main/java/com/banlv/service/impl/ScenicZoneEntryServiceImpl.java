package com.banlv.service.impl;

import com.banlv.bean.ScenicZoneEntry;
import com.banlv.dao.IScenicZoneEntryDao;
import com.banlv.service.ScenicZoneEntryService;
import com.util.bean.PageBean;
import com.util.utils.GetSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScenicZoneEntryServiceImpl implements ScenicZoneEntryService {
    private static IScenicZoneEntryDao mapper;

    static {
        mapper = GetSqlSession.safeSqlSession().getMapper(IScenicZoneEntryDao.class);
    }
    @Override
    public List<ScenicZoneEntry> findAll() {
        return mapper.findAll();
    }

    @Override
    public PageBean<ScenicZoneEntry> findAllByPage(int currentPage, int rows) {
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
        List<ScenicZoneEntry> scenicZoneEntryList = mapper.findAllByPage(pageMap);
        return  new PageBean<>(totalCount,totalPage,scenicZoneEntryList,currentPage,rows);
    }


    @Override
    public PageBean<ScenicZoneEntry> findAllByPage() {
        return findAllByPage(0,5);
    }

    @Override
    public PageBean<ScenicZoneEntry> searchAllByPage(int currentPage, int rows, ScenicZoneEntry scenicZoneEntry) {
//        总记录数
        int totalCount = mapper.findSearchTotalCount(scenicZoneEntry);
        int totalPage = (totalCount % rows)== 0 ? (totalCount / rows): (totalCount / rows) + 1;
        if (currentPage > totalPage) currentPage = totalPage;
        if (currentPage<=0) currentPage = 1;

//        包装
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("start",(currentPage-1)*rows);
        pageMap.put("rows",rows);
//        根据页数查询
        List<ScenicZoneEntry> scenicZoneEntryList = mapper.searchAllByPage(pageMap,scenicZoneEntry);
        return  new PageBean<>(totalCount,totalPage,scenicZoneEntryList,currentPage,rows);
    }

    @Override
    public List<ScenicZoneEntry> searchAll(ScenicZoneEntry scenicZoneEntry) {
        return mapper.searchAll(scenicZoneEntry);
    }

    @Override
    public int addScenicZoneEntry(ScenicZoneEntry scenicZoneEntry) {
        int i = mapper.addScenicZoneEntry(scenicZoneEntry);
        cleanSqlSession();
        return i;
    }

    @Override
    public int deleteByScenicZoneEntryId(int scenicZoneEntryId) {
        int i = mapper.deleteByScenicZoneEntryId(scenicZoneEntryId);
        cleanSqlSession();
        return i;
    }

    @Override
    public int updateScenicZoneEntry(ScenicZoneEntry scenicZoneEntry) {
        int i = mapper.updateScenicZoneEntry(scenicZoneEntry);
        cleanSqlSession();
        return i;
    }

    public void cleanSqlSession (){
        GetSqlSession.cleanSqlSession();
    }

}
