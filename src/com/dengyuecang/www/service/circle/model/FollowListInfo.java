package com.dengyuecang.www.service.circle.model;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/1/12.
 */
public class FollowListInfo {

    private String followId;

    private String followedId;

    private String imgurl;
    private String ctime;
    private String timestamp;
    private List<String> interestname;
    private String status;

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
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

    public List<String> getInterestname() {
        return interestname;
    }

    public void setInterestname(List<String> interestname) {
        this.interestname = interestname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }

    public String getFollowedId() {
        return followedId;
    }

    public void setFollowedId(String followedId) {
        this.followedId = followedId;
    }
}
