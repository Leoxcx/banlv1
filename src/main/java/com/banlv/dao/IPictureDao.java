package com.banlv.dao;

import com.banlv.bean.Picture;

import java.util.List;
import java.util.Map;

public interface IPictureDao {
    /**
     * 查询所有
     * @return
     */
    List<Picture> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    List<Picture> findAllByPage(Map<String, Integer> pageMap);

    /**
     * 查询
     * @return
     */
    List<Picture> searchAllByPage(Map<String, Integer> pageMap, Picture picture);

    /**
     * 查询
     * @return
     */
    List<Picture> searchAll(Picture picture);

    /**
     * 查询总记录数
     * @return
     */
    int findTotalCount();

    /**
     * 查询总记录数
     * @return
     */
    int findSearchTotalCount(Picture picture);

    /**
     * 新增
     * @return
     */
    int addPicture(Picture picture);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByPictureId(int picture_id);

    /**
     * 更新
     * @return
     */
    int updatePicture(Picture picture);
}
