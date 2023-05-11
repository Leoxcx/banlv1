package com.banlv.service.impl;

import com.banlv.bean.ScenicZone_scenicSpot;
import com.banlv.dao.IScenicZone_scenicSpotDao;
import com.banlv.service.ScenicZone_scenicSpotService;
import com.util.bean.PageBean;
import com.util.utils.GetSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScenicZone_scenicSpotServiceImpl implements ScenicZone_scenicSpotService {
    private static IScenicZone_scenicSpotDao mapper;

    static {
        mapper = GetSqlSession.safeSqlSession().getMapper(IScenicZone_scenicSpotDao.class);
    }
    @Override
    public List<ScenicZone_scenicSpot> findAll() {
        return mapper.findAll();
    }

    @Override
    public PageBean<ScenicZone_scenicSpot> findAllByPage(int currentPage, int rows) {
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
        List<ScenicZone_scenicSpot> scenicZone_scenicSpotList = mapper.findAllByPage(pageMap);
        return  new PageBean<>(totalCount,totalPage,scenicZone_scenicSpotList,currentPage,rows);
    }


    @Override
    public PageBean<ScenicZone_scenicSpot> findAllByPage() {
        return findAllByPage(0,5);
    }

    @Override
    public PageBean<ScenicZone_scenicSpot> searchAllByPage(int currentPage, int rows, ScenicZone_scenicSpot scenicZone_scenicSpot) {
//        总记录数
        int totalCount = mapper.findSearchTotalCount(scenicZone_scenicSpot);
        int totalPage = (totalCount % rows)== 0 ? (totalCount / rows): (totalCount / rows) + 1;
        if (currentPage > totalPage) currentPage = totalPage;
        if (currentPage<=0) currentPage = 1;

//        包装
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("start",(currentPage-1)*rows);
        pageMap.put("rows",rows);
//        根据页数查询
        List<ScenicZone_scenicSpot> scenicZone_scenicSpotList = mapper.searchAllByPage(pageMap,scenicZone_scenicSpot);
        return  new PageBean<>(totalCount,totalPage,scenicZone_scenicSpotList,currentPage,rows);
    }

    @Override
    public List<ScenicZone_scenicSpot> searchAll(ScenicZone_scenicSpot scenicZone_scenicSpot) {
        return mapper.searchAll(scenicZone_scenicSpot);
    }

    @Override
    public int addScenicZone_scenicSpot(ScenicZone_scenicSpot scenicZone_scenicSpot) {
        int i = mapper.addScenicZone_scenicSpot(scenicZone_scenicSpot);
        cleanSqlSession();
        return i;
    }

    @Override
    public int deleteByScenicZone_scenicSpotId(int scenicZone_scenicSpotId) {
        int i = mapper.deleteByScenicZone_scenicSpotId(scenicZone_scenicSpotId);
        cleanSqlSession();
        return i;
    }

    @Override
    public int updateScenicZone_scenicSpot(ScenicZone_scenicSpot scenicZone_scenicSpot) {
        int i = mapper.updateScenicZone_scenicSpot(scenicZone_scenicSpot);
        cleanSqlSession();
        return i;
    }

    public void cleanSqlSession (){
        GetSqlSession.cleanSqlSession();
    }

}
