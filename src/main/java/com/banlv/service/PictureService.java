package com.banlv.service;

import com.banlv.bean.Picture;
import com.util.bean.PageBean;

import java.util.List;

public interface PictureService {
    /**
     * 查询所有
     * @return
     */
    List<Picture> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    PageBean<Picture> findAllByPage(int currentPage, int rows);

    /**
     * 通过页面查询所有(空参)
     * @return
     */
    PageBean<Picture> findAllByPage();

    /**
     * 查询
     * @return
     */
    PageBean<Picture> searchAllByPage(int currentPage, int rows,Picture picture);

    /**
     * 查询所有
     * @return
     */
    List<Picture> searchAll(Picture picture);

    /**
     * 新增
     * @return
     */
    int addPicture(Picture picture);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByPictureId(int pictureId);

    /**
     * 更新
     * @return
     */
    int updatePicture(Picture picture);
}
