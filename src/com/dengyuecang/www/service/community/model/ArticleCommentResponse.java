package com.dengyuecang.www.service.community.model;

import java.util.Date;

/**
 * Created by acang on 16/7/12.
 */
public class ArticleCommentResponse {

    private String id;

    private String comment;

    private ArticleDiscussant discussant;

    private Date ctime;

    private String timestamp;

    public ArticleCommentResponse() {
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

    public ArticleDiscussant getDiscussant() {
        return discussant;
    }

    public void setDiscussant(ArticleDiscussant discussant) {
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
