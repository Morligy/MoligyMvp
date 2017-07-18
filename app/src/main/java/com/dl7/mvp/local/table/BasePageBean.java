package com.dl7.mvp.local.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by moligy on 2017/7/18.
 */
@Entity
public class BasePageBean {
    private int currentPage;
    private int totalPage;
    public int getTotalPage() {
        return this.totalPage;
    }
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getCurrentPage() {
        return this.currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    @Generated(hash = 1836092904)
    public BasePageBean(int currentPage, int totalPage) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
    }
    @Generated(hash = 288733549)
    public BasePageBean() {
    }
}
