package com.dengyuecang.www.controller.api.community.model;

/**
 * Created by acang on 16/7/12.
 */
public class ArticleCommentRequest {

    public String timestamp;

    public String pageSize = "10";

    public String articleId = "";

    public String commentId = "";

    public ArticleCommentRequest() {
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

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
}
