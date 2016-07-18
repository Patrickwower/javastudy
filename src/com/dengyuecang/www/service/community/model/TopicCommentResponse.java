package com.dengyuecang.www.service.community.model;

import com.dengyuecang.www.entity.Member;

import java.util.Date;

/**
 * Created by acang on 16/7/12.
 */
public class TopicCommentResponse {

    private String id;

    private String comment;

    private TopicDiscussant discussant;

    private Date ctime;

    private String timestamp;

    public TopicCommentResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public TopicDiscussant getDiscussant() {
        return discussant;
    }

    public void setDiscussant(TopicDiscussant discussant) {
        this.discussant = discussant;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
