package com.dengyuecang.www.controller.api.sys.model;

import com.longinf.lxcommon.dao.params.PageModel;

/**
 * Created by acang on 16/9/13.
 */
public class IndexListRequest {

    private PageModel pageModel = new PageModel();

    private String title;

    private String author;

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
}
