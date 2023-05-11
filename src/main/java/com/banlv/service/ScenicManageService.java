package com.banlv.service;

import com.banlv.bean.ScenicManage;
import com.util.bean.PageBean;

import java.util.List;

public interface ScenicManageService {
    /**
     * 查询所有
     * @return
     */
    List<ScenicManage> findAll();

    /**
     * 通过页面查询所有
     * @return
     */
    PageBean<ScenicManage> findAllByPage(int currentPage, int rows);

    /**
     * 通过页面查询所有(空参)
     * @return
     */
    PageBean<ScenicManage> findAllByPage();

    /**
     * 查询
     * @return
     */
    PageBean<ScenicManage> searchAllByPage(int currentPage, int rows,ScenicManage scenicManage);

    /**
     * 查询所有
     * @return
     */
    List<ScenicManage> searchAll(ScenicManage scenicManage);

    /**
     * 新增
     * @return
     */
    int addScenicManage(ScenicManage scenicManage);

    /**
     * 通过Id删除
     * @return
     */
    int deleteByScenicManageId(int scenicManageId);

    /**
     * 更新
     * @return
     */
    int updateScenicManage(ScenicManage scenicManage);
}
