package com.dengyuecang.www.controller.api.circle.model.follow;

/**
 * Created by Administrator on 2017/1/16.
 */
public class FollowRequest {

    private String pageSize = "10";

    private String timestamp;

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
