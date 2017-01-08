package com.dengyuecang.www.controller.api.circle.model.comment;

/**
 * Created by acang on 2017/1/8.
 */
public class CommentRequest {

    private String momentId;

    public String timestamp;

    public String pageSize = "10";

    public String getMomentId() {
        return momentId;
    }

    public void setMomentId(String momentId) {
        this.momentId = momentId;
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
