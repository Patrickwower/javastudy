package com.dengyuecang.www.controller.api.circle.model.follow;

/**
 * Created by Administrator on 2017/1/16.
 */
public class FollowRequest {

    private String follow_id;

    private String followed_id;

    private String pageSize = "10";

    private String timestamp;

    public String getFollow_id() {
        return follow_id;
    }

    public void setFollow_id(String follow_id) {
        this.follow_id = follow_id;
    }

    public String getFollowed_id() {
        return followed_id;
    }

    public void setFollowed_id(String followed_id) {
        this.followed_id = followed_id;
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
