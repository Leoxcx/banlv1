package com.util.service.impl;

import com.util.bean.Attribute;
import com.util.bean.PageBean;
import com.util.dao.IAttributeDao;
import com.util.service.AttributeService;
import com.util.utils.GetSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttributeServiceImpl implements AttributeService {
    private static IAttributeDao mapper;

    static {
        mapper = GetSqlSession.getSqlSession().getMapper(IAttributeDao.class);
    }

    @Override
    public List<Attribute> findAll() {
        return mapper.findAll();
    }

    @Override
    public PageBean<Attribute> findAllByPage(int currentPage, int rows) {
//        总记录数
        int totalCount = mapper.findTotalCount();
        int totalPage = (totalCount % rows)== 0 ? (totalCount / rows): (totalCount / rows) + 1;
        if (currentPage<=0) currentPage = 1;
        if (currentPage>totalPage) currentPage = totalPage;
//        包装
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("start",(currentPage-1)*rows);
        pageMap.put("rows",rows);
//        根据页数查询
        List<Attribute> attributeList = mapper.findAllByPage(pageMap);
        return  new PageBean<>(totalCount,totalPage,attributeList,currentPage,rows);
    }

    @Override
    public PageBean<Attribute> searchAllByPage(int currentPage, int rows, Attribute attribute) {
//        总记录数
        int totalCount = mapper.findSearchTotalCount(attribute);
        int totalPage = (totalCount % rows)== 0 ? (totalCount / rows): (totalCount / rows) + 1;
        if (currentPage > totalPage) currentPage = totalPage;
        if (currentPage<=0) currentPage = 1;

//        包装
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("start",(currentPage-1)*rows);
        pageMap.put("rows",rows);
//        根据页数查询
        List<Attribute> attributeList = mapper.searchAllByPage(pageMap,attribute);
        return  new PageBean<>(totalCount,totalPage,attributeList,currentPage,rows);
    }

    @Override
    public PageBean<Attribute> findAllByPage() {
        return  findAllByPage(0,10);
    }

    @Override
    public PageBean<Attribute> searchAllByPage(Attribute attribute) {
        return  searchAllByPage(0,10,attribute);
    }

    @Override
    public List<Attribute> findByAttrTable(String attrTable) {
        return mapper.findByAttrTable(attrTable);
    }

    @Override
    public int addAttribute(Attribute attribute) {
        int i = mapper.addAttribute(attribute);
        cleanSqlSession();
        return i;
    }

    @Override
    public int deleteByAttrId(int attrId) {
        int i = mapper.deleteByAttrId(attrId);
        cleanSqlSession();
        return i;
    }

    @Override
    public int updateAttribute(Attribute attribute) {
        int i = mapper.updateAttribute(attribute);
        cleanSqlSession();
        return i;
    }

    public void cleanSqlSession (){
        GetSqlSession.cleanSqlSession();
    }
}
