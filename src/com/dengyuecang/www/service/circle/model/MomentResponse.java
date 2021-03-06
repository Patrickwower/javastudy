package com.dengyuecang.www.service.circle.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acang on 2016/12/12.
 */
public class MomentResponse {

    private String momentId;

    private String content;

    private MomentCreater creater;

    private MomentInterest interest;

    private String coverimg;

    private List<MomentImg> imgs = new ArrayList<MomentImg>();

    private String timestamp;

    private String date;

    private String zanCount;

    private String ifZan;

    private String commentCount;

    private String ifComment;

    private String public_level;

    public String getMomentId() {
        return momentId;
    }

    public void setMomentId(String momentId) {
        this.momentId = momentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MomentCreater getCreater() {
        return creater;
    }

    public void setCreater(MomentCreater creater) {
        this.creater = creater;
    }

    public MomentInterest getInterest() {
        return interest;
    }

    public void setInterest(MomentInterest interest) {
        this.interest = interest;
    }

    public String getCoverimg() {
        return coverimg;
    }

    public void setCoverimg(String coverimg) {
        this.coverimg = coverimg;
    }

    public List<MomentImg> getImgs() {
        return imgs;
    }

    public void setImgs(List<MomentImg> imgs) {
        this.imgs = imgs;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getIfComment() {
        return ifComment;
    }

    public void setIfComment(String ifComment) {
        this.ifComment = ifComment;
    }

    public String getPublic_level() {
        return public_level;
    }

    public void setPublic_level(String public_level) {
        this.public_level = public_level;
    }
}
