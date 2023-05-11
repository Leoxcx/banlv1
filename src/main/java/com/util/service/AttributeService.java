package com.util.service;

import com.util.bean.Attribute;
import com.util.bean.PageBean;

import java.util.List;

public interface AttributeService {
    /**
     * 查询所有
     * @return
     */
    List<Attribute> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    PageBean<Attribute> findAllByPage(int currentPage, int rows);

    /**
     * 查询
     * @return
     */
    PageBean<Attribute> searchAllByPage(int currentPage, int rows, Attribute attribute);

    /**
     * 通过页面查询所有
     * @return
     */
    PageBean<Attribute> findAllByPage();

    /**
     * 查询
     * @return
     */
    PageBean<Attribute> searchAllByPage(Attribute attribute);

    /**
     * 根据表名查询
     * @return
     */
    List<Attribute> findByAttrTable(String attrTable);

    /**
     * 新增
     * @return
     */
    int addAttribute(Attribute attribute);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByAttrId(int attrId);

    /**
     * 更新
     * @return
     */
    int updateAttribute(Attribute attribute);
}
