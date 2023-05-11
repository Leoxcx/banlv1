package com.banlv.service.impl;

import com.banlv.bean.ScenicZone_scenicZoneEntry;
import com.banlv.dao.IScenicZone_scenicZoneEntryDao;
import com.banlv.service.ScenicZone_scenicZoneEntryService;
import com.util.bean.PageBean;
import com.util.utils.GetSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScenicZone_scenicZoneEntryServiceImpl implements ScenicZone_scenicZoneEntryService {
    private static IScenicZone_scenicZoneEntryDao mapper;

    static {
        mapper = GetSqlSession.safeSqlSession().getMapper(IScenicZone_scenicZoneEntryDao.class);
    }
    @Override
    public List<ScenicZone_scenicZoneEntry> findAll() {
        return mapper.findAll();
    }

    @Override
    public PageBean<ScenicZone_scenicZoneEntry> findAllByPage(int currentPage, int rows) {
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
        List<ScenicZone_scenicZoneEntry> scenicZone_scenicZoneEntryList = mapper.findAllByPage(pageMap);
        return  new PageBean<>(totalCount,totalPage,scenicZone_scenicZoneEntryList,currentPage,rows);
    }


    @Override
    public PageBean<ScenicZone_scenicZoneEntry> findAllByPage() {
        return findAllByPage(0,5);
    }

    @Override
    public PageBean<ScenicZone_scenicZoneEntry> searchAllByPage(int currentPage, int rows, ScenicZone_scenicZoneEntry scenicZone_scenicZoneEntry) {
//        总记录数
        int totalCount = mapper.findSearchTotalCount(scenicZone_scenicZoneEntry);
        int totalPage = (totalCount % rows)== 0 ? (totalCount / rows): (totalCount / rows) + 1;
        if (currentPage > totalPage) currentPage = totalPage;
        if (currentPage<=0) currentPage = 1;

//        包装
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("start",(currentPage-1)*rows);
        pageMap.put("rows",rows);
//        根据页数查询
        List<ScenicZone_scenicZoneEntry> scenicZone_scenicZoneEntryList = mapper.searchAllByPage(pageMap,scenicZone_scenicZoneEntry);
        return  new PageBean<>(totalCount,totalPage,scenicZone_scenicZoneEntryList,currentPage,rows);
    }

    @Override
    public List<ScenicZone_scenicZoneEntry> searchAll(ScenicZone_scenicZoneEntry scenicZone_scenicZoneEntry) {
        return mapper.searchAll(scenicZone_scenicZoneEntry);
    }

    @Override
    public int addScenicZone_scenicZoneEntry(ScenicZone_scenicZoneEntry scenicZone_scenicZoneEntry) {
        int i = mapper.addScenicZone_scenicZoneEntry(scenicZone_scenicZoneEntry);
        cleanSqlSession();
        return i;
    }

    @Override
    public int deleteByScenicZone_scenicZoneEntryId(int scenicZone_scenicZoneEntryId) {
        int i = mapper.deleteByScenicZone_scenicZoneEntryId(scenicZone_scenicZoneEntryId);
        cleanSqlSession();
        return i;
    }

    @Override
    public int updateScenicZone_scenicZoneEntry(ScenicZone_scenicZoneEntry scenicZone_scenicZoneEntry) {
        int i = mapper.updateScenicZone_scenicZoneEntry(scenicZone_scenicZoneEntry);
        cleanSqlSession();
        return i;
    }

    public void cleanSqlSession (){
        GetSqlSession.cleanSqlSession();
    }

}
