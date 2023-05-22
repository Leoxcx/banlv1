package com.banlv.service.impl;

import com.banlv.bean.Picture;
import com.banlv.dao.IPictureDao;
import com.banlv.service.PictureService;
import com.util.bean.PageBean;
import com.util.utils.GetSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PictureServiceImpl implements PictureService {
    private static IPictureDao mapper;

    static {
        mapper = GetSqlSession.safeSqlSession().getMapper(IPictureDao.class);
    }
    @Override
    public List<Picture> findAll() {
        return mapper.findAll();
    }

    @Override
    public PageBean<Picture> findAllByPage(int currentPage, int rows) {
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
        List<Picture> pictureList = mapper.findAllByPage(pageMap);
        return  new PageBean<>(totalCount,totalPage,pictureList,currentPage,rows);
    }


    @Override
    public PageBean<Picture> findAllByPage() {
        return findAllByPage(0,5);
    }

    @Override
    public PageBean<Picture> searchAllByPage(int currentPage, int rows, Picture picture) {
//        总记录数
        int totalCount = mapper.findSearchTotalCount(picture);
        int totalPage = (totalCount % rows)== 0 ? (totalCount / rows): (totalCount / rows) + 1;
        if (currentPage > totalPage) currentPage = totalPage;
        if (currentPage<=0) currentPage = 1;

//        包装
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("start",(currentPage-1)*rows);
        pageMap.put("rows",rows);
//        根据页数查询
        List<Picture> pictureList = mapper.searchAllByPage(pageMap,picture);
        return  new PageBean<>(totalCount,totalPage,pictureList,currentPage,rows);
    }

    @Override
    public List<Picture> searchAll(Picture picture) {
        return mapper.searchAll(picture);
    }

    @Override
    public int addPicture(Picture picture) {
        int i = mapper.addPicture(picture);
        cleanSqlSession();
        return i;
    }

    @Override
    public int deleteByPictureId(int pictureId) {
        int i = mapper.deleteByPictureId(pictureId);
        cleanSqlSession();
        return i;
    }

    @Override
    public int updatePicture(Picture picture) {
        int i = mapper.updatePicture(picture);
        cleanSqlSession();
        return i;
    }

    public void cleanSqlSession (){
        GetSqlSession.cleanSqlSession();
    }

}
