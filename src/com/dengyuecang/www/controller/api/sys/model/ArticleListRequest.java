package com.dengyuecang.www.controller.api.sys.model;

import com.longinf.lxcommon.dao.params.PageModel;

/**
 * Created by acang on 16/9/7.
 */
public class ArticleListRequest {

    private PageModel pageModel = new PageModel();

    private String title;

    private String author;

    private String stime;

    private String etime;

    private String status;

    public PageModel getPageModel() {
        return pageModel;
    }

    public void setPageModel(PageModel pageModel) {
        this.pageModel = pageModel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
