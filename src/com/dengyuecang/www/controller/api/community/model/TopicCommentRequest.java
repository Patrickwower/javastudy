package com.dengyuecang.www.controller.api.community.model;

/**
 * Created by acang on 16/7/12.
 */
public class TopicCommentRequest {

    public String timestamp;

    public String pageSize = "10";

    public String topicId = "";

    public TopicCommentRequest() {
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

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }
}
