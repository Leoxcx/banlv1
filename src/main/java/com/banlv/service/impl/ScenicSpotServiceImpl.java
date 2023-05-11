package com.banlv.service.impl;

import com.banlv.bean.ScenicSpot;
import com.banlv.dao.IScenicSpotDao;
import com.banlv.service.ScenicSpotService;
import com.util.bean.PageBean;
import com.util.utils.GetSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScenicSpotServiceImpl implements ScenicSpotService {
    private static IScenicSpotDao mapper;

    static {
        mapper = GetSqlSession.safeSqlSession().getMapper(IScenicSpotDao.class);
    }
    @Override
    public List<ScenicSpot> findAll() {
        return mapper.findAll();
    }

    @Override
    public PageBean<ScenicSpot> findAllByPage(int currentPage, int rows) {
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
        List<ScenicSpot> scenicSpotList = mapper.findAllByPage(pageMap);
        return  new PageBean<>(totalCount,totalPage,scenicSpotList,currentPage,rows);
    }


    @Override
    public PageBean<ScenicSpot> findAllByPage() {
        return findAllByPage(0,5);
    }

    @Override
    public PageBean<ScenicSpot> searchAllByPage(int currentPage, int rows, ScenicSpot scenicSpot) {
//        总记录数
        int totalCount = mapper.findSearchTotalCount(scenicSpot);
        int totalPage = (totalCount % rows)== 0 ? (totalCount / rows): (totalCount / rows) + 1;
        if (currentPage > totalPage) currentPage = totalPage;
        if (currentPage<=0) currentPage = 1;

//        包装
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("start",(currentPage-1)*rows);
        pageMap.put("rows",rows);
//        根据页数查询
        List<ScenicSpot> scenicSpotList = mapper.searchAllByPage(pageMap,scenicSpot);
        return  new PageBean<>(totalCount,totalPage,scenicSpotList,currentPage,rows);
    }

    @Override
    public List<ScenicSpot> searchAll(ScenicSpot scenicSpot) {
        return mapper.searchAll(scenicSpot);
    }

    @Override
    public int addScenicSpot(ScenicSpot scenicSpot) {
        int i = mapper.addScenicSpot(scenicSpot);
        cleanSqlSession();
        return i;
    }

    @Override
    public int deleteByScenicSpotId(int scenicSpotId) {
        int i = mapper.deleteByScenicSpotId(scenicSpotId);
        cleanSqlSession();
        return i;
    }

    @Override
    public int updateScenicSpot(ScenicSpot scenicSpot) {
        int i = mapper.updateScenicSpot(scenicSpot);
        cleanSqlSession();
        return i;
    }

    public void cleanSqlSession (){
        GetSqlSession.cleanSqlSession();
    }

}
