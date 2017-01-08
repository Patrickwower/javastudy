package com.dengyuecang.www.controller.api.circle.model.comment;

/**
 * Created by acang on 2017/1/8.
 */
public class CommentAddRequest {

    private String content;

    private String momentId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMomentId() {
        return momentId;
    }

    public void setMomentId(String momentId) {
        this.momentId = momentId;
    }
}
