package com.dengyuecang.www.service.sys.model;

import com.dengyuecang.www.service.community.model.IndexArticle;

import java.util.Date;

/**
 * Created by acang on 16/9/13.
 */
public class SysIndexArticle {

    private IndexArticle indexArticle;

    private String id;

    private Date index_time;

    private int sort;

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

    public Date getIndex_time() {
        return index_time;
    }

    public void setIndex_time(Date index_time) {
        this.index_time = index_time;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
