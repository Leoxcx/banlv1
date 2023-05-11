package com.util.bean;

import java.util.List;

public class PageBean<T> {
    private int totalCount;//总记录数
    private int totalPage;//总页码 = 总记录数 % 每页显示条数 == 0 ? 总记录数/每页记录数 : 总记录数/每页显示条数 + 1
    private List<T> List;//每页数据
    private int currentPage;//当前页
    private int rows;//每页显示的条数

    public PageBean() {
    }

    public PageBean(int totalCount, int totalPage, java.util.List<T> list, int currentPage, int rows) {
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        List = list;
        this.currentPage = currentPage;
        this.rows = rows;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public java.util.List<T> getList() {
        return List;
    }

    public void setList(java.util.List<T> list) {
        List = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", List=" + List +
                ", currentPage=" + currentPage +
                ", rows=" + rows +
                '}';
    }
}
