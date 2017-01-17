package com.dengyuecang.www.service.members.model;

import com.dengyuecang.www.service.circle.model.MomentInterest;

import java.util.*;

/**
 * Created by acang on 16/7/18.
 */
public class CommunityMemberResponse {

    private String id;

    private String nickname;

    private String head;

    private String introduction;

    private String sex;

    private String province;

    private String area;

    private String city;

    private String school;

    private String enrollment;

    private Date ctime;

    private String timestamp;

    private List<MomentInterest> interestBars;

    private String username;

    private String ifFollow;

    private String followCount;

    private String fansCount;

    private String messageCount;

    public CommunityMemberResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public List getInterestBars() {
        return interestBars;
    }

    public void setInterestBars(List<MomentInterest> interestBars) {
        this.interestBars = interestBars;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIfFollow() {
        return ifFollow;
    }

    public void setIfFollow(String ifFollow) {
        this.ifFollow = ifFollow;
    }

    public String getFollowCount() {
        return followCount;
    }

    public void setFollowCount(String followCount) {
        this.followCount = followCount;
    }

    public String getFansCount() {
        return fansCount;
    }

    public void setFansCount(String fansCount) {
        this.fansCount = fansCount;
    }

    public String getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(String messageCount) {
        this.messageCount = messageCount;
    }
}
