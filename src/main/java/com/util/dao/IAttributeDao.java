package com.util.dao;

import com.util.bean.Attribute;

import java.util.List;
import java.util.Map;

public interface IAttributeDao {
    /**
     * 查询所有
     * @return
     */
    List<Attribute> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    List<Attribute> findAllByPage(Map<String, Integer> pageMap);

    /**
     * 查询
     * @return
     */
    List<Attribute> searchAllByPage(Map<String, Integer> pageMap, Attribute attribute);

    /**
     * 查询总记录数
     * @return
     */
    int findTotalCount();

    /**
     * 查询总记录数
     * @return
     */
    int findSearchTotalCount(Attribute attribute);

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
