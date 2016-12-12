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

    private long timestamp;

    private String date;

    private String zanCount;

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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
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
}
