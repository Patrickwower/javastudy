package com.dengyuecang.www.service.circle.model.comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acang on 2017/1/9.
 */
public class CommentResponse {

    private String commentId;

    private String content;

    private String ctime;

    private String timestamp;

    private CommentDiscussant discussant;

    private String canDelete;

    private List<Reply> replys = new ArrayList<Reply>();

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

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public CommentDiscussant getDiscussant() {
        return discussant;
    }

    public void setDiscussant(CommentDiscussant discussant) {
        this.discussant = discussant;
    }

    public String getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(String canDelete) {
        this.canDelete = canDelete;
    }

    public List<Reply> getReplys() {
        return replys;
    }

    public void setReplys(List<Reply> replys) {
        this.replys = replys;
    }
}
