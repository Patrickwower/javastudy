package com.dengyuecang.www.service.community.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by acang on 16/7/12.
 */
public class ArticleCommentResponse {

    private String id;

    private String comment;

    private ArticleDiscussant discussant;

    private Date ctime;

    private String timestamp;

    private List<ArticleCommentAtMember> atMembers = new ArrayList<ArticleCommentAtMember>();

    //0是无,1是有回复列表,可点入查看
    private String ifHaveList = "0";

    private String zanCount = "0";

    private String ifZan = "0";

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

    public List<ArticleCommentAtMember> getAtMembers() {
        return atMembers;
    }

    public void setAtMembers(List<ArticleCommentAtMember> atMembers) {
        this.atMembers = atMembers;
    }

    public String getIfHaveList() {
        return ifHaveList;
    }

    public void setIfHaveList(String ifHaveList) {
        this.ifHaveList = ifHaveList;
    }

    public String getZanCount() {
        return zanCount;
    }

    public void setZanCount(String zanCount) {
        this.zanCount = zanCount;
    }

    public String getIfZan() {
        return ifZan;
    }

    public void setIfZan(String ifZan) {
        this.ifZan = ifZan;
    }
}
