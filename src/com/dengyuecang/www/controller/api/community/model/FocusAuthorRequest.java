package com.dengyuecang.www.controller.api.community.model;

/**
 * Created by acang on 16/7/14.
 */
public class FocusAuthorRequest {

    private String authorId;

    private String action;

    public String timestamp;

    public String pageSize = "10";

    public FocusAuthorRequest() {
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
