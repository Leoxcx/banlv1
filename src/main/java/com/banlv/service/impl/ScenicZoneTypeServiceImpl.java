package com.banlv.service.impl;

import com.banlv.bean.ScenicZoneType;
import com.banlv.dao.IScenicZoneTypeDao;
import com.banlv.service.ScenicZoneTypeService;
import com.util.bean.PageBean;
import com.util.utils.GetSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScenicZoneTypeServiceImpl implements ScenicZoneTypeService {
    private static IScenicZoneTypeDao mapper;

    static {
        mapper = GetSqlSession.safeSqlSession().getMapper(IScenicZoneTypeDao.class);
    }
    @Override
    public List<ScenicZoneType> findAll() {
        return mapper.findAll();
    }

    @Override
    public PageBean<ScenicZoneType> findAllByPage(int currentPage, int rows) {
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
        List<ScenicZoneType> scenicZoneTypeList = mapper.findAllByPage(pageMap);
        return  new PageBean<>(totalCount,totalPage,scenicZoneTypeList,currentPage,rows);
    }


    @Override
    public PageBean<ScenicZoneType> findAllByPage() {
        return findAllByPage(0,5);
    }

    @Override
    public PageBean<ScenicZoneType> searchAllByPage(int currentPage, int rows, ScenicZoneType scenicZoneType) {
//        总记录数
        int totalCount = mapper.findSearchTotalCount(scenicZoneType);
        int totalPage = (totalCount % rows)== 0 ? (totalCount / rows): (totalCount / rows) + 1;
        if (currentPage > totalPage) currentPage = totalPage;
        if (currentPage<=0) currentPage = 1;

//        包装
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("start",(currentPage-1)*rows);
        pageMap.put("rows",rows);
//        根据页数查询
        List<ScenicZoneType> scenicZoneTypeList = mapper.searchAllByPage(pageMap,scenicZoneType);
        return  new PageBean<>(totalCount,totalPage,scenicZoneTypeList,currentPage,rows);
    }

    @Override
    public List<ScenicZoneType> searchAll(ScenicZoneType scenicZoneType) {
        return mapper.searchAll(scenicZoneType);
    }

    @Override
    public int addScenicZoneType(ScenicZoneType scenicZoneType) {
        int i = mapper.addScenicZoneType(scenicZoneType);
        cleanSqlSession();
        return i;
    }

    @Override
    public int deleteByScenicZoneTypeId(int scenicZoneTypeId) {
        int i = mapper.deleteByScenicZoneTypeId(scenicZoneTypeId);
        cleanSqlSession();
        return i;
    }

    @Override
    public int updateScenicZoneType(ScenicZoneType scenicZoneType) {
        int i = mapper.updateScenicZoneType(scenicZoneType);
        cleanSqlSession();
        return i;
    }

    public void cleanSqlSession (){
        GetSqlSession.cleanSqlSession();
    }

}
