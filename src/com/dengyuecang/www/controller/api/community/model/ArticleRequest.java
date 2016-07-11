package com.dengyuecang.www.controller.api.community.model;

/**
 * Created by acang on 16/7/8.
 */
public class ArticleRequest {

    public String timestamp;

    public String pageSize = "10";

    public ArticleRequest() {
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
