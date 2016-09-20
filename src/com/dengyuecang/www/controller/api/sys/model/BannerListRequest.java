package com.dengyuecang.www.controller.api.sys.model;

import com.longinf.lxcommon.dao.params.PageModel;

/**
 * Created by acang on 16/9/18.
 */
public class BannerListRequest {

    private PageModel pageModel;

    private String author;

    private String title;

    private String status;

    public PageModel getPageModel() {
        return pageModel;
    }

    public void setPageModel(PageModel pageModel) {
        this.pageModel = pageModel;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
