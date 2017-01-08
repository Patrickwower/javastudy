package com.dengyuecang.www.controller.api.circle.model.comment.reply;

/**
 * Created by acang on 2017/1/9.
 */
public class ReplyAddRequest {

    private String commentId;

    private String content;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
