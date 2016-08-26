package com.dengyuecang.www.controller.api.community.model;

/**
 * Created by acang on 16/7/12.
 */
public class CommentRequest {

    private String articleId;

    private String topicId;

    private String comment;

    private String commentId;

    private String atMember;

    public CommentRequest() {
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
