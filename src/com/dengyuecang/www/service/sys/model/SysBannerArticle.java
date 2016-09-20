package com.dengyuecang.www.service.sys.model;

import com.dengyuecang.www.service.community.model.IndexArticle;

import java.util.Date;

/**
 * Created by acang on 16/9/13.
 */
public class SysBannerArticle {

    private IndexArticle indexArticle;

    private String id;

    private Date ctime;

    private String sort;

    private String status;

    private String statusName;

    public IndexArticle getIndexArticle() {
        return indexArticle;
    }

    public void setIndexArticle(IndexArticle indexArticle) {
        this.indexArticle = indexArticle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
