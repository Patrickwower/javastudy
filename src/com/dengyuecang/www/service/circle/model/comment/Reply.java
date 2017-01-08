package com.dengyuecang.www.service.circle.model.comment;

import javax.lang.model.element.Name;

/**
 * Created by acang on 2017/1/9.
 */
public class Reply {

    private String replyId;

    private String content;

    private String discussantId;

    private String discussanName;

    private String atId;

    private String atName;

    //0不能删,1能删
    private String canDelete;

    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDiscussantId() {
        return discussantId;
    }

    public void setDiscussantId(String discussantId) {
        this.discussantId = discussantId;
    }

    public String getDiscussanName() {
        return discussanName;
    }

    public void setDiscussanName(String discussanName) {
        this.discussanName = discussanName;
    }

    public String getAtId() {
        return atId;
    }

    public void setAtId(String atId) {
        this.atId = atId;
    }

    public String getAtName() {
        return atName;
    }

    public void setAtName(String atName) {
        this.atName = atName;
    }

    public String getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(String canDelete) {
        this.canDelete = canDelete;
    }
}
